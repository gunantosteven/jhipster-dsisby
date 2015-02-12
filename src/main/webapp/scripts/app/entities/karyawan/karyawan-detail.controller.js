'use strict';

angular.module('dsisbyApp')
    .controller('KaryawanDetailController', function ($scope, $stateParams, Karyawan, Ijin) {
        $scope.karyawan = {};
        $scope.load = function (id) {
            Karyawan.get({id: id}, function(result) {
              $scope.karyawan = result;
            });
        };
        $scope.load($stateParams.id);
    });
