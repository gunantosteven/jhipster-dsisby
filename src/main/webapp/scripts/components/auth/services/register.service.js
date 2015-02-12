'use strict';

angular.module('dsisbyApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


