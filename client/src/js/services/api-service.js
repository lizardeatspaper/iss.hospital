angular.module('iss.hospital').factory('apiService', [
	'$http',
	function apiServiceFactory($http) {
		'use strict';

		var serverUrl = window.location.protocol + '//' + window.location.host;

		var apiService = {
			login: login,
			logout: logout,
			getLoggedUser: getLoggedUser,
			getStaffMembers: getStaffMembers,
			getStaffMember: getStaffMember,
			getPatients: getPatients,
			getPatient: getPatient,
			getUser: getUser,
			createPatient: createPatient,
			updatePatient: updatePatient,
			updateStaffMember: updateStaffMember,
			createStaffMember: createStaffMember
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

			config.url = serverUrl + config.url;

			return $http(config);
		}

		/**
		 * Login to the account.
		 *
		 * @param  {Object} data
		 * @return {HttpPromise}
		 */
		function login(data) {
			return request({
				method: 'POST',
				url: '/login',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},
				transformRequest: function(obj) {
					var str = [];
					angular.forEach(obj, function(val, key) {
						str.push(encodeURIComponent(key) + '=' + encodeURIComponent(val));
					});
					return str.join('&');
				},
				data: data
			});
		}

		/**
		 * Logout.
		 *
		 * @return {HttpPromise}
		 */
		function logout() {
			return request({
				method: 'GET',
				url: '/logout'
			})
		}

		/**
		 * Get staff members.
		 *
		 * @return {HttpPromise}
		 */
		function getStaffMembers() {
			return request({
				method: 'GET',
				url: '/api/staff'
			});
		}

		/**
		 * Get staff member by id.
		 *
		 * @param  {number} id
		 * @return {HttpPromise}
		 */
		function getStaffMember(id) {
			return request({
				method: 'GET',
				url: '/api/staff/' + id
			});
		}

        /**
		 * Update existing staff member.
		 *
         * @param {Object} data
         * @returns {HttpPromise}
         */
		function updateStaffMember(data) {
			return request({
				method: 'PUT',
				url: '/api/staff/',
				data: data
			});
		}

        /**
		 * Create staff member.
		 *
         * @param {Object} data
         * @returns {HttpPromise}
         */
		function createStaffMember(data) {
			return request({
				method: 'POST',
				url: '/api/staff/',
				data: data
			});
		}

		/**
		 * Get patients.
		 *
		 * @return {HttpPromise}
		 */
		function getPatients() {
			return request({
				method: 'GET',
				url: '/api/patient'
			});
		}

		/**
		 * Get staff member by id.
		 *
		 * @param  {number} id
		 * @return {HttpPromise}
		 */
		function getPatient(id) {
			return request({
				method: 'GET',
				url: '/api/patient/' + id
			});
		}

        /**
		 * Update existing patient.
		 *
         * @param {Object} data
         * @returns {HttpPromise}
         */
		function updatePatient(data) {
			return request({
				method: 'PUT',
				url: '/api/patient/',
				data: data
			})
		}

        /**
		 * Create patient.
		 *
         * @param {Object} data
         * @returns {HttpPromise}
         */
		function createPatient(data) {
			return request({
				method: 'POST',
				url: '/api/patient/',
				data: data
			});
		}

		/**
		 * /account/ - all accounts GET
		 * /staff/ - GET, POST, PUT, DELETE  /staf/:id - GET
		 * /patient/ -||-
		 * 
		 */

		function getLoggedUser() {
			return request({
				method: 'GET',
				url: '/api/user'
			});
		}

		/**
		 * Get user id.
		 *
		 * @param  {number} id
		 * @return {HttpPromise}
		 */
		function getUser(id) {
			return request({
				method: 'GET',
				url: '/api/user/' + id
			});
		}
	}
]);