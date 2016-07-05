package com.shentong.configure;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//    @Qualifier("customUserDetailsService")
//    UserDetailsService userDetailsService;
	
//	 @Autowired
//	    CustomSuccessHandler customSuccessHandler;
 
//    @Autowired
//    PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select username,password,1 from PUSERS where username = ? ")
		.authoritiesByUsernameQuery("select username,role from PUSERS where username = ?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		
		.antMatchers("/").permitAll()
		.antMatchers("/admin").access("hasRole('ADMIN')")
		.antMatchers("/patient/*").access("hasRole('USER')")
		.antMatchers("/jump").authenticated()
		.and().formLogin().loginPage("/login")
		.usernameParameter("username").passwordParameter("password")
		.and().csrf().disable();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
	
//		.antMatchers("/","/login").permitAll()
//		.antMatchers("/admin").access("hasRole('ADMIN')")
//		.antMatchers("/patient/**").access("hasRole('ADMIN') and hasRole('USER')")
//		.and().formLogin().loginPage("/login")
//		.usernameParameter("username").passwordParameter("password");
//		.and().csrf().disable();
//		.and().exceptionHandling().accessDeniedPage("/Access_Denied");;
//	}
//	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/", "/home").permitAll()
//		.antMatchers("/admin/**").access("hasRole('ADMIN')")
//		.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
//		.and().formLogin().loginPage("/login")
//		.usernameParameter("ssoId").passwordParameter("password")
//		.and().csrf()
//		.and().exceptionHandling().accessDeniedPage("/Access_Denied");
//	}
	
	@Autowired
	DataSource dataSource;
}
