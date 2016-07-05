package com.shentong.controller;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.shentong.DAO.UserDAO;
import com.shentong.DAO.UserProfileDAO;
import com.shentong.model.User;
import com.shentong.model.UserProfile;
import com.shentong.service.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Autowired
	private UserDAO userDao;

	@Autowired
	UserService userService;

	@Autowired
	private UserProfileDAO userProfileDao;

	/**
	 * Show the login view first
	 */
	

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String gotoLogin() {
		return "login";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/jump", method=RequestMethod.GET)
	public ModelAndView jumpPage(HttpServletRequest request, HttpServletResponse response){
		Collection<? extends GrantedAuthority> auth = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(auth.toString().equals("[ROLE_ADMIN]")){
			return new ModelAndView("redirect:/admin");
		}else{
			UserProfile emp = userProfileDao.findByUsername(username);
			return new ModelAndView("/patient","command",emp);
		}
		
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String gotoAdmin() {
		
		return "admin";
	}
	@RequestMapping(value = "/patient", method = RequestMethod.GET)
	public String gotoPatient() {
		return "patient";
	}
	

	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<UserProfile>> listAllPatients() {
		List<UserProfile> users = userProfileDao.findAllUserProfiles();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserProfile>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserProfile>>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> listAllUser() {
		List<User> users = userDao.findAllUsers();
	    if(users.isEmpty()){
	    	return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	    }
	    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value="/profiles/{username}", method=RequestMethod.GET, produces={"application/json"})
	@ResponseBody
	public ResponseEntity<UserProfile> getPatient(@PathVariable("username") String username){
		UserProfile p = userProfileDao.findByUsername(username);
		if(p==null){
			return new ResponseEntity<UserProfile>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<UserProfile>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{username}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUser(@PathVariable("username") String username){
		User p = userDao.findByUsername(username);
		if(p==null){
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(p, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add/user", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> createUser(@RequestBody User user){
		User u = userDao.findByUsername(user.getUserName());
		if(u!=null){
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}else{
			userDao.save(user);
		}
		return new ResponseEntity<User>(user,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/add/profile", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserProfile> createPatient(@RequestBody UserProfile userProfile){
		UserProfile p = userProfileDao.findByUsername(userProfile.getUserName());
		if(p!=null){
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_ACCEPTABLE);
		}
		userProfileDao.save(userProfile);
		
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.gmail.com");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(userProfile.getEmail());  
        mailMessage.setFrom("qishentong2014@gmail.com");  
        mailMessage.setSubject("One patient has been created!");  
        mailMessage.setText("Your patient "+userProfile.getFirstName()+" "+userProfile.getLastName()+" has been created!");  
        senderImpl.setUsername("qishentong2014@gmail.com");
        senderImpl.setPassword("qishentong");
        senderImpl.setPort(587);
        Properties prop = new Properties();  
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.debug", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        senderImpl.setJavaMailProperties(prop);  
        
        senderImpl.send(mailMessage);  
        
		return new ResponseEntity<UserProfile>(userProfile,HttpStatus.CREATED);
	}

	@RequestMapping(value="/edit/user", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<User> editUser(@RequestBody User user){
		User u = userDao.findByUsername(user.getUserName());
		if(u==null){
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}else{
			userDao.save(user);
		}
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/edit/profile", method=RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<UserProfile> editPatient(@RequestBody UserProfile userProfile){
		UserProfile p = userProfileDao.findByUsername(userProfile.getUserName());
		if(p==null){
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_ACCEPTABLE);
		}else{
			userProfileDao.save(userProfile);
		}
		return new ResponseEntity<UserProfile>(userProfile, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/delete/user/{username}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<User> delUser(@PathVariable("username") String username){
		User u = userDao.findByUsername(username);
		if(u==null){
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}else{
			userProfileDao.delete(username);
			userDao.deleteByUsername(username);;
		}
		return new ResponseEntity<User>(u, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="/delete/patient/{username}", method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<UserProfile> delPatient(@PathVariable("username") String username){
		UserProfile p = userProfileDao.findByUsername(username);
		if(p==null){
			return new ResponseEntity<UserProfile>(HttpStatus.NOT_ACCEPTABLE);
		}else{
			userProfileDao.delete(username);
		}
		return new ResponseEntity<UserProfile>(p, HttpStatus.ACCEPTED);
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();

		}
		return userName;
	}
	
}
