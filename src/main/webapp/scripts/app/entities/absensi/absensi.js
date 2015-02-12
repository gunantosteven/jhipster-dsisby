'use strict';

angular.module('dsisbyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('absensi', {
                parent: 'entity',
                url: '/absensi',
                
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/absensi/absensis.html',
                        controller: 'AbsensiController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('absensi');
                        return $translate.refresh();
                    }]
                }
            })
            .state('absensiDetail', {
                parent: 'entity',
                url: '/absensi/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/absensi/absensi-detail.html',
                        controller: 'AbsensiDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('absensi');
                        return $translate.refresh();
                    }]
                }
            });
    });
