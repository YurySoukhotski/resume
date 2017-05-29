hotelApp.controller('OrderBookingController', ['HttpMyService', function (HttpMyService) {
    var ctrl = this;
    ctrl.order = {};
    ctrl.rooms = [];  //rooms
    ctrl.guests = [];  //guests
    ctrl.guest = {};
    ctrl.dateF = {};

    ctrl.startShow = false;
    ctrl.roomShow = true;
    ctrl.guestShow = true;
    ctrl.bookingShow = true;
    ctrl.searchGuestShow = true;
    ctrl.searchName = '';
    ctrl.isDisabled = true;

    ctrl.countries = [];
    ctrl.selectedIndex = 1;

    HttpMyService.get('/documents/').then(
        function successCallback(response) {
            ctrl.documents = response.data;
        }, function errorCallback(response) {
            console.log("error");
        });


    ctrl.loadGuest = function () {
        HttpMyService.get('/guest/').then(
            function successCallback(response) {
                ctrl.guests = response.data;
            }, function errorCallback(response) {
                console.log("error");
            })
    };

    ctrl.loadGuest();

    HttpMyService.get('/countries/').then(
        function successCallback(response) {
            ctrl.countries = response.data;

        }, function errorCallback(response) {
            console.log("error");
        });

    ctrl.find = function () {
        ctrl.dateF = moment(ctrl.order.dateStart).format("YYYY-MM-DD");
        HttpMyService.getId('/freeroom/', ctrl.dateF)
            .then(
                function successCallback(response) {
                    ctrl.rooms = response.data;
                }, function errorCallback(response) {
                    console.log("error");
                });
        ctrl.roomShow = false;
        ctrl.selectedIndex = 2;
    };

    ctrl.selectRoom = function (r) {
        ctrl.room = r;
        ctrl.bookingShow = false;
        ctrl.order.idRoom = ctrl.room.id;
        ctrl.selectedIndex = 3;
        var oneDay = 24 * 60 * 60 * 1000; // hours*minutes*seconds*milliseconds
        var firstDate = new Date(ctrl.order.dateSt);
        var secondDate = new Date(ctrl.order.dateEnd);
        var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime()) / (oneDay)));
        ctrl.cost = diffDays * ctrl.room.price;
        ctrl.order.dateSt = firstDate;
        ctrl.order.dateEnd = secondDate;

    };


    ctrl.selectGuest = function (g) {
        ctrl.order.idGuest = g.id;
        ctrl.guest = g;
        ctrl.searchGuestShow = true;
        ctrl.isDisabled = false;


    };


    ctrl.saveGuest = function () {

        ctrl.guest.amount = 0;
        HttpMyService.create('/guest/', ctrl.guest).then(function successCallback(response) {
            alert(response.data.body + ". You can find this user from base and add new order");
            ctrl.loadGuest();
            ctrl.searchGuestShow = true;
        }, function errorCallback(response) {
            alert("error");
        });
    };

    ctrl.saveOrder = function () {
        HttpMyService.create('/orders/', ctrl.order).then(function successCallback(response) {
            alert(response.data.body);
        }, function errorCallback(response) {
            alert("error");
        });

    };

    ctrl.myInterval = 5000;
    ctrl.noWrapSlides = false;
    ctrl.active = 0;

    var slides = ctrl.slides = [{"image": "img/galery/room/74111.jpg", "text": "", "id": 0},
        {"image": "img/galery/room/74112.jpg", "text": "", "id": 1},
        {"image": "img/galery/room/74113.jpg", "text": "", "id": 2},
        {"image": "img/galery/room/74114.jpg", "text": "", "id": 3}];
    var currIndex = 0;

    /************************/
    ctrl.searchName = '';

    ctrl.selected = null,
        ctrl.previous = null;


}])




