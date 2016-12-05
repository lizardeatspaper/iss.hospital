angular.module('iss.hospital').config([
	'$urlRouterProvider',
	'$stateProvider',
	function($urlRouterProvider, $stateProvider) {
		'use strict';

		$stateProvider.state('hospital', {
			abstract: true,
			controller: 'MainController',
			template: '<ui-view />'
		});

		$stateProvider.state('hospital.authorization', {
			abstract: true,
			controller: 'AuthorizationController',
			url: '/auth',
			templateUrl: 'authorization/layout.html'
		});

		$stateProvider.state('hospital.authorization.login', {
			controller: 'LoginController',
			url: '/login',
			templateUrl: 'authorization/login.html'
		});

		$stateProvider.state('hospital.authorized', {
			abstract: true,
			controller: 'AuthorizedController',
			url: '/',
			templateUrl: 'authorized/layout.html'
		});

		/**
		 * Page used as my account, patient page, staff member page.
		 */
		$stateProvider.state('hospital.authorized.account', {
			params: {
				account: null,
				isOwner: false
			},
			controller: 'AccountPageController',
			url: 'account/:id',
			templateUrl: 'authorized/account.html'
		});

		$stateProvider.state('hospital.authorized.createAccount', {
			controller: 'CreateAccountController',
			url: 'create-account',
			templateUrl: 'authorized/create-account.html'
		});

		$stateProvider.state('hospital.authorized.medicalHistory', {
			abstract: true,
			url: 'medical-history',
			controller: 'MedicalHistoryController',
			template: '<ui-view />'
		});

		/**
		 * Page used as my treat history, patient treat history.
		 */
		$stateProvider.state('hospital.authorized.medicalHistory.list', {
			controller: 'MedicalHistoryListController',
			url: '/:patientId',
			templateUrl: 'authorized/medical-history.html'
		});

		$stateProvider.state('hospital.authorized.medicalHistory.detail', {
			controller: 'MedicalHistoryDetailController',
			url: 'detail/:id',
			templateUrl: 'authorized/medical-history-detail.html'
		});

		$stateProvider.state('hospital.authorized.medicalHistory.add', {
			params: {
				patientId: null,
				doctorId: null
			},
			controller: 'MedicalHistoryAddController',
			url: 'add',
			templateUrl: 'authorized/medical-history-add.html'
		});

		/**
		 * Page used as patients list and staff members list.
		 */
		$stateProvider.state('hospital.authorized.usersList', {
			controller: 'UsersListController',
			url: 'members/:type',
			templateUrl: 'authorized/users-list.html'
		});

		$urlRouterProvider.otherwise('/auth/login');
	}
]);
