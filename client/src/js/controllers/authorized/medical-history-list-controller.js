angular.module('iss.hospital').controller('MedicalHistoryListController', [
	'$scope',
	'$stateParams',
	'apiService',
	'errorHandler',
	'identityService',
	'Constants',
	'Notification',
	function MedicalHistoryListController($scope, $stateParams, apiService, errorHandler, identityService, Constants, Notification) {
		'use strict';

		// public variables
		$scope.medicalHistory = [];
		$scope.patientId = $stateParams.patientId;
		$scope.identity = null;

		$scope.isAuthorized = isAuthorized;
		$scope.removeHistoryRecord = removeHistoryRecord;

		initialize();

		function initialize() {
            identityService.getUserIdentity().then(function(identity) {
				$scope.identity = identity;
            });

			apiService.getHistoryByPatientId($stateParams.patientId).then(function(response) {
				$scope.medicalHistory = response.data;
			}, errorHandler);
		}

		function isAuthorized() {
			return $scope.identity && ($scope.identity.role === Constants.ROLES.DOCTOR || $scope.identity.role === Constant.ROLES.ADMIN);
		}

		function removeHistoryRecord(item) {
			apiService.deleteHistory(item.id).then(function(response) {
				Notification.success({title: 'Success', message: 'History record has been successfully deleted.'});
				var index = $scope.medicalHistory.indexOf(item);
				if (index >= 0) {
					$scope.medicalHistory.splice(index, 1);
				}
			}, errorHandler);
		}
	}
]);