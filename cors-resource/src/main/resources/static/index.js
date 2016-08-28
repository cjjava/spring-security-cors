(function(angular,$){
	'use strict';
	window.contextPath = "";
	angular.module('spring-security', [])
	.run(['$http', function($http) {
		$http.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	}])
	.controller('IndexController', ['$scope','$http',function($scope,$http) {
	    $scope.credentials = {};
	    $scope.login = function(){
	    	return $http.post(contextPath+"/j_spring_security_check",null,{
	            params: $scope.credentials
	        }).then(function (resp) {
	        	$scope.message = resp.data.text;
	        });
	    }
	    $scope.logout = function() {
			$http.post(contextPath+'logout', {}).then(function(resp){
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
	    	})
	    }
	}]);
})(angular,jQuery);