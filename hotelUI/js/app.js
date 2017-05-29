var hotelApp = angular.module('hotelApp', ['ng-fusioncharts', 'ngMaterial', 'ngMessages', 'ui.router', 'ngAnimate',
        'ui.bootstrap', 'angularMoment'])
    // principal is a service that tracks the user's identity.
    // calling identity() returns a promise while it does what you need it to do
    // to look up the signed-in user's identity info. for example, it could make an
    // HTTP request to a rest endpoint which returns the user's name, roles, etc.
    // after validating an auth token in a cookie. it will only do this identity lookup
    // once, when the application first runs. you can force re-request it by calling identity(true)
    .factory('principal', ['$q', '$http', '$timeout',
        function ($q, $http, $timeout) {
            var _identity = undefined,
                _authenticated = false;

            return {
                isIdentityResolved: function () {
                    return angular.isDefined(_identity);
                },
                isAuthenticated: function () {
                    return _authenticated;
                },
                isInRole: function (role) {
                    if (!_authenticated || !_identity.roles) return false;

                    return _identity.roles.indexOf(role) != -1;
                },
                isInAnyRole: function (roles) {
                    if (!_authenticated || !_identity.roles) return false;

                    for (var i = 0; i < roles.length; i++) {
                        if (this.isInRole(roles[i])) return true;
                    }

                    return false;
                },
                authenticate: function (identity) {
                    _identity = identity;
                    _authenticated = identity != null;
                },
                identity: function (force) {
                    var deferred = $q.defer();

                    if (force === true) _identity = undefined;

                    // check and see if we have retrieved the identity data from the server. if we have, reuse it by immediately resolving
                    if (angular.isDefined(_identity)) {
                        deferred.resolve(_identity);
                        return deferred.promise;
                    }

                    // otherwise, retrieve the identity data from the server, update the identity object, and then resolve.
                    //                   $http.get('/svc/account/identity', { ignoreErrors: true })
                    //                        .success(function(data) {
                    //                            _identity = data;
                    //                            _authenticated = true;
                    //                            deferred.resolve(_identity);
                    //                        })
                    //                        .error(function () {
                    //                            _identity = null;
                    //                            _authenticated = false;
                    //                            deferred.resolve(_identity);
                    //                        });

                    // for the sake of the demo, fake the lookup by using a timeout to create a valid
                    // fake identity. in reality,  you'll want something more like the $http request
                    // commented out above. in this example, we fake looking up to find the user is
                    // not logged in

                    var self = this;
                    $timeout(function () {
                        self.authenticate(null);
                        deferred.resolve(_identity);
                    }, 1000);
                    return deferred.promise;
                }
            };
        }
    ])
    // authorization service's purpose is to wrap up authorize functionality
    // it basically just checks to see if the principal is authenticated and checks the root state
    // to see if there is a state that needs to be authorized. if so, it does a role check.
    // this is used by the state resolver to make sure when you refresh, hard navigate, or drop onto a
    // route, the app resolves your identity before it does an authorize check. after that,
    // authorize is called from $stateChangeStart to make sure the principal is allowed to change to
    // the desired state
    .factory('authorization', ['$rootScope', '$state', 'principal',
        function ($rootScope, $state, principal) {
            return {
                authorize: function () {
                    return principal.identity()
                        .then(function () {
                            var isAuthenticated = principal.isAuthenticated();
                            if ($rootScope.toState.data.roles && $rootScope.toState.data.roles.length > 0 && !principal.isInAnyRole($rootScope.toState.data.roles)) {
                                if (isAuthenticated) $state.go('accessdenied'); // user is signed in but not authorized for desired state
                                else {
                                    // user is not authenticated. stow the state they wanted before you
                                    // send them to the signin state, so you can return them when you're done
                                    $rootScope.returnToState = $rootScope.toState;
                                    $rootScope.returnToStateParams = $rootScope.toStateParams;
                                    // now, send them to the signin state so they can log in
                                    $state.go('signin');
                                }
                            }
                        });
                }
            };
        }
    ])
    .factory('AuthTokenFactory', function ($window) {
        var store = $window.localStorage;
        var key = 'auth-token';
        return {
            getToken: getToken,
            setToken: setToken
        };

        /* Получить токен из Local Storage */
        function getToken() {
            return store.getItem(key);
        }

        /* Создать или удалить токен из Local Storage */
        function setToken(token) {
            if (token) {
                store.setItem(key, token);
            }
            else {
                store.removeItem(key);
            }
        }
    })
    .factory('UserFactory', function UserFactory($http, AuthTokenFactory, $q) {
        return {
            login: login,
            logout: logout,
            getUser: getUser
        };

        /* Запрос к api для получение токена */
        function login(username, password) {
            return $http({
                url: "http://localhost:8080/hotelWEB/oauth/token?grant_type=password&username=" + username + '&password=' + password,
                method: 'POST',
                headers: {
                    "Authorization": "Basic " + 'bXktdHJ1c3RlZC1jbGllbnQ6c2VjcmV0',
                    "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
                }
            })
                .then(function success(response) {
                    /* При успешном получении токена сохраняем его в Local Storage */
                    AuthTokenFactory.setToken(response.data.access_token);
                    return response;
                });
        }

        /* Выход */
        function logout() {
            /* Удаляем токен из Local Storage */
            AuthTokenFactory.setToken();
        }

        /* Получить инфу об юзере */
        function getUser(username, password) {
            if (AuthTokenFactory.getToken()) {
                return $http.post('http://localhost:8080/hotelWEB/authorization/?access_token=' + AuthTokenFactory.getToken(),
                    {
                        "name": username,
                        "pass": password
                    }
                )
            }
            else {
                return $q.reject({data: 'Нет токена'});
            }
        }
    })
    .controller('SigninCtrl', function SigninCtrl($rootScope, $state, principal, HttpMyService, UserFactory, AuthTokenFactory) {
            var vm = this;
            $rootScope.isUser = false;
            $rootScope.isAdmin = false;
            $rootScope.isLoginPage = false;
            $rootScope.userName = '';
            $rootScope.loginMessage = '';
            vm.user = {};
            vm.log_in = {};


            vm.login = login;
            vm.logout = logout;

            /* Логин */
            function login(username, password) {
                UserFactory.login(vm.userEmail, vm.pass)
                    .then(function success(response) {

                        /* Получаем инфу о юзере */
                        UserFactory.getUser(vm.userEmail, vm.pass)
                            .then(function success(response) {
                                vm.user = response.data;
                            }, handleError);
                        console.log("loaded user--- " + vm.user.name);
                        console.log("check load user---- " +JSON.stringify(HttpMyService.get('/guesttoken/'+'?access_token=' + AuthTokenFactory.getToken())));

                    }, handleError);
            }

            /* Выход */
            function logout() {
                UserFactory.logout();
                vm.user = null;
                principal.authenticate(null);
                $state.go('signin');
                $rootScope.isUser = false;
                $rootScope.isAdmin = false;
                $rootScope.userName = '';
            }

            /* Обработка ошибок */
            function handleError(response) {
                console.log('Error: ' + response.data);
            }


            /***************** old */
            vm.signout = function () {
                principal.authenticate(null);
                $state.go('signin');
                $rootScope.isUser = false;
                $rootScope.isAdmin = false;
                $rootScope.userName = '';
            };
            /***  old version of login*/
            vm.signin = function () {
                vm.log_in.name = vm.userEmail;
                vm.log_in.pass = vm.pass;
                HttpMyService.create("/auth/", vm.log_in)
                    .then(function successCallback(response) {
                        if (response.data.body == "User not found") {
                            $rootScope.loginMessage = response.data.body;
                            alert(response.data.body)
                        } else {
                            principal.authenticate({
                                name: response.data.name,
                                roles: response.data.body
                            });
                            $rootScope.userName = response.data.name;
                            $state.go("orderlist", vm.returnToStateParams);
                            if (response.data.body == "ADMIN") {
                                $rootScope.isUser = false;
                                $rootScope.isAdmin = true;
                            } else {
                                $rootScope.isUser = true;
                                $rootScope.isAdmin = false;
                            }
                            $rootScope.isLoginPage = true;
                        }
                    }, function errorCallback(response) {
                        $rootScope.loginMessage = 'Problem with connection, try later';
                    });

            }
        }
    )

    .config(['$stateProvider', '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise('/');
            $stateProvider.state('site', {
                'abstract': true,
                resolve: {
                    authorize: ['authorization',
                        function (authorization) {
                            return authorization.authorize();
                        }
                    ]
                }
            }).state('home', {
                parent: 'site',
                url: '/',
                data: {
                    roles: ['OPERATOR', 'ADMIN']
                },
                views: {
                    'content@': {
                        templateUrl: 'views/orderlist.html',
                        controller: 'OrderController'
                    }
                }
            }).state('signin', {
                parent: 'site',
                url: '/signin',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'signin.html',
                        controller: 'SigninCtrl'
                    }
                }
            }).state('orderlist', {
                    parent: 'site',
                    url: '/orderlist',
                    data: {
                        roles: ['OPERATOR', 'ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/orderlist.html',
                            controller: 'OrderController'
                        }
                    }
                })
                .state('booking', {
                    parent: 'site',
                    url: '/booking',
                    data: {
                        roles: ['OPERATOR']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/booking.html',
                            controller: 'OrderBookingController'
                        }
                    }
                })
                .state('listguest', {
                    parent: 'site',
                    url: '/listguest',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/guestlist.html',
                            controller: 'GuestController'
                        }
                    }
                })
                .state('roomlist', {
                    parent: 'site',
                    url: '/roomlist',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/roomlist.html',
                            controller: 'RoomController'
                        }
                    }
                })

                .state('servicelist', {
                    parent: 'site',
                    url: '/servicelist',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/servicelist.html',
                            controller: 'ServiceController'
                        }
                    }
                })
                .state('countylist', {
                    parent: 'site',
                    url: '/countylist',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/country.html',
                            controller: 'CountryController'
                        }
                    }
                })
                .state('doclist', {
                    parent: 'site',
                    url: '/doclist',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/docs.html',
                            controller: 'DocController'
                        }
                    }
                })
                .state('news', {
                    parent: 'site',
                    url: '/news',
                    data: {
                        roles: []
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/news.html',
                            controller: 'NewsController'
                        }
                    }
                })

                .state('about', {
                    parent: 'site',
                    url: '/about',
                    data: {
                        roles: []
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/about.html',
                            controller: 'AboutCtrl'
                        }
                    }
                })

                .state('preorder', {
                    parent: 'site',
                    url: '/preorder',
                    data: {
                        roles: ['OPERATOR']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/preorder.html',
                            controller: 'PreorderCtrl'
                        }
                    }
                })

                .state('online', {
                    parent: 'site',
                    url: '/online',
                    data: {
                        roles: []
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/online.html',
                            controller: 'OnlineCtrl'
                        }
                    }
                })

                .state('sheduller', {
                    parent: 'site',
                    url: '/sheduller',
                    data: {
                        roles: ['OPERATOR']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/sheduller.html',
                            controller: 'ShedullerCtrl'
                        }
                    }
                })

                .state('manage', {
                    parent: 'site',
                    url: '/manage',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/manage.html',
                            controller: 'MngCtrl'
                        }
                    }
                })

                .state('stat', {
                    parent: 'site',
                    url: '/stat',
                    data: {
                        roles: ['ADMIN']
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/stat.html',
                            controller: 'StatCtrl'
                        }
                    }
                })
                .state('accessdenied', {
                    parent: 'site',
                    url: '/denied',
                    data: {
                        roles: []
                    },
                    views: {
                        'content@': {
                            templateUrl: 'denied.html'
                        }
                    }
                });
        }
    ])
    .run(['$rootScope', '$state', '$stateParams', 'authorization', 'principal',
        function ($rootScope, $state, $stateParams, authorization, principal) {
            $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
                $rootScope.toState = toState;
                $rootScope.toStateParams = toStateParams;

                if (principal.isIdentityResolved()) authorization.authorize();
            });
        }
    ]);
