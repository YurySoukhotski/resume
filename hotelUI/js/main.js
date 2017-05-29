(function() {
  'use strict';

  angular
    .module('hotelApp', [], function config($httpProvider) {
      $httpProvider.interceptors.push('AuthInterceptor'); // Добавляем созданный interceptor
    })
   })();