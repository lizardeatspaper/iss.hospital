angular.module('iss.hospital').controller('MedicalHistoryListController', [
	'$scope',
	'$stateParams',
	'apiService',
	'errorHandler',
	'identityService',
	'Constants',
	function MedicalHistoryListController($scope, $stateParams, apiService, errorHandler, identityService, Constants) {
		'use strict';

		// public variables
		$scope.medicalHistory = [];
		$scope.patientId = $stateParams.patientId;
		$scope.identity = null;

		$scope.isAuthorized = isAuthorized;

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
	}
]);