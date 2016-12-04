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
                function UserFormDirectiveController($scope) {

                }
            ]
        };
    }
]);