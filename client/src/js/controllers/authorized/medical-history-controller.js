angular.module('iss.hospital').controller('MedicalHistoryController', [
	'$scope',
	'$stateParams',
	'apiService',
	'errorHandler',
	function MedicalHistoryController($scope, $stateParams, apiService, errorHandler) {
		'use strict';

		// public variables
		$scope.medicalHistory = [];
		$scope.patientId = $stateParams.patientId;

		initialize();

		function initialize() {
			apiService.getHistoryByPatientId($stateParams.patientId).then(function(response) {
				$scope.medicalHistory = response.data;
			}, errorHandler);
		}
	}
]);