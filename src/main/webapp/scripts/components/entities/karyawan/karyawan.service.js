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
                    data.startWorking = new Date(data.startWorking);
                    var birthdayFrom = data.birthday.split("-");
                    data.birthday = new Date(data.birthday);
                    return data;
                }
            }
        });
    });
