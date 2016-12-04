angular.module('iss.hospital').controller('UsersListController', [
	'$scope',
	'$state',
	'$stateParams',
	function UsersListController($scope, $state, $stateParams) {
		'use strict';

		$scope.users = [];
		$scope.pageHeader = $stateParams === 'staff' ? 'Staff members' : 'Patients';

		$scope.goToDetails = goToDetails;

		initialize();

		function initialize() {
			switch ($stateParams.type) {
				case 'staff':
					loadStaffMembers();
					break;
				case 'patients':
					loadPatients();
					break;
				default:
					$state.go('hospital.authorized.usersList', {type: 'patients'});
					break;
			}
		}

		function loadPatients() {
			$scope.users = [
				{
					id: 0,
					firstname: 'John',
					lastname: 'Doe',
					doctor: 'Mike Straus',
					role: 'patient',
					status: 'Sick'
				},
				{
					id: 1,
					firstname: 'John',
					lastname: 'Doe',
					doctor: 'Mike Straus',
					role: 'patient',
					status: 'Sick'
				},
				{
					id: 2,
					firstname: 'John',
					lastname: 'Doe',
					doctor: 'Mike Straus',
					role: 'patient',
					status: 'Sick'
				}
			];
		}

		function loadStaffMembers() {
			$scope.users = [
				{
					id: 3,
					firstname: 'Mike',
					lastname: 'Straus',
					department: 'Surgery',
					role: 'doctor'
				},
				{
					id: 4,
					firstname: 'Mike',
					lastname: 'Straus',
					department: 'Surgery',
					role: 'doctor'
				},
				{
					id: 5,
					firstname: 'Mike',
					lastname: 'Straus',
					department: 'Surgery',
					role: 'doctor'
				}
			];
		}

		function goToDetails(user) {
			$state.go('hospital.authorized.account', {id: user.id});
		}
	}
]);
