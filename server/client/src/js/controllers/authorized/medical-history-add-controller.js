angular.module('iss.hospital').controller('MedicalHistoryAddController', [
    '$scope',
    '$stateParams',
    'apiService',
    'errorHandler',
    'Notification',
    '$state',
    function MedicalHistoryAddController($scope, $stateParams, apiService, errorHandler, Notification, $state) {
        'use strict';

        $scope.values = {};

        $scope.submit = submit;

        function submit() {
            var data = {
                patientId: parseInt($stateParams.patientId),
                doctorId: $stateParams.doctorId,
                title: $scope.values.title,
                description: $scope.values.description
            };

            apiService.createHistory(data).then(function() {
                Notification.success({title: 'Success', message: 'Medical history record has been added.'});
                $state.go('hospital.authorized.medicalHistory.list', {patientId: $stateParams.patientId});
            }, errorHandler);
        }
    }
]);