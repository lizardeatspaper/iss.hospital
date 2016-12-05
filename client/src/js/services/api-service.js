angular.module('iss.hospital').factory('apiService', [
	'$http',
	'$q',
	function apiServiceFactory($http, $q) {
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
			createStaffMember: createStaffMember,
			deletePatient: deletePatient,
			deleteStaffMember: deleteStaffMember,
			createHistory: createHistory,
			deleteHistory: deleteHistory,
			getHistoryByPatientId: getHistoryByPatientId,
			getHistoryDetails: getHistoryDetails,
			getDepartments: getDepartments,
			createDepartment: createDepartment,
			deleteDepartment: deleteDepartment,
			updateDepartment: updateDepartment
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

			return $http(config).then(function(response) {
				if (typeof response.data === 'string') {
					return $q.reject({status: 403, data: { error: 'Not Authorized', message: 'Please log in.'}});
				} else {
                    return $q.when(response);
                }
			}, function(reason) {
				return $q.reject(reason);
			});
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
				url: '/api/staff/' + data.id,
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
		 * Delete staff member.
		 *
         * @param {number} id
         * @returns {HttpPromise}
         */
		function deleteStaffMember(id) {
			return request({
				method: 'DELETE',
				url: '/api/staff/' + id
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
				url: '/api/patient/' + data.id,
				data: data
			})
		}

        /**
		 * Delete patient.
		 *
         * @param {number} id
         * @returns {HttpPromise}
         */
		function deletePatient(id) {
			return request({
				method: 'DELETE',
				url: '/api/patient/' + id
			});
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
		 * Get history by patient id.
		 *
         * @param {number} id
         * @returns {HttpPromise}
         */
		function getHistoryByPatientId(id) {
			return request({
				method: 'GET',
				url: '/api/history/patient/' + id
			});
		}

        /**
		 * Get history detail.
		 *
         * @param {number} id
         * @returns {HttpPromise}
         */
		function getHistoryDetails(id) {
			return request({
				method: 'GET',
				url: '/api/history/' + id
			});
		}

        /**
		 * Create history.
		 *
         * @param {Object} data
		 * -patientId
		 * -doctorId
		 * -title
		 * -description
         * @returns {HttpPromise}
         */
		function createHistory(data) {
			return request({
				method: 'POST',
				url: '/api/history/',
				data: data
			});
		}

        /**
		 * Delete history.
		 *
         * @param {number} id
         * @returns {HttpPromise}
         */
		function deleteHistory(id) {
			return request({
				method: 'DELETE',
				url: '/api/history/' + id
			});
		}

        /**
		 * Get departments.
		 *
         * @returns {HttpPromise}
         */
		function getDepartments() {
			return request({
				method: 'GET',
				url: '/api/department/'
			});
		}

        /**
		 * Create department.
		 *
         * @param {Object} data
         * @returns {HttpPromise}
         */
		function createDepartment(data) {
			return request({
				method: 'POST',
				url: '/api/department/',
				data: data
			});
		}

        /**
		 * Delete department.
		 *
         * @param {Object} id
         * @returns {HttpPromise}
         */
		function deleteDepartment(id) {
			return request({
				method: 'DELETE',
				url: '/api/department/' + id
			});
		}

        /**
		 * Update department.
		 *
         * @param {number} id
         * @param {Object} data
         * @returns {HttpPromise}
         */
		function updateDepartment(id, data) {
			return request({
				method: 'PUT',
				url: '/api/department/' + id,
				data: data
			});
		}

		/**
		 * /account/ - all accounts GET
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