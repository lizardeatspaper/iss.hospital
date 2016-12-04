angular.module('iss.hospital').controller('TreatHistoryController', [
	'$scope',
	function TreatHistoryController($scope) {
		'use strict';

		// public variables
		$scope.treatHistory = [];

		initialize();

		function initialize() {
			$scope.treatHistory = [
				{
					date: new Date(),
					doctor: 'Mike Straus',
					department: 'Surgery',
					description: 'X-ray'
				},
				{
					date: new Date(),
					doctor: 'Mike Straus',
					department: 'Surgery',
					description: 'Blood test'
				}
			];
		}
	}
]);