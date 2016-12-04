angular.module('iss.hospital').controller('MedicalHistoryDetailController', [
    '$scope',
    '$stateParams',
    'apiService',
    'errorHandler',
    function MedicalHistoryDetailController($scope, $stateParams, apiService, errorHandler) {
        'use strict';

        $scope.detail = null;
        $scope.patientId = $stateParams.patientId;
        $scope.historyId = $stateParams.id;

        initialize();

        function initialize() {
            apiService.getHistoryDetails($scope.historyId).then(function(response) {
                $scope.detail = response.data;
            }, errorHandler);
        }
    }
]);