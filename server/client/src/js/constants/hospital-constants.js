angular.module('iss.hospital').constant('Constants', {
    ROLES: {
        PATIENT: 'PATIENT',
        NURSE: 'NURSE',
        ADMIN: 'ADMIN',
        DOCTOR: 'DOCTOR',
        ALL: 'ALL'
    },
    STATUSES: {
        DECEASED: 'Deceased',
        SICK_HOSPITALIZED: 'Sick(hospitalized)',
        SICK_NOT_HOSPITALIZED: 'Sick(not hospitalized)',
        HEALTHY: 'Healthy'
    }
});