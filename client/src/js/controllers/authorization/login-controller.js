angular.module('iss.hospital').controller('LoginController', [
	'$scope',
	'$state',
	'apiService',
	function LoginController($scope, $state, apiService) {
		'use strict';

		// public variables
		$scope.values = {
			login: null,
			password: null
		};

		// public methods
		$scope.login = login;

		/**
		 * Send request to the backend to login.
		 */
		function login() {
			apiService.login({
				username: $scope.values.login,
				password: $scope.values.password
			}).then(function(response) {
				console.log(response);
				$state.go('hospital.authorized.account', {id: 1});
			});
		}
	}
]);
