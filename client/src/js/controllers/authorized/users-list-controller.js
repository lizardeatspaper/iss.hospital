angular.module('iss.hospital').controller('UsersListController', [
	'$scope',
	'$state',
	'$stateParams',
	'apiService',
	'errorHandler',
	'Notification',
	function UsersListController($scope, $state, $stateParams, apiService, errorHandler, Notification) {
		'use strict';

		$scope.users = [];
		$scope.pageHeader = $stateParams.type === 'staff' ? 'Staff members' : 'Patients';
		$scope.type = $stateParams.type;

		$scope.removeUser = removeUser;

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

		function removeUser(user) {
			var apiFunction;

			switch($stateParams.type) {
				case 'staff':
					apiFunction = apiService.deleteStaffMember;
					break;
				case 'patients':
					apiFunction = apiService.deletePatient;
					break;
				default:
					throw 'UserListController: removeUser(): invalid type.';
			}

			apiFunction(user.id).then(function(response) {
				Notification.success({title: 'Success', message: 'User account has been successfully deleted.'});
				var index = $scope.users.indexOf(user);
				if (index >= 0) {
					$scope.users.splice(index, 1);
				}
			}, function(response) {
				if (response.status === 204) {
                    Notification.success({title: 'Success', message: 'User account has been successfully deleted.'});
                    var index = $scope.users.indexOf(user);
                    if (index >= 0) {
                        $scope.users.splice(index, 1);
                    }
				} else {
					errorHandler(response);
				}
			});
		}
	}
]);
