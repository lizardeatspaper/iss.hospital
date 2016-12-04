angular.module('iss.hospital').controller('MedicalHistoryController', [
	'$scope',
	'$stateParams',
	'apiService',
	'errorHandler',
	function MedicalHistoryController($scope, $stateParams, apiService, errorHandler) {
		'use strict';

		// public variables
		$scope.medicalHistory = [];

		initialize();

		function initialize() {
			apiService.getHistoryByPatientId($stateParams.patientId).then(function(response) {

			}, errorHandler);
		}
	}
]);