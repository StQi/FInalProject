'use strict';

App.factory('UserService', ['$http', '$q', function($http, $q){

	return {
		
			findAllUsers: function() {
					return $http.get('http://localhost:8181/beaconProjectT/users/')
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while fetching users');
										return $q.reject(errResponse);
									}
							);
			},
		    
		    saveUser: function(user){
					return $http.post('http://localhost:8181/beaconProjectT/add/profile', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while creating user');
										return $q.reject(errResponse);
									}
							);
		    },
		    
		    updateUser: function(user, username){
					return $http.put('http://localhost:8181/beaconProjectT/edit/profile', user)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while updating user');
										return $q.reject(errResponse);
									}
							);
			},
		    
			deleteUser: function(username){
					return $http['delete']('http://localhost:8181/beaconProjectT/delete/user/'+username)
							.then(
									function(response){
										return response.data;
									}, 
									function(errResponse){
										console.error('Error while deleting user');
										return $q.reject(errResponse);
									}
							);
			}
		
	};

}]);
