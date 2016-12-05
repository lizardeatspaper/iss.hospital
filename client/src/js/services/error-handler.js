angular.module('iss.hospital').factory('errorHandler', [
	'Notification',
	'$state',
	function errorHandlerFactory(Notification, $state) {
		'use strict';

		return function errorHandler(error) {
			if (error.status === 403) {
				$state.go('hospital.authorization.login');
			}

			if (angular.isObject(error.data)) {
				if (angular.isArray(error.data.errors)) {
					angular.forEach(error.data.errors, function(e) {
						Notification.error({title: error.data.error, message: e.field + ': ' + e.defaultMessage})
					});
				} else {
                    Notification.error({title: error.data.error, message: error.data.message})
                }
			} else if (error.status = 500) {
				Notification.error({title: 'Internal Server Error', message: 'Please try later.'});
			} else {
                Notification.error({title: 'Error', message: 'The request has ended with an error.'});
            }
		};
	}
]);