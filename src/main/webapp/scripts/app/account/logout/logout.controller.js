'use strict';

angular.module('dsisbyApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
