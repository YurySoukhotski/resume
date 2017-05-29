hotelApp.controller('GuestController',
    function ($scope, HttpMyService) {
        $scope.sortType = 'id'; // значение сортировки по умолчанию
        $scope.sortReverse = false;  // обратная сортривка
        $scope.searchName = '';     // значение поиска по умолчанию

        $scope.startShow = false;
        $scope.cardShow = true;
        $scope.histShow = false;
        $scope.newShow = false;
        $scope.selectedIndex = 0;
        $scope.isEdit = true;


        /*data*/
        $scope.guests = [];
        $scope.guestNew = {};
        $scope.documents = [];
        $scope.countries = [];

        HttpMyService.get('/guest/').then(
            function successCallback(response) {
                $scope.guests = response.data;
                console.log("ok");
            }, function errorCallback(response) {
                console.log("error");
            });

        HttpMyService.get('/documents/').then(
            function successCallback(response) {
                $scope.documents = response.data;
                console.log("ok");
            }, function errorCallback(response) {
                console.log("error");
            });


        HttpMyService.get('/countries/').then(
            function successCallback(response) {
                $scope.countries = response.data;
                console.log("country ok");
            }, function errorCallback(response) {
                console.log("error");
            });


        $scope.refreshGuest = function () {

            HttpMyService.get('/guest/').then(
                function successCallback(response) {
                    $scope.guests = response.data;
                    console.log("ok");
                }, function errorCallback(response) {
                    console.log("error");
                });

        };


        /*******************
         *
         * select
         */

        $scope.selectGuest = function (gg) {
            $scope.guest = gg;
            $scope.cardShow = false;
            $scope.selectedIndex = 1;

        }

        /********
         *
         *
         * work with card
         *
         *
         */

        $scope.saveGuest = function () {
            console.log('updated guest is: ' + JSON.stringify($scope.guest));
            HttpMyService.update('/guest/', $scope.guest).then(function successCallback(response) {
                alert(response.data.body);
            }, function errorCallback(response) {
                alert("error");
            });
        };

        $scope.createGuest = function () {
            console.log('new guest is: ' + JSON.stringify($scope.guestNew));
            HttpMyService.create('/guest/', $scope.guestNew).then(function successCallback(response) {
                alert(response.data.body);
                if (response.data.body == "Added") {
                    $scope.guestNew = {};
                }
            }, function errorCallback(response) {
                alert("error");
            });
        };

        $scope.historyGuest = function () {
            HttpMyService.getId('/guesthistory/', $scope.guest.id)
                .then(
                    function successCallback(response) {
                        $scope.histories = response.data;
                        console.log("load guest history");
                    }, function errorCallback(response) {
                        console.log("error");
                    });

            $scope.histShow = true;


        };


        /*********************** pagination */
        $scope.currentPage = 0;
        $scope.itemsPerPage = 10;

        $scope.firstPage = function () {
            return $scope.currentPage == 0;
        }
        $scope.lastPage = function () {
            var lastPageNum = Math.ceil($scope.guests.length / $scope.itemsPerPage - 1);
            return $scope.currentPage == lastPageNum;
        }

        $scope.numberOfPages = function () {
            return Math.ceil($scope.guests.length / $scope.itemsPerPage);
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
)
    