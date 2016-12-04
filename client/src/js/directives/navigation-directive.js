angular.module('iss.hospital').directive('navigation', [
	function navigationDirectiveFactory() {
		'use strict';

		return {
			restrict: 'E',
			scope: {
				toggleSidebar: '='
			},
			templateUrl: 'authorized/directives/navigation-directive.html',
			controller: [
				'$scope',
				'$state',
				function NavigationDirectiveController($scope, $state) {

					$scope.user = 'admin';

					$scope.menuItems = [
						{
							icon: 'fa fa-user',
							href: $state.href('hospital.authorized.account', { id: 1}),
							routeName: 'My account',
							available: 'all'
						},
						{
							icon: 'fa fa-history',
							href: $state.href('hospital.authorized.treatHistory', { id: 1}),
							routeName: 'Treat history',
							available: 'all'
						},
						{
							icon: 'fa fa-users',
							href: $state.href('hospital.authorized.usersList', { type: 'patients'}),
							routeName: 'Patients list',
							available: ['doctor', 'nurse', 'admin']
						},
						{
							icon: 'fa fa-user-md',
							href: $state.href('hospital.authorized.usersList', { type: 'staff'}),
							routeName: 'Staff members',
							available: ['doctor', 'admin']
						}
					];

					$scope.isAvailableForThisUser = isAvailableForThisUser;

					function isAvailableForThisUser(item) {
						return item.available === 'all' || item.available.indexOf($scope.user) >= 0;
					}
				}
			]
		};
	}
]);