angular.module('iss.hospital').directive('userForm', [
    function userFormDirectiveFactory() {
        'use strict';

        return {
            restrict: 'E',
            scope: {
                mode: '='
            },
            templateUrl: 'authorized/directives/user-form-directive.html',
            controller: [
                '$scope',
                '$stateParams',
                '$state',
                'identityService',
                'Constants',
                'apiService',
                'Notification',
                'errorHandler',
                '$filter',
                function UserFormDirectiveController($scope, $stateParams, $state, identityService, Constants, apiService, Notification, errorHandler, $filter) {
                    $scope.account = null;
                    $scope.identity = {};
                    $scope.statuses = [];
                    $scope.roles = [];
                    $scope.departments = [];
                    $scope.isLoading = true;

                    $scope.isAuthorized = isAuthorized;
                    $scope.isPatient = isPatient;
                    $scope.isDoctor = isDoctor;
                    $scope.submit = submit;

                    initialize();

                    function initialize() {
                        loadEnums();
                        identityService.getUserIdentity().then(function(response) {
                            $scope.identity = formatAccountData(response);

                            if ($scope.mode === 'edit') {
                                loadAccount();
                            } else {
                                $scope.account = {};
                                $scope.isLoading = false;
                            }

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

                    function submit() {
                        if ($scope.mode === 'edit') {
                            saveAccountData();
                        } else {
                            createAccount();
                        }
                    }

                    function saveAccountData() {
                        var apiFunction;
                        var accountToSend = angular.copy($scope.account);

                        delete accountToSend.lastModifiedDate;

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
                                throw 'UserFormDirectiveController: saveAccountData(): invalid account role';
                        }

                        $scope.isLoading = true;
                        apiFunction(accountToSend).then(function() {
                            $scope.isLoading = false;
                            Notification.success({title: 'Success', message: 'Account data has been successfully updated.'})
                        }, function(reason) {
                            $scope.isLoading = false;
                            errorHandler(reason);
                        });
                    }

                    function createAccount() {
                        var accountToCreate = angular.copy($scope.account);
                        var apiFunction;
                        var type;

                        accountToCreate.birthdate = Date.parse(accountToCreate.birthdate);
                        console.log(accountToCreate.birthdate);

                        switch(accountToCreate.role) {
                            case Constants.ROLES.PATIENT:
                                apiFunction = apiService.createPatient;
                                type = 'patients';
                                break;
                            case Constants.ROLES.DOCTOR:
                            case Constants.ROLES.NURSE:
                            case Constants.ROLES.DOCTOR:
                            case Constants.ROLES.ADMIN:
                                apiFunction = apiService.createStaffMember;
                                type = 'staff';
                                break;
                            default:
                                throw 'UserFormDirectiveController: createAccount(): invalid account role.'
                        }

                        $scope.isLoading = true;
                        apiFunction(accountToCreate).then(function(response) {
                            $scope.isLoading = false;
                            Notification.success({title: 'Success', message: 'Account has been successfully created.'});
                            $state.go('hospital.authorized.usersList', {type: type});
                        }, function(error) {
                            $scope.isLoading = false;
                            errorHandler(error);
                        });
                    }

                    /**
                     * Helper function to populate enums.
                     */
                    function loadEnums() {
                        angular.forEach(Constants.STATUSES, function(status, key) {
                            $scope.statuses.push({key: key, value: status});
                        });

                        angular.forEach(Constants.ROLES, function(role, key) {
                            if (Constants.ROLES.ALL !== role) {
                                $scope.roles.push({key: key, value: role});
                            }
                        });

                        apiService.getDepartments().then(function(response) {
                            $scope.departments = response.data;
                        });
                    }

                    function formatAccountData(data) {
                        data.birthdate = new Date(data.birthdate);
                        data.department = data.department && $filter('filter')($scope.departments, {name: data.department})[0];
                        data.username = data.account && data.account.userName;
                        return data;
                    }

                    /**
                     * Check if user is authorized to change account.
                     *
                     * @returns {boolean}
                     */
                    function isAuthorizedToEdit() {
                        return $scope.mode === 'edit' && ($scope.identity && ($scope.identity.role === Constants.ROLES.ADMIN)) ||
                            ($scope.identity && $scope.account && (($scope.account.id === $scope.identity.id) ||
                            ($scope.identity.role === Constants.ROLES.DOCTOR && $scope.account.role === Constants.ROLES.PATIENT)));
                    }

                    /**
                     * Check if user is authorized to create account.
                     *
                     * @returns {boolean}
                     */
                    function isAuthorizedToCreate() {
                        return $scope.mode === 'create' && (($scope.identity &&
                            ($scope.identity.role === Constants.ROLES.DOCTOR || $scope.identity.role === Constants.ROLES.ADMIN)));
                    }

                    function isAuthorized() {
                        return isAuthorizedToCreate() || isAuthorizedToEdit();
                    }

                    function isPatient() {
                        return $scope.account && $scope.account.role === Constants.ROLES.PATIENT;
                    }

                    function isDoctor() {
                        return $scope.account && $scope.account.role === Constants.ROLES.DOCTOR;
                    }
                }
            ]
        };
    }
]);