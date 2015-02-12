'use strict';

angular.module('dsisbyApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ijin', {
                parent: 'entity',
                url: '/ijin',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ijin/ijins.html',
                        controller: 'IjinController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ijin');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ijinuser', {
                parent: 'entity',
                url: '/ijin',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ijin/ijinsuser.html',
                        controller: 'IjinController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ijin');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ijinDetail', {
                parent: 'entity',
                url: '/ijin/:id',
                data: {
                    roles: ['ROLE_ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ijin/ijin-detail.html',
                        controller: 'IjinDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ijin');
                        return $translate.refresh();
                    }]
                }
            });
    });
