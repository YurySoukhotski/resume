angular.module('hotelApp').factory('AuthInterceptor', function(AuthTokenFactory) {
      return {
        request: addToken
      };

      /* Добавить к запросу заголовок с токеном */
      function addToken(config) {
        var token = AuthTokenFactory.getToken();

        if(token) {
          config.headers = config.headers || {};
          config.headers.Authorization = "Basic " + 'bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0';
        }
        return config;
      }
    });