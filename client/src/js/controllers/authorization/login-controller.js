angular.module('iss.hospital').controller('LoginController', [
	'$scope',
	'$state',
	'apiService',
	'errorHandler',
	function LoginController($scope, $state, apiService, errorHandler) {
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
				var account = response.data;
				$state.go('hospital.authorized.account', {id: account.id, account: account, isOwner: true});
			}, errorHandler);
		}
	}
]);
