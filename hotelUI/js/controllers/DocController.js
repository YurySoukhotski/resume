hotelApp.controller('DocController',
    [
        '$scope',
        'HttpMyService',
        function ($scope, HttpMyService) {
            $scope.sortType = 'id'; // значение сортировки по умолчанию
            $scope.sortReverse = false;  // обратная сортривка
            $scope.searchName = '';     // значение поиска по умолчанию
            $scope.activeDoc;
            $scope.newS = {};
            $scope.docs = [];

            $scope.startShow = false;
            $scope.newShow = false;
            $scope.selectedIndex = 0;
            $scope.newS = {};


            HttpMyService.get('/documents').then(
                function successCallback(response) {
                    $scope.docs = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });


            $scope.save = function () {
                HttpMyService.create('/documents', $scope.newS)
                    .then(
                        function successCallback(response) {
                        }, function errorCallback(response) {
                            console.log("error");
                        });

                $scope.activeDoc = null;
            };

            $scope.edit = function (doc) {
                $scope.activeDoc = doc;
            };

            $scope.update = function (doc) {
                var data = JSON.stringify(doc);
                HttpMyService.update('/documents', data)
                    .then(
                        function successCallback(response) {
                        }, function errorCallback(response) {
                            console.log("error");
                        });

                $scope.activeDoc = null;
            };

            $scope.delete = function (doc) {

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
                var lastPageNum = Math.ceil($scope.docs.length / $scope.itemsPerPage - 1);
                return $scope.currentPage == lastPageNum;
            }

            $scope.numberOfPages = function () {
                return Math.ceil($scope.docs.length / $scope.itemsPerPage);
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