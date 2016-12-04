angular.module('iss.hospital').controller('AccountPageController', [
	'$scope',
	'$stateParams',
	'identityService',
	'Constants',
	'apiService',
    'Notification',
    'errorHandler',
    function AccountPageController($scope, $stateParams, identityService, Constants, apiService, Notification, errorHandler) {
        'use strict';

        $scope.account = null;
        $scope.identity = null;
        $scope.statuses = [];
        $scope.departments = [];
        $scope.isLoading = true;

        $scope.isAuthorized = isAuthorized;
        $scope.saveAccountData = saveAccountData;

        initialize();

        function initialize() {
            identityService.getUserIdentity().then(function(response) {
                $scope.identity = formatAccountData(response);
                loadEnums();
                loadAccount();
            });
        }

        function loadAccount() {
            if (angular.isObject($stateParams.account)) {
                $scope.account = formatAccountData($stateParams.account);
            } else if ($stateParams.isOwner) {
                $scope.account = angular.copy($scope.identity);
            } else {
                apiService.getUser($stateParams.id).then(function(response) {
                    $scope.account = formatAccountData(response.data);
                });
            }

            $scope.isLoading = false;
        }

        function saveAccountData() {
            var apiFunction;

            switch ($scope.account.role) {
                case Constants.ROLES.DOCTOR:
                case Constants.ROLES.NURSE:
                case Constants.ROLES.ADMIN:
                    apiFunction = apiService.updateStaffMember;
                    break;
                case Constants.ROLES.PATIENT:
                    apiFunction = apiService.updatePatient;
                    break;
                default:
                    throw 'AccountPageController: saveAccountData(): invalid account role';
            }

            $scope.isLoading = true;
            apiFunction($scope.account).then(function() {
                $scope.isLoading = false;
                Notification.success({title: 'Success', message: 'Account data has been successfully updated.'})
            }, function(reason) {
                $scope.isLoading = false;
                errorHandler(reason);
            });
        }

        /**
         * Helper function to populate enums.
         */
        function loadEnums() {
            angular.forEach(Constants.STATUSES, function(status, key) {
                $scope.statuses.push(key);
            });

            apiService.getDepartments().then(function(response) {
                $scope.departments = response.data;
            });
        }

        function formatAccountData(data) {
            data.birthdate = new Date(data.birthdate);
            return data;
        }

        /**
         * Check if user is authorized to change the data.
         *
         * @returns {*|null|boolean}
         */
        function isAuthorized() {
            return ($scope.account && ($scope.account.id === $scope.identity.id)) ||
                ($scope.identity && ($scope.identity.role === Constants.ROLES.DOCTOR && $scope.account.role === Constants.ROLES.PATIENT)) ||
                ($scope.identity && ($scope.identity.role === Constants.ROLES.ADMIN));
        }
    }
]);
