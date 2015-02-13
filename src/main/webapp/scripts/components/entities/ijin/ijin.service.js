'use strict';

angular.module('dsisbyApp')
    .factory('Ijin', function ($resource) {
        return $resource('api/ijins/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    var tanggalIjinFrom = data.tanggalIjin.split("-");
                    data.tanggalIjin = new Date(data.tanggalIjin);
                    var dariFrom = data.dari.split("-");
                    data.dari = new Date(data.dari);
                    var sampaiFrom = data.sampai.split("-");
                    data.sampai = new Date(data.sampai);
                    return data;
                }
            }
        });
    });
