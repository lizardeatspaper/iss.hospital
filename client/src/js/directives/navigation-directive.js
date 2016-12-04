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
				'apiService',
				'errorHandler',
				'identityService',
				'Constants',
				function NavigationDirectiveController($scope, $state, apiService, errorHandler, identityService, Constants) {

					$scope.user = null;

					$scope.menuItems = [
						{
							icon: 'fa fa-user',
							href: $state.href('hospital.authorized.account', { id: 1}),
							routeName: 'My account',
							available: Constants.ROLES.ALL
						},
						{
							icon: 'fa fa-history',
							href: $state.href('hospital.authorized.treatHistory', { id: 1}),
							routeName: 'Treat history',
							available: Constants.ROLES.PATIENT
						},
						{
							icon: 'fa fa-users',
							href: $state.href('hospital.authorized.usersList', { type: 'patients'}),
							routeName: 'Patients list',
							available: [Constants.ROLES.DOCTOR, Constants.ROLES.NURSE, Constants.ROLES.ADMIN]
						},
						{
							icon: 'fa fa-user-md',
							href: $state.href('hospital.authorized.usersList', { type: 'staff'}),
							routeName: 'Staff members',
							available: [Constants.ROLES.DOCTOR, Constants.ROLES.NURSE, Constants.ROLES.ADMIN]
						}
					];

					$scope.isAvailableForThisUser = isAvailableForThisUser;
					$scope.logout = logout;

					initialize();

					function initialize() {
						identityService.getUserIdentity().then(function(identity) {
							$scope.user = identity;
						});
					}

                    /**
                     * Check if this menu item should be shown for currently logged user.
                     * @param {Object} item
                     * @return {Boolean}
                     */
					function isAvailableForThisUser(item) {
						return item.available === Constants.ROLES.ALL || $scope.user && item.available.indexOf($scope.user.role) >= 0;
					}

					function logout() {
						apiService.logout(function() {
							$state.go('hospital.authorization.login');
						}, errorHandler);
					}
				}
			]
		};
	}
]);