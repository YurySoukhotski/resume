hotelApp.controller('ServiceController',
    [
        '$scope',
        'HttpMyService',
        '$uibModal',
        function ($scope, HttpMyService, $uibModal) {
            $scope.sortType = 'id'; // значение сортировки по умолчанию
            $scope.sortReverse = false;  // обратная сортривка
            $scope.searchName = '';     // значение поиска по умолчанию
            $scope.activeService;
            $scope.newS = {};
            $scope.services = [];

            $scope.startShow = false;
            $scope.newShow = false;
            $scope.selectedIndex = 0;
            $scope.newS = {};


            HttpMyService.get('/service/').then(
                function successCallback(response) {
                    $scope.services = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });


            $scope.save = function () {
                HttpMyService.create('/service/', $scope.newS)
                    .then(
                        function successCallback(response) {
                        }, function errorCallback(response) {
                            console.log("error");
                        });

                $scope.activeService = null;
            };

            $scope.edit = function (service) {
                $scope.activeService = service;
            };

            $scope.update = function (service) {
                var data = JSON.stringify(service);
                HttpMyService.update('/service/', data)
                    .then(
                        function successCallback(response) {
                        }, function errorCallback(response) {
                            console.log("error");
                        });

                $scope.activeService = null;
            };

            $scope.delete = function (service) {

            };


            /************
             *
             * ********** pagination
             * */
            $scope.currentPage = 0;
            $scope.itemsPerPage = 10;

            $scope.firstPage = function () {
                return $scope.currentPage == 0;
            }
            $scope.lastPage = function () {
                var lastPageNum = Math.ceil($scope.services.length / $scope.itemsPerPage - 1);
                return $scope.currentPage == lastPageNum;
            }

            $scope.numberOfPages = function () {
                return Math.ceil($scope.services.length / $scope.itemsPerPage);
            }
            $scope.startingItem = function () {
                return $scope.currentPage * $scope.itemsPerPage;
            }
            $scope.pageBack = function () {
                $scope.currentPage = $scope.currentPage - 1;
            }
            $scope.pageForward = function () {
                $scope.currentPage = $scope.currentPage + 1;
            }


        }
    ]);