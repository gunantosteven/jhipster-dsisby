'use strict';

angular.module('dsisbyApp')
    .controller('IjinDetailController', function ($scope, $stateParams, Ijin, Karyawan) {
        $scope.ijin = {};
        $scope.load = function (id) {
            Ijin.get({id: id}, function(result) {
              $scope.ijin = result;
            });
        };
        $scope.load($stateParams.id);
    });
