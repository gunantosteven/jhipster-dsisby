'use strict';

angular.module('dsisbyApp')
    .controller('IjinController', function ($scope, Ijin, Karyawan, $http) {
        $scope.ijins = [];
        $scope.karyawans = Karyawan.query();
        $http.get('/api/karyawansidandname').
            success(function(data) {
                data = angular.fromJson(data);
                $scope.karyawansidandname = data;
            });
            
            
            
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
//            $http.get('/api/ijins/' + id).
//            success(function(data) {
//                data = angular.fromJson(data);
//                var tanggalIjinFrom = data.tanggalIjin.split("-");
//                data.tanggalIjin = new Date(data.tanggalIjin);
//                var dariFrom = data.dari.split("-");
//                data.dari = new Date(data.dari);
//                var sampaiFrom = data.sampai.split("-");
//                data.sampai = new Date(data.sampai);
//                $scope.ijin = data;
//            });
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
