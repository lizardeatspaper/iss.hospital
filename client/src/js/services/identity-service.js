angular.module('iss.hospital').factory('identityService', [
    'apiService',
    'errorHandler',
    '$q',
    function identityServiceFactory(apiService, errorHandler, $q) {
        'use strict';

        var user = null;

        var identityService = {
            getUserIdentity: getUserIdentity,
            setUserIdentity: setUserIdentity
        };

        return identityService;

        /**
         * Get saved user identity.
         *
         * @returns {Promise}
         */
        function getUserIdentity() {
            if (!user) {
                return apiService.getLoggedUser().then(function(response) {
                    return response.data;
                }, errorHandler);
            } else {
                return $q.when(user);
            }
        }

        /**
         * Set user identity.
         * @param {Object} newIdentity
         */
        function setUserIdentity(newIdentity) {
            user = newIdentity;
        }
    }
]);