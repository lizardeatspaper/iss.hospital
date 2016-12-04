angular.module('iss.hospital').controller('UsersListController', [
	'$scope',
	'$state',
	'$stateParams',
	'apiService',
	'errorHandler',
	function UsersListController($scope, $state, $stateParams, apiService, errorHandler) {
		'use strict';

		$scope.users = [];
		$scope.pageHeader = $stateParams.type === 'staff' ? 'Staff members' : 'Patients';
		$scope.type = $stateParams.type;

		initialize();

		function initialize() {
			switch ($stateParams.type) {
				case 'staff':
					loadStaffMembers();
					break;
				case 'patients':
					loadPatients();
					break;
				default:
					$state.go('hospital.authorized.usersList', {type: 'patients'});
					break;
			}
		}

		function loadPatients() {
			apiService.getPatients().then(function(response) {
				$scope.users = response.data;
			}, errorHandler);
		}

		function loadStaffMembers() {
			apiService.getStaffMembers().then(function(response) {
				$scope.users = response.data;
			}, errorHandler);
		}
	}
]);
