angular.module('hotelApp'
).factory('HttpMyService', function($http, Config) {
	var BASE_URL = Config.APPURL;
	return {
		get: function(addUrl) {
			return $http.get(BASE_URL+addUrl);
		},
		getId: function(addUrl, parametrs) {
			return $http.get(BASE_URL+addUrl+parametrs);
		},
		create: function(addUrl, data) {
			return $http.post(BASE_URL+addUrl, data);
		},
		update: function(addUrl, data) {
			return $http.put(BASE_URL+addUrl, data);
		},
		delete: function(addUrl, ind) {
			return $http.delete(BASE_URL+addUrl+ind);
		}
	};
});