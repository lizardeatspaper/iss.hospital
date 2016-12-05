angular.module('iss.hospital').factory('errorHandler', [
	'Notification',
	'$state',
	function errorHandlerFactory(Notification, $state) {
		'use strict';

		return function errorHandler(error) {
			if (error.status === 403) {
				$state.go('hospital.authorization.login');
			}

			var notification;
			if (angular.isObject(error)) {
				notification = {
					message: error.data.message,
					title: error.data.error
				};
			} else {
				notification = error;
			}

			Notification.error(notification);
		};
	}
]);