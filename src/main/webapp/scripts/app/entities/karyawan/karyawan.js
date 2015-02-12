'use strict';

angular.module('dsisbyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('karyawan', {
                parent: 'entity',
                url: '/karyawan',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/karyawan/karyawans.html',
                        controller: 'KaryawanController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('karyawan');
                        return $translate.refresh();
                    }]
                }
            })
            .state('karyawanDetail', {
                parent: 'entity',
                url: '/karyawan/:id',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/karyawan/karyawan-detail.html',
                        controller: 'KaryawanDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('karyawan');
                        return $translate.refresh();
                    }]
                }
            });
    });
