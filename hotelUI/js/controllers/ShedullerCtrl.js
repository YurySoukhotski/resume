/**
 * Created by yury.soukhotski on 15.04.2017.
 */
hotelApp.controller('ShedullerCtrl', function ($scope, HttpMyService) {
    $scope.events = [];
    $scope.rooms = [];
    $scope.guests = [];
    $scope.guest = {};
    $scope.room = {};

    HttpMyService.get('/guest/').then(
        function successCallback(response) {
            $scope.guests = response.data;
        }, function errorCallback(response) {
            console.log("error");
        });

    HttpMyService.get('/room/').then(
        function successCallback(response) {
            $scope.rooms = response.data;

        }, function errorCallback(response) {
            console.log("error");
        });


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
    $scope.getSheduller = function () {
        HttpMyService.get('/sheduller/').then(
            function successCallback(response) {
                $scope.sheduller = response.data;
                for (var i = 0; i < $scope.sheduller.length; i++) {
                    $scope.findGuest($scope.sheduller[i].idGuest);
                    $scope.findRoom($scope.sheduller[i].idRoom);
                    $scope.events.push({
                        id: $scope.sheduller[i].id,
                        text: 'Guest:' + $scope.guest.name + '.' + $scope.guest.surname + ' Room №:' + $scope.room.number,
                        start_date: new Date($scope.sheduller[i].dateSt),
                        end_date: new Date($scope.sheduller[i].dateEnd)
                    });
                }
            }, function errorCallback(response) {
                console.log("error load sheduller");
            });
    };

    $scope.getSheduller();


    $scope.scheduler = {date: new Date(2017, 04, 28)};
});