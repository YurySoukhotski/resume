hotelApp.controller('CountryController',
    [
        '$scope',
        'HttpMyService',
       
        function ($scope, HttpMyService) {
            $scope.sortType = 'id'; // значение сортировки по умолчанию
            $scope.sortReverse = false;  // обратная сортривка
            $scope.searchName = '';     // значение поиска по умолчанию
            $scope.activeCountry;
            $scope.newS = {};
            $scope.countries=[];

            $scope.startShow = false;
            $scope.newShow = false;
            $scope.selectedIndex = 0;
            $scope.newS={};


            HttpMyService.get('/countries').then(
                function successCallback(response) {
                    $scope.countries = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });


            $scope.save = function () {
                HttpMyService.create('/countries', $scope.newS)
                    .then(
                        function successCallback(response) {
                        }, function errorCallback(response) {
                            console.log("error");
                        });

                $scope.activeCountry = null;
            };

            $scope.edit = function (country) {
                $scope.activeCountry = country;
            };

            $scope.update = function (country) {
                var data = JSON.stringify(country);
                HttpMyService.update('/countries', data)
                    .then(
                        function successCallback(response) {
                        }, function errorCallback(response) {
                            console.log("error");
                        });

                $scope.activeCountry = null;
            };

            $scope.delete = function (country) {

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
                var lastPageNum = Math.ceil($scope.countries.length / $scope.itemsPerPage - 1);
                return $scope.currentPage == lastPageNum;
            }

            $scope.numberOfPages = function () {
                return Math.ceil($scope.countries.length / $scope.itemsPerPage);
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