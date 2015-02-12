'use strict';

angular.module('dsisbyApp')
    .controller('IjinController', function ($scope, Ijin, Karyawan) {
        $scope.ijins = [];
        $scope.karyawans = Karyawan.query();
        $scope.loadAll = function() {
            Ijin.query(function(result) {
               $scope.ijins = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Ijin.save($scope.ijin,
                function () {
                    $scope.loadAll();
                    $('#saveIjinModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.ijin = Ijin.get({id: id});
            $('#saveIjinModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.ijin = Ijin.get({id: id});
            $('#deleteIjinConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Ijin.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteIjinConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.ijin = {tanggalIjin: null, dari: null, sampai: null, alasan: null, id: null};
        };
    });
