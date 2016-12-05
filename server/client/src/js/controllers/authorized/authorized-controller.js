angular.module('iss.hospital').controller('AuthorizedController', [
	'$scope',
	function AuthorizedController($scope) {
		'use strict';

		// public variables
		$scope.isLeftSidebarOpened = true;

		// public methods
		$scope.toggleSidebar = toggleSidebar;

		function toggleSidebar() {
			$scope.isLeftSidebarOpened = !$scope.isLeftSidebarOpened;
		}
	}
]);
