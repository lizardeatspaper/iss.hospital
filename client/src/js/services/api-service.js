angular.module('iss.hospital').factory('apiService', [
	'$http',
	function apiServiceFactory($http) {
		'use strict';

		var apiService = {
			login: login,
			getLoggedUser: getLoggedUser
		};

		return apiService;

		/**
		 * Send request to the server and return HTTP Promise.
		 *
		 * @param  {string} config.method
		 * @param  {string} config.url
		 * @param  {Object} config.headers
		 * @param  {Object} config.data
		 * @param  {Boolean} config.withCredentials
		 * @return {HttpPromise}
		 */
		function request(config) {
			if (!config.method || !config.url) {
				throw 'apiService: request(): invalid config';
			}

			if (!angular.isObject(config.headers)) {
				config.headers = {};
			}

			if (!config.headers['Content-Type']) {
				config.headers['Content-Type'] = 'application/json';
			}

			return $http(config);
		}

		function login(data) {
			return request({
				method: 'POST',
				url: 'http://localhost:8080/api/login',
				data: data
			});
		}

		function getLoggedUser() {
			return request({
				method: 'GET',
				url: 'http://localhost:8080/api/user'
			});
		}
	}
]);