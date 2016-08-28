(function(angular,$){
	'use strict';
	window.contextPath = "http://localhost:9000";
	angular.module('spring-security', ['ngCookies'])
	.run(['$http', '$cookies', function($http, $cookies) {
		$http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		$http.defaults.headers.common['X-Auth-Token'] = $cookies.get('X-Auth-Token');
	}])
	.controller('IndexController', ['$scope','$http','$cookies',function($scope,$http,$cookies) {
	    $scope.credentials = {};
	    $scope.login = function(){
	    	return $http.post(contextPath+"/j_spring_security_check",null,{
	            params: $scope.credentials
	        }).then(function (resp) {
	        	var result = resp.data;
	        	$scope.message = result.text;
	        	if(result.code == 0){//save cookie
	        		$cookies.put('X-Auth-Token',result.data);
	        		$http.defaults.headers.common['X-Auth-Token'] = result.data;
	        	}
	        });
	    }
	    $scope.logout = function() {
			$http.post(contextPath+'/logout', {}).then(function(resp){
				$scope.message = resp.data.text;
				$scope.greeting = {};
			});
		}
	    $scope.resource = function(){
	    	$http.get(contextPath+'/resource').then(function(resp) {
				if(resp.data.code==-500){
	    			$scope.message = resp.data.text;
	    		}
	    		$scope.greeting = resp.data;
			});
	    }
	}]);
})(angular,jQuery);