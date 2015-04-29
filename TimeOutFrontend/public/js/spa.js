angular.module("timeout", ["ngRoute"])
	.config(function($routeProvider) {
	  $routeProvider
	   	 .when("/", {
		    templateUrl: "main.html",
		    controller: "MainController"
		  })
		  .when("/home", {
		    templateUrl: "home.html",
		    controller: "HomeController"
		  })
		  .when("/createEvent", {
		  	templateUrl: "createEvent.html",
		  	controller: "createEvent"
		  })
		  .when("/createGroup", {
		  	templateUrl: "createGroup.html",
		  	controller: "createGroup"
		  })
		  .when("/profileEdit", {
		  	templateUrl: "profileEdit.html",
		  	controller: "profileEdit"
		  })
		  .when("/eventsInvited", {
		  	templateUrl: "eventsInvited.html",
		  	controller: "eventsInvited"
		  })
		  .when("/eventsCreated", {
		  	templateUrl: "eventsCreated.html",
		  	controller: "eventsCreated"
		  })
		  .when("/friendsGroups", {
		  	templateUrl: "friendsGroups.html",
		  	controller: "friendsGroups"
		  })
		  .when("/myEvents", {
		  	templateUrl: "myEvents.html",
		  	controller: "myEvents"
		  })
		  .when("/myGroups", {
		  	templateUrl: "myGroups.html",
		  	controller: "myGroups"
		  })
		  .when("/newGroups", {
		  	templateUrl: "newGroups.html",
		  	controller: "newGroups"
		  })
		  .when("/search", {
		  	templateUrl: "search.html",
		  	controller: "searchController"
		  })
		  .when("/suggestedGroups", {
		  	templateUrl: "suggestedGroups.html",
		  	controller: "suggestedGroups"
		  })
		  .when("/myFriends", {
		  	templateUrl: "myFriends.html",
		  	controller: "myFriends"
		  })
		  .when("/myProfile", {
		  	templateUrl: "myProfile.html",
		  	controller: "myProfile"
		  });
	})

	.controller("IndexController", function($scope, $http, $location, $window, timeOutFactory) {
		console.log("IndexController works");
		$scope.doLogin = function() {
			// Simple GET request example :
			var loginUrl = timeOutFactory.getBackendUrl() + "/login?userEmail=" + $scope.userEmail + "&password=" + $scope.loginPassword;
			console.log(loginUrl);
			$http({method: "GET",  url: loginUrl})
			  .success(function(data, status) {
			    if(data.type == "Success") {
			    	$location.path("/home");
			    	timeOutFactory.setUserLoggedIn(true);
			    	timeOutFactory.setSessionId(data.cookie);
			    } else {
			    	$window.alert(data.type + ": " + data.message);
			    }
			  })
			  .error(function(data, status) {
			 	$window.alert("Specified username or password do not match with the records!!!");
			  });
		};

		$scope.isCookieSet = function() {
			return timeOutFactory.isUserLoggedIn();
		};

		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
		};

		$scope.search = function(url){
			timeOutFactory.setSearchText($scope.searchText);
			$scope.goToPage(url);
		};
	})

	.controller("MainController", function($scope, $http, $location, $window, timeOutFactory) {
		$scope.signUp = function() {
			// Simple GET request example :
			$http({method: "GET",  url: timeOutFactory.getBackendUrl() + "/register?userEmail=" + $scope.email + "&password=" + $scope.sigUpPassword})
			  .success(function(data, status) {
			    if(data.type == "Success") {
			    	$window.alert(data.message);
			    	$scope.name = "";
			    	$scope.lastName = "";
			    	$scope.email = "";
			    	$scope.reEmail = "";
			    	$scope.sigUpPassword = "";
			    	$scope.rePassword = "";
			    }
			  })
			  .error(function(data, status) {
			 	$window.alert(stringfy(data));
			  });
		};
	})

	.controller("searchController", function($scope, $http, $location, $window, timeOutFactory) {
		// Simple GET request example :
		$http({method: "GET",  url: timeOutFactory.getBackendUrl() + "/searchTag?tag=" + timeOutFactory.getSearchText()})
		  .success(function(data, status) {
		    console.log(data);
		  })
		  .error(function(data, status) {
		 	$window.alert("Specified username or password do not match with the records!!!");
		  });
	})

	.controller("HomeController", function($scope, $http, $window, $location) {
		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
			//$location.path("/eventsCreated");
			//$location.path("/suggestedGroups");
			//$location.path("/newGroups");
			//$location.path("/friendsGroups");
			//$location.path("/eventsInvited");
		};
	})

	.controller("eventsCreated", function($scope, $http, $window, $location) {

		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
		};
	})

	.controller("profileEdit", function($scope, $http, $window, $location, timeOutFactory) {

		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
		};

		$scope.genderOption = ['male', 'female', 'other'];
		$scope.selectedLanguage = ['Turkish','English', 'French', 'German', 'Italian', 'Spanish'];

		$scope.profileEdit = function(){

		$http.post(timeOutFactory.getBackendUrl() + '/profile/edit?userEmail=' + $scope.userName )
		 .success(function(data, status) {
			$window.alert("Success " + data.actionId);
		  })
		  .error(function(data, status) {
		 	console.log("Error");
		  });
		 }
	})

	.controller("createEvent", function($scope, $http, $window, $location, timeOutFactory) {

		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
		};

		$scope.createEvent = function(){
			var sessionId = timeOutFactory.getSessionId();
			var config = {headers: {'Set-Cookie': String(sessionId)};

			$http.get(timeOutFactory.getBackendUrl() + '/event/create' + '?eventName=' + $scope.eventName + '&eventDescription=' + $scope.eventDescription, config})
			 .success(function(data, status) {
				$window.alert("Success " + data.actionId);
			  })
			  .error(function(data, status) {
			 	console.log("Error " + data);
			  });
		}
	})

	.controller("createGroup", function($scope, $http, $window, $location, timeOutFactory) {

		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
		};

		$scope.createGroup = function() {
			$http.post('/group/create?groupName=' + $scope.groupName + '&groupDescription=' + $scope.groupDescription + '&tag=' + $scope.tag)
			 .success(function(data, status) {
				$window.alert("Success " + data.actionId);
			  })
			  .error(function(data, status) {
			 	console.log("Error");
			  });
		}
	})

	.controller("myFriends", function($scope, $http, $window, $location) {

		$scope.goToPage = function(url) {
			console.log("GoToPage:" + url);
			$location.path(url);
		};
	})

	.factory("timeOutFactory", function(){
		var timeOutFactory = {};
		var lists = [];
		var userLoggedIn = false;
		var backendUrl = "http://localhost:8080";
		//var backendUrl = "http://timeoutswe5743.appspot.com";
		var sessionId = "";
		var searchText = "";

		timeOutFactory.getLists = function(){
			return lists;
		};

		timeOutFactory.getList = function(listId){
			return lists[listId];
		};

		timeOutFactory.addList = function(newList){
			lists.push({
				id:lists.length,
				name:newList,
				items:[]
			});
		};

		timeOutFactory.addItem = function(listId, newItem){
			lists[listId].items.push(newItem);
		};

		timeOutFactory.isUserLoggedIn = function(){
			return userLoggedIn;
		};

		timeOutFactory.setUserLoggedIn = function(value){
			userLoggedIn = value;
		};

		timeOutFactory.getBackendUrl = function(){
			return backendUrl;
		};

		timeOutFactory.getSessionId = function(){
			return sessionId;
		};

		timeOutFactory.setSessionId = function(cookie){
			sessionId = cookie;
		};

		timeOutFactory.getSearchText = function(){
			return searchText;
		};

		timeOutFactory.setSearchText = function(text){
			searchText = text;
		};

		return timeOutFactory;
	});