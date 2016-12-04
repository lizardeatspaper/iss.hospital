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

					$scope.menuItems = [];

					$scope.isAvailableForThisUser = isAvailableForThisUser;
					$scope.logout = logout;
					$scope.redirectToState = redirectToState;

					initialize();

					function initialize() {
						identityService.getUserIdentity().then(function(identity) {
							$scope.user = identity;
                            $scope.menuItems = [
                                {
                                    icon: 'fa fa-user',
                                    state: 'hospital.authorized.account',
                                    stateOpts: {id: $scope.user.id, account: $scope.user, isOwner: true},
                                    routeName: 'My account',
                                    available: Constants.ROLES.ALL
                                },
                                {
                                    icon: 'fa fa-history',
                                    state: 'hospital.authorized.medicalHistory',
                                    stateOpts: {patientId: $scope.user.id, isOwner: true},
                                    routeName: 'Medical history',
                                    available: [Constants.ROLES.PATIENT]
                                },
                                {
                                    icon: 'fa fa-users',
                                    state: 'hospital.authorized.usersList',
                                    stateOpts: { type: 'patients' },
                                    routeName: 'Patients list',
                                    available: [Constants.ROLES.DOCTOR, Constants.ROLES.NURSE, Constants.ROLES.ADMIN]
                                },
                                {
                                    icon: 'fa fa-user-md',
                                    state: 'hospital.authorized.usersList',
                                    stateOpts: { type: 'staff' },
                                    routeName: 'Staff members',
                                    available: [Constants.ROLES.DOCTOR, Constants.ROLES.NURSE, Constants.ROLES.ADMIN]
                                }
                            ];
						});
					}

                    /**
                     * Get state object.
                     * @param {string} name
                     */
					function redirectToState(item) {
					    $state.go(item.state, item.stateOpts);
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
						apiService.logout().then(function() {
							$state.go('hospital.authorization.login');
						}, errorHandler);
					}
				}
			]
		};
	}
]);