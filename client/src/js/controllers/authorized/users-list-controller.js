angular.module('iss.hospital').controller('UsersListController', [
	'$scope',
	'$state',
	'$stateParams',
	'apiService',
	'errorHandler',
	'Notification',
	'Constants',
	'identityService',
	function UsersListController($scope, $state, $stateParams, apiService, errorHandler, Notification, Constants, identityService) {
		'use strict';

		$scope.users = [];
		$scope.pageHeader = $stateParams.type === 'staff' ? 'Staff members' : 'Patients';
		$scope.type = $stateParams.type;
		$scope.identity = null;

		$scope.itemsPerPage = 10;
		$scope.totalItems = 0;
		$scope.currentPage = 1;

		$scope.$watch(function() {
			return $scope.users;
		}, function(newValue) {
			$scope.totalItems = newValue.length;
		});

		$scope.isAuthorized = isAuthorized;

		$scope.removeUser = removeUser;

		initialize();

		function initialize() {

			identityService.getUserIdentity().then(function(identity) {
				$scope.identity = identity;
			});

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

		function isAuthorized() {
			return $scope.identity && ($scope.identity.role === Constants.ROLES.ADMIN || $scope.identity.role === Constants.ROLES.DOCTOR);
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
