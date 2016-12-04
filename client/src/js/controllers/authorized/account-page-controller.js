angular.module('iss.hospital').controller('AccountPageController', [
	'$scope',
	'$stateParams',
	'apiService',
	function AccountPageController($scope, $stateParams, apiService) {
		'use strict';

		$scope.account = null;

		$scope.isAuthorized = isAuthorized;

		initialize();

		function initialize() {
			if (angular.isObject($stateParams.account)) {
				$scope.account = formatAccountData($stateParams.account);
			} else if ($stateParams.isOwner) {
				apiService.getLoggedUser().then(function(response) {
					$scope.account = formatAccountData(response.data);
				});
			} else {
				apiService.getUser($stateParams.id).then(function(response) {
					$scope.account = formatAccountData(response.data);
				});
			}
		}

		function formatAccountData(data) {
			data.birthdate = new Date(data.birthdate);
			return data;
		}

		function isAuthorized() {
			return $scope.account && $scope.account.role !== 'patient';
		}
	}
]);
