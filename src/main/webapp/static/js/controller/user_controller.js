'use strict';

App.controller('UserController', ['$scope', 'UserService', function($scope, UserService) {
          var self = this;
          self.user={userName:null,password:'',role:'',firstName:'',lastName:'',phoneNum:'',address:'',email:''};
          self.users=[];
              
          self.findAllUsers = function(){
              UserService.findAllUsers()
                  .then(
      					       function(d) {
      						        self.users = d;
      					       },
            					function(errResponse){
            						console.error('Error while fetching Currencies');
            					}
      			       );
          };
           
          self.saveUser = function(user){
              UserService.saveUser(user)
		              .then(
                      self.findAllUsers, 
				              function(errResponse){
					               console.error('Error while creating User.');
				              }	
                  );
          };

         self.updateUser = function(user, username){
              UserService.updateUser(user, username)
		              .then(
				              self.findAllUsers, 
				              function(errResponse){
					               console.error('Error while updating User.');
				              }	
                  );
          };

         self.deleteUser = function(username){
              UserService.deleteUser(username)
		              .then(
				              self.findAllUsers, 
				              function(errResponse){
					               console.error('Error while deleting User.');
				              }	
                  );
          };

          self.findAllUsers();

          self.submit = function() {
        	  var index = -1;
        	  for(var i=0;i<self.users.length;i++){
        		  if(self.users[i].userName==self.user.userName){
        			  index=i;
        			  break;
        		  }
        	  }
              if(index==-1){
                  console.log('Saving New User', self.user);    
                  self.saveUser(self.user);
              }else{
                  self.updateUser(self.user, self.user.userName);
                  console.log('User updated with username ', self.user.userName);
              }
              self.reset();
          };
              
          self.edit = function(username){
              console.log('username to be edited', username);
              for(var i = 0; i < self.users.length; i++){
                  if(self.users[i].userName == username) {
                     self.user = angular.copy(self.users[i]);
                     break;
                  }
              }
          };
              
          self.remove = function(username){
              console.log('username to be deleted', username);
              if(self.user.username === username) {//clean form if the user to be deleted is shown there.
                 self.reset();
              }
              self.deleteUser(username);
          };

          
          self.reset = function(){
              self.user={userName:null,password:'',role:'',firstName:'',lastName:'',phoneNum:'',address:'',email:''};
              $scope.myForm.$setPristine(); //reset Form
          };

      }]);
