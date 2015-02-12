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
                    data.tanggalIjin = new Date(new Date(tanggalIjinFrom[0], tanggalIjinFrom[1] - 1, tanggalIjinFrom[2]));
                    var dariFrom = data.dari.split("-");
                    data.dari = new Date(new Date(dariFrom[0], dariFrom[1] - 1, dariFrom[2]));
                    var sampaiFrom = data.sampai.split("-");
                    data.sampai = new Date(new Date(sampaiFrom[0], sampaiFrom[1] - 1, sampaiFrom[2]));
                    return data;
                }
            }
        });
    });
