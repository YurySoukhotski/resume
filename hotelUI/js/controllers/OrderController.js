hotelApp.controller('OrderController',
    function ($scope, $timeout, HttpMyService) {
        $scope.sortType = 'id'; // значение сортировки по умолчанию
        $scope.sortReverse = false;  // обратная сортривка
        $scope.searchName = '';     // значение поиска по умолчанию
        $scope.activeAdd = false;  //selected add service
        $scope.tempService = {};
        $scope.custService = {};
        $scope.listServices = [];
        $scope.startShow = false;
        $scope.infoShow = true;
        $scope.searchShow = true;
        $scope.selectedIndex = 1;

        $scope.isRoomChange = false;
        $scope.isPayCard = false;
        $scope.isRoomServiceCard = false;
        $scope.order = {};
        $scope.orders = [];
        $scope.rooms = [];
        $scope.guests = [];
        $scope.room = {};
        $scope.change = {};
        $scope.spends = [];
        $scope.payments = [];
        $scope.summaSpend = 0;
        $scope.summaPayed = 0;
        $scope.roomServices = [];
        $scope.typeP = ["ERIP", "CARD", "CASH"];
        $scope.typeStatus = ["OPEN", "CLOSED", "BOOKED"];
        $scope.paymentExport = {};
        $scope.config = {};
        $scope.guest = {};

        $scope.isCanClose = true; //  disabled close order button before not payed

        $scope.getOrders = function () {
            HttpMyService.get('/orders/').then(
                function successCallback(response) {
                    $scope.orders = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });

        };

        $scope.getOrders();

        /*****
         * base loader
         * ***************/


        $scope.getConfig = function () {
            HttpMyService.get('/config/')
                .then(
                    function successCallback(response) {
                        $scope.config = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    });
        };

        $scope.getServices = function () {
            HttpMyService.get('/service/')
                .then(
                    function successCallback(response) {
                        $scope.listServices = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    });
        };

        $scope.getGuests = function () {
            HttpMyService.get('/guest/')
                .then(
                    function successCallback(response) {
                        $scope.guests = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    });
        };


        $scope.getRoom = function () {
            HttpMyService.get('/room/').then(
                function successCallback(response) {
                    $scope.rooms = response.data;
               }, function errorCallback(response) {
                    console.log("error");
                });
        };

        $scope.getServiceList = function () {
            HttpMyService.getId('/servicebyorder/', $scope.order.id)
                .then(
                    function successCallback(response) {
                        $scope.roomServices = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    });
        };

        $scope.loadPayment = function () {
            HttpMyService.getId('/payment/', $scope.order.id)
                .then(
                    function successCallback(response) {
                        $scope.payments = response.data;
                    }, function errorCallback(response) {
                        console.log("error");
                    });
        };


        $scope.getConfig();
        $scope.getGuests();
        $scope.getRoom();
        $scope.getServices();
        $scope.loadPayment();


        $scope.findGuest = function (ids) {
            for (var i = 0; i < $scope.guests.length; i++) {
                if ($scope.guests[i].id == ids) {
                    $scope.guest = $scope.guests[i];
                }
            }
        };

        $scope.findRoom = function (ids) {
            for (var i = 0; i < $scope.rooms.length; i++) {
                if ($scope.rooms[i].id == ids) {
                    $scope.room = $scope.rooms[i];
                }
            }
        };


        $scope.selectOrder = function (ord) {
            if ($scope.order.status == 'CLOSED') {
                $scope.isCanClose = true;
            }
            $scope.findGuest($scope.order.idGuest);
            $scope.findRoom($scope.order.idRoom);
            $scope.order = ord;
            $scope.infoShow = false;
            $scope.selectedIndex = 2;
            $scope.findGuest($scope.order.idGuest);
            $scope.findRoom($scope.order.idRoom);

        };


        $scope.guestInfo = function () {
            $scope.selection = "guest";
        };
        $scope.roomInfo = function () {
            $scope.selection = "room";
        };

        $scope.roomServiceInfo = function () {
            $scope.selection = "roomService";
            $scope.getServiceList();
        };

        $scope.paymentInfo = function () {
            $scope.paymentCalculate();
            $scope.selection = "pay";
            $scope.paymentCalculate();
            $scope.reloadSum();
            if (($scope.summNeed == 0 ) && ($scope.order.status != 'CLOSED')) {
                $scope.isCanClose = false;
            }

        };

        $scope.paymentCalculate = function () {
            $scope.getServiceList();
            $scope.loadPayment();
            $scope.summaSpend = 0;
            $scope.summaPayed = 0;
            $scope.spends = [];
            var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
            var firstDate = new Date($scope.order.dateSt);
            var secondDate = new Date($scope.order.dateEnd);
            var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime()) / (oneDay)));
            $scope.costLivin = diffDays * $scope.room.price;
            for (var i = 0; i < $scope.roomServices.length; i++) {
                $scope.spends.push({
                    name: $scope.roomServices[i].serviceName,
                    sum: $scope.roomServices[i].sum
                });
                $scope.summaSpend = $scope.summaSpend + $scope.roomServices[i].sum;
            }
            ;
            $scope.spends.push({name: "Living in room", sum: $scope.costLivin});
            $scope.summaSpend = $scope.summaSpend + $scope.costLivin;
            if ($scope.guest.amount >= $scope.config.discountLevelTen){
                $scope.summaSpend=Math.round($scope.summaSpend*0.9)
            } else if(($scope.guest.amount >= $scope.config.discountLevelFive)&&($scope.guest.amount < $scope.config.discountLevelTen)){
                $scope.summaSpend=Math.round($scope.summaSpend*0.95)
            }


            for (var j = 0; j < $scope.payments.length; j++) {
                $scope.summaPayed = $scope.summaPayed + $scope.payments[j].summ;
            }
        };

        $scope.changeRoom = function () {
            $scope.room = $scope.change.room;
        };

        $scope.reloadSum = function () {
            $scope.summNeed = $scope.summaSpend - $scope.summaPayed;
        };

        $scope.addMoney = function () {
            $scope.paymentExport.order = $scope.order.id;
            $scope.paymentExport.comments = $scope.paymentExport.paymentType;
            $scope.paymentExport.datePayment = new Date();
            HttpMyService.create('/payment/', $scope.paymentExport)
                .then(function successCallback(response) {
                    if (response.data.body == 'Saved') {
                        $scope.guest.amount = $scope.paymentExport.summ + $scope.guest.amount;
                        $scope.addGuestMoney();
                    }
                }, function errorCallback(response) {
                    console.log("error");
                });
            $scope.paymentCalculate();
            $scope.reloadSum();
        };

        $scope.addGuestMoney = function () {
            console.log("guest update " + JSON.stringify($scope.guest));
            HttpMyService.update('/guest/', $scope.guest)
                .then(function successCallback(response) {
                }, function errorCallback(response) {
                    console.log("error");
                });
        };

        $scope.addService = function () {
            $scope.custService.orderId = $scope.order.id;
            $scope.custService.serviceId = $scope.change.service.id;
            $scope.custService.count = $scope.change.service.count;
            $scope.custService.service_name = $scope.change.service.sName + "/" + $scope.change.service.description;
            $scope.custService.current_sum = $scope.change.service.count * $scope.change.service.sPrice;

            HttpMyService.update('/orders/', $scope.custService)
                .then(function successCallback(response) {
                    if (response.data.body == "Added") {
                        $scope.roomServiceInfo();
                        $scope.change.service.count = 0;
                        $scope.change.service = {};
                    }
                }, function errorCallback(response) {
                    console.log("error");
                });
        };

        $scope.closeOrder = function () {
            HttpMyService.getId('/closeorder/', $scope.order.id)
                .then(
                    function successCallback(response) {
                        $scope.getOrders();
                        $scope.selectedIndex = 1;
                    }, function errorCallback(response) {
                        console.log("error closed");
                    });
        };

        /******************** PAGINATION */
        $scope.currentPage = 0;
        $scope.itemsPerPage = 5;

        $scope.firstPage = function () {
            return $scope.currentPage == 0;
        }
        $scope.lastPage = function () {
            var lastPageNum = Math.ceil($scope.orders.length / $scope.itemsPerPage - 1);
            return $scope.currentPage == lastPageNum;
        }

        $scope.numberOfPages = function () {
            return Math.ceil($scope.orders.length / $scope.itemsPerPage);
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