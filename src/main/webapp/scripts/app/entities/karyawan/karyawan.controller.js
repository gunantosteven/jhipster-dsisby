'use strict';

angular.module('dsisbyApp')
    .controller('KaryawanController', function ($scope, Karyawan, Ijin) {
        $scope.karyawans = [];
        $scope.ijins = Ijin.query();
        $scope.loadAll = function() {
            Karyawan.query(function(result) {
               $scope.karyawans = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Karyawan.save($scope.karyawan,
                function () {
                    $scope.loadAll();
                    $('#saveKaryawanModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.karyawan = Karyawan.get({id: id});
            $('#saveKaryawanModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.karyawan = Karyawan.get({id: id});
            $('#deleteKaryawanConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Karyawan.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteKaryawanConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.karyawan = {idAbsensi: null, namaLengkap: null, nickname: null, startWorking: null, status: null, phone: null, birthday: null, tempatLahir: null, alamatTinggal: null, namaKeluarga: null, hpKeluarga: null, hubunganKeluarga: null, jumlahAnak: null, id: null};
        };
    })
  .directive('ngBootstrapFix',['$filter', function($filter) {
  return {
    require: 'ngModel',
    priority: 1,
    link: function($scope, $element, $attrs, ngModelCtrl) {
      ngModelCtrl.$parsers.push(function(viewValue) {
        viewValue = $filter('date')(viewValue, 'yyyy-MM-dd');
        return viewValue;
      });
      ngModelCtrl.$render = function() {
        var val = $filter('date')(ngModelCtrl.$viewValue, 'yyyy-MM-dd');
        $element.val(val);
      };
    }
  };
}]);
