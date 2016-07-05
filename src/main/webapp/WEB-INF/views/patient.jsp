<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Patient Page</title>
<!-- <style>
.username.ng-valid {
	background-color: lightgreen;
}

.username.ng-dirty.ng-invalid-required {
	background-color: red;
}

.username.ng-dirty.ng-invalid-minlength {
	background-color: yellow;
}

.email.ng-valid {
	background-color: lightgreen;
}

.email.ng-dirty.ng-invalid-required {
	background-color: red;
}

.email.ng-dirty.ng-invalid-email {
	background-color: yellow;
}
</style> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/user_service.js' />"></script>
<script src="<c:url value='/static/js/controller/user_controller.js' />"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div ng-controller="UserController as ctrl" class="generic-container">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="lead">User Information </span> <a href="logout">logout</a>
			</div>

			<div class="formcontainer">
				<form ng-submit="ctrl.submit()" name="myForm"
					class="form-horizontal">
					<input type="hidden" ng-model="ctrl.user.username"
						style="width: 5px; height: 5px;" />



					<div class="row">
						<div ng-app="myApp" ng-controller="UserController">{{
							"Welcome back! "
							}}</div>
					</div>
					
					<div type="hidden" class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">UserName</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.userName" name="userName"
									class="username form-control input-sm"
									placeholder="Enter your name" required ng-minlength="3" />
								
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Password</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.password" name="password"
									class="password form-control input-sm"
									placeholder="Edit your Password" required />
								
							</div>
						</div>
					</div>



					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">First
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.firstName"
									class="form-control input-sm"
									placeholder="Edit your Firstname. [This field is validation free]" />
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Last
								Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.lastName"
									class="form-control input-sm"
									placeholder="Edit your Lastname. [This field is validation free]" />
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Phone
								Number</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.phoneNum"
									class="form-control input-sm"
									placeholder="Edit your Phone Number. [This field is validation free]" />
							</div>
						</div>
					</div>


					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Address</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.user.address"
									class="form-control input-sm"
									placeholder="Edit your Address. [This field is validation free]" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-group col-md-12">
							<label class="col-md-2 control-lable" for="file">Email</label>
							<div class="col-md-7">
								<input type="email" ng-model="ctrl.user.email" name="email"
									class="email form-control input-sm"
									placeholder="Edit your Email" required />
								<div class="has-error" ng-show="myForm.$dirty">
									<span ng-show="myForm.email.$error.required">This is a
										required field</span> <span ng-show="myForm.email.$invalid">This
										field is invalid </span>
								</div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="form-actions floatRight">
							<input type="submit" value="Edit" class="btn btn-primary btn-sm"
								ng-disabled="myForm.$invalid">

						</div>
					</div>
				</form>
			</div>
		</div>






	</div>
	</div>



</body>
</html>