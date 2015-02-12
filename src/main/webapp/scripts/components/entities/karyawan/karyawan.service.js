'use strict';

angular.module('dsisbyApp')
    .factory('Karyawan', function ($resource) {
        return $resource('api/karyawans/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    var startWorkingFrom = data.startWorking.split("-");
                    data.startWorking = new Date(new Date(startWorkingFrom[0], startWorkingFrom[1] - 1, startWorkingFrom[2]));
                    var birthdayFrom = data.birthday.split("-");
                    data.birthday = new Date(new Date(birthdayFrom[0], birthdayFrom[1] - 1, birthdayFrom[2]));
                    return data;
                }
            }
        });
    });
