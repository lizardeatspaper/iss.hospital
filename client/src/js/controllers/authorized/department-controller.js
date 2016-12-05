angular.module('iss.hospital').controller('DepartmentController', [
    '$scope',
    'apiService',
    'errorHandler',
    'Notification',
    function DepartmentController($scope, apiService, errorHandler, Notification) {
        'use strict';

        $scope.departments = [];
        $scope.values = {};

        initialize();

        function initialize() {
            apiService.getDepartments().then(function(response) {
                $scope.departments = response.data;
            }, errorHandler);
        }

        function createDepartment() {
            var department = {
                name: angular.copy($scope.values.name)
            };

            apiService.createDepartment(department).then(function(response) {
                Notification.success({title: 'Success', message: 'Department has been successfully created.'});
                $scope.values.name = '';
            }, errorHandler);
        }

        function deleteDepartment(id) {
            apiService.deleteDepartment(id).then(function(response) {
                Notification.success({title: 'Success', message: 'Department has been successfully deleted.'});
            }, errorHandler);
        }
    }
]);