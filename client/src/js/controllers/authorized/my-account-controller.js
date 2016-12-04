angular.module('iss.hospital').controller('AccountPageController', [
	'$scope',
	function AccountPageController($scope) {
		'use strict';

		$scope.account = null;

		$scope.isAuthorized = isAuthorized;

		initialize();

		function initialize() {
			if (!$scope.account) {
				$scope.account = {
					firstname: 'John',
					lastname: 'Doe',
					birthDate: new Date(),
					role: 'patient',
					tel: '+420 773 642 621',
					address: 'Brno, Sportovni 5'
				};
			}
		}

		function isAuthorized() {
			return $scope.account && $scope.account.role !== 'patient';
		}
	}
]);
