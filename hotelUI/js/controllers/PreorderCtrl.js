hotelApp
    .controller('PreorderCtrl', ['HttpMyService', '$uibModal', '$scope', '$timeout', '$mdBottomSheet', '$mdToast',
        function (HttpMyService, $uibModal, $scope, $timeout, $mdBottomSheet, $mdToast) {
            $scope.preorder = {};
            $scope.preorders = {};
            $scope.order = {};
            $scope.guest = {};
            $scope.document = {};
            $scope.country = {};
            $scope.room = {};
            $scope.rooms = [];
            $scope.startShow = false;
            $scope.bookingShow = true;
            $scope.searchGuestShow = true;
            $scope.isDisabled = true;
            $scope.isSaveDisabled = false;
            $scope.isSaveGuestDisabled = true;
            $scope.selectedIndex = 1;


            $scope.loadGuest = function () {
                HttpMyService.get('/guest/').then(
                    function successCallback(response) {
                        $scope.guests = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    })
            };

            $scope.loadPreorder = function () {
                HttpMyService.get('/preorder/').then(
                    function successCallback(response) {
                        $scope.preorders = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    })
            };
            $scope.loadGuest();
            $scope.loadPreorder();

            HttpMyService.get('/documents/').then(
                function successCallback(response) {
                    $scope.documents = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });

            HttpMyService.get('/room/').then(
                function successCallback(response) {
                    $scope.rooms = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });


            HttpMyService.get('/countries/').then(
                function successCallback(response) {
                    $scope.countries = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });


            $scope.delete = function (pr) {
                HttpMyService.delete('/preorder/', pr.id).then(
                    function successCallback(response) {
                    }, function errorCallback(response) {
                        console.log("error");
                    });
            }

            $scope.findRoom = function (ids) {
                for (var i = 0; i < $scope.rooms.length; i++) {
                    console.log(ids + '- room i-' + JSON.stringify($scope.rooms[i]));
                    if ($scope.rooms[i].id == ids) {
                        $scope.room = $scope.rooms[i];
                    }
                }
                $scope.order.idRoom = $scope.room.id;
            };

            $scope.accept = function (pr) {
                $scope.bookingShow = false;
                $scope.preorder = pr;
                $scope.selectedIndex = 2;
                $scope.findRoom($scope.preorder.room);
                var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
                var firstDate = new Date($scope.preorder.dateStart);
                var secondDate = new Date($scope.preorder.dateEnd);
                var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime()) / (oneDay)));
                $scope.cost = diffDays * $scope.room.price;
                $scope.order.dateSt = firstDate;
                $scope.order.dateEnd = secondDate;
            };

            $scope.selectGuest = function (g) {
                $scope.searchGuestShow = true;
                $scope.guest = g;
                $scope.preorder.nameGuest = $scope.guest.name;
                $scope.preorder.surnameGuest = $scope.guest.surname;
                $scope.preorder.phoneGuest = $scope.guest.phone;
                $scope.preorder.emailGuest = $scope.guest.email;
                $scope.isDisabled = false;
                $scope.order.idGuest = $scope.guest.id;
            };

            $scope.saveOrder = function () {
                HttpMyService.create('/orders/', $scope.order).then(function successCallback(response) {
                    alert(response.data.body);
                    if (response.data.body == 'Added') {
                        $scope.delete($scope.preorder);
                        $scope.loadPreorder();
                        $scope.selectedIndex = 1;
                    }
                }, function errorCallback(response) {
                    alert("error");
                });
            };

            $scope.saveGuest = function () {

                $scope.guest.name = $scope.preorder.nameGuest;
                $scope.guest.surname = $scope.preorder.surnameGuest;
                $scope.guest.docFileName = "foto.jpg";
                $scope.guest.phone = $scope.preorder.phoneGuest;
                $scope.guest.email = $scope.preorder.emailGuest;
                $scope.guest.amount = 0;
                $scope.guest.document = $scope.document;
                $scope.guest.country = $scope.country;

                console.log('new guest is: ' + JSON.stringify($scope.guest));
                HttpMyService.create('/guest/', $scope.guest).then(function successCallback(response) {
                    alert(response.data.body + " You can find this user from base and add new order");
                    $scope.loadGuest();
                    $scope.searchGuestShow = true;
                }, function errorCallback(response) {
                    alert("error");
                });
            };


            $scope.currentPage = 0;
            $scope.itemsPerPage = 4;

            $scope.firstPage = function () {
                return $scope.currentPage == 0;
            }
            $scope.lastPage = function () {
                var lastPageNum = Math.ceil($scope.preorders.length / $scope.itemsPerPage - 1);
                return $scope.currentPage == lastPageNum;
            }

            $scope.numberOfPages = function () {
                return Math.ceil($scope.preorders.length / $scope.itemsPerPage);
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

        }]);