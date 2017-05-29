hotelApp.controller('RoomController',
    function ($scope, HttpMyService, Config) {
        $scope.sortType = 'number'; // значение сортировки по умолчанию
        $scope.sortReverse = false;  // обратная сортривка
        $scope.searchNumber = '';     // значение поиска по умолчанию
        $scope.startShow = false;
        $scope.cardShow = true;
        $scope.histShow = false;
        $scope.newShow = false;
        $scope.selectedIndex = 0;
        $scope.isEdit = true;
        /****
         *
         * data
         *
         * @type {Array}
         */
        $scope.room = {};
        $scope.roomN = {};
        $scope.rooms = [];
        $scope.roomStatus = Config.ROOMSTATUS;
        $scope.roomStandart = Config.ROOMSTANDART;

        HttpMyService.get('/room/').then(
            function successCallback(response) {
                $scope.rooms = response.data;
            }, function errorCallback(response) {
                console.log("error");
            });
        /*******************
         * select
         */
        $scope.selectRoom = function (rr) {
            $scope.room = rr;
            $scope.cardShow = false;
            $scope.selectedIndex = 1;
        };
        /***
         * action
         *
         */
        $scope.historyRoom = function () {
            $scope.histShow = true;
            HttpMyService.getId('/lastguestroom/', $scope.room.id)
                .then(
                    function successCallback(response) {
                        $scope.histories = response.data;
                   }, function errorCallback(response) {
                        console.log("error");
                    });
        };
        $scope.createRoom = function () {
            console.log('new room is: ' + JSON.stringify($scope.roomN));
            HttpMyService.create('/room/', $scope.roomN).then(function successCallback(response) {
                alert(response.data.body);
                if (response.data.body == "Added") {
                    $scope.roomsN = {};
                }
            }, function errorCallback(response) {
                alert("error");
            });

        };
        $scope.updateRoom = function () {

            HttpMyService.update('/room/', $scope.room).then(
                function successCallback(response) {
                }, function errorCallback(response) {
                    console.log("error");
                });

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
            var lastPageNum = Math.ceil($scope.rooms.length / $scope.itemsPerPage - 1);
            return $scope.currentPage == lastPageNum;
        }

        $scope.numberOfPages = function () {
            return Math.ceil($scope.rooms.length / $scope.itemsPerPage);
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