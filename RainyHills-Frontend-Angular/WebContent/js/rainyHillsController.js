	'use strict';
	
	var rainyHills = angular.module('rainyHills', [ 'ngRoute' ]);
	
	rainyHills.config([ '$routeProvider', '$sceProvider',
	
	function($routeProvider, $sceProvider) {
		$routeProvider.when('/', {
			templateUrl : '/RainyHills-Angular/html/home.html',
			controller : 'rainyHillsController'
		}).otherwise({
			redirectTo : '/'
		});
	
		$sceProvider.enabled(false);
	
	} ]);
	
	rainyHills.controller('rainyHillsController', function($scope, $http, $window) {
	
		// Function that is called when a pit is clicked
		$scope.calc = function(pHillsHeight) {
			const body = {
				hillsHeight : pHillsHeight
			};
			$http.post('http://localhost:8080/RainyHills-Services/api/surface-area/calculate/', body).success(
					function(data) {
						$scope.surface = data;
					});
		}
	
	});
