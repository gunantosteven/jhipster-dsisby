'use strict';

angular.module('dsisbyApp')
    .factory('Absensi', function ($resource) {
        return $resource('api/absensis/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
    

