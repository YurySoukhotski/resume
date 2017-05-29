/**
 * Created by yury.soukhotski on 15.04.2017.
 */
hotelApp.controller('OnlineCtrl', ['HttpMyService', 'moment', function (HttpMyService, moment) {

    var ctrl = this;
    ctrl.order = {};
    ctrl.order.dateStart = new Date();
    ctrl.order.dateEnd = new Date();
    ctrl.rooms = [];  //rooms
    ctrl.dateF = {};
    ctrl.startShow = false;
    ctrl.roomShow = true;
    ctrl.guestShow = true;
    ctrl.cardShow = false;
    ctrl.country = {};
    ctrl.countries = [];
    ctrl.selectedIndex = 1;

    ctrl.myInterval = 5000;
    ctrl.noWrapSlides = false;
    ctrl.active = 0;

    var slides = ctrl.slides = [{"image": "img/galery/room/74111.jpg", "text": "", "id": 0},
        {"image": "img/galery/room/74112.jpg", "text": "", "id": 1},
        {"image": "img/galery/room/74113.jpg", "text": "", "id": 2},
        {"image": "img/galery/room/74114.jpg", "text": "", "id": 3}];
    var currIndex = 0;

    HttpMyService.get('/countries/').then(
        function successCallback(response) {
            ctrl.countries = response.data;
            console.log("country ok");
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

    ctrl.selectRoom = function (room) {
        ctrl.guestShow = false;
        ctrl.order.room = room.id;
        ctrl.selectedIndex = 3;
    };

    ctrl.save = function () {
        ctrl.order.dateStart = moment(ctrl.order.dateStart).add(1, "day");
        ctrl.order.dateEnd = moment(ctrl.order.dateEnd).add(1, "day");
        HttpMyService.create('/onlinebooking', ctrl.order).then(function successCallback(response) {
            alert(response.data.body);
            ctrl.selectedIndex = 1;
        }, function errorCallback(response) {
            alert("error");
            ctrl.selectedIndex = 1;
        });

    };
}])
