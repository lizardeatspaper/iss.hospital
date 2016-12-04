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

		/**
		 * Page used as my treat history, patient treat history.
		 */
		$stateProvider.state('hospital.authorized.treatHistory', {
			controller: 'TreatHistoryController',
			url: 'treat-history/:id',
			templateUrl: 'authorized/treat-history.html'
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
