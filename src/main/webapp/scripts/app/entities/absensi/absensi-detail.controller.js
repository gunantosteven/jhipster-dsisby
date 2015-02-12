'use strict';

angular.module('dsisbyApp')
    .controller('AbsensiDetailController', function ($scope, $stateParams, Absensi) {
        $scope.absensi = {};
        $scope.load = function (id) {
            Absensi.get({id: id}, function(result) {
              $scope.absensi = result;
            });
        };
        $scope.load($stateParams.id);
    });
