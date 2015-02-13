'use strict';

angular.module('dsisbyApp')
    .config( [
        '$compileProvider',
        function( $compileProvider )
        {   
            $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|data):/);
            // Angular before v1.2 uses $compileProvider.urlSanitizationWhitelist(...)
        }
    ])
    .controller('AbsensiController', function ($scope, Absensi, $http) {
        $scope.absensis = [];
        $scope.loadAll = function() {
            Absensi.query(function(result) {
               $scope.absensis = result;
            });
        };
        $scope.loadAll();

//        $scope.create = function () {
//            Absensi.save($scope.absensi,
//                function () {
//                    $scope.loadAll();
//                    $('#saveAbsensiModal').modal('hide');
//                    $scope.clear();
//                });
//        };

                  
        
        $scope.create = function () {
            var formData=new FormData();
            formData.append("file",file.files[0]);
            formData.append("namaFile", document.getElementById('namaFile').value);
            $http.post('/api/absensis', formData, {
                transformRequest: function(data, headersGetterFunction) {
                    
                    return data;
                },
                headers: { 'Content-Type': undefined }
                }).success(function(data, status) {                       
                    alert("Success ... " + status);
                    $scope.loadAll();
                    $('#saveAbsensiModal').modal('hide');
                    $scope.clear();
                }).error(function(data, status) {
                    alert("Error ... " + status);
                });
            };

        $scope.update = function (id) {
            $scope.absensi = Absensi.get({id: id});
            $('#saveAbsensiModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.absensi = Absensi.get({id: id});
            $('#deleteAbsensiConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Absensi.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteAbsensiConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.absensi = {namaFile: null, id: null, file: null};
        };
    });
