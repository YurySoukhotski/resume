/**
 * Created by yury.soukhotski on 15.04.2017.
 */
hotelApp.controller('MngCtrl', ['HttpMyService', '$scope', 'moment', function (HttpMyService, $scope, moment) {
    $scope.startShow = false;
    $scope.newShow = false;
    $scope.config = {};
    $scope.selectedIndex = 0;
    $scope.pageSize = 3;
    $scope.dateAccount = new Date();
    $scope.count = {};
    $scope.summ = {};
    $scope.data = {};

    HttpMyService.get('/config/').then(
        function successCallback(response) {
            $scope.config = response.data;
        }, function errorCallback(response) {
            console.log("error");
        });

    $scope.getStat = function () {
        HttpMyService.get('/analitics').then(
            function successCallback(response) {
                $scope.stats = response.data;
            }, function errorCallback(response) {
                console.log("error");
            });
    };


    $scope.getStat();

    $scope.save = function () {
        HttpMyService.update('/config/', $scope.config).then(function successCallback(response) {
        }, function errorCallback(response) {
            console.log("Error");

        });

    };

    $scope.getSum = function (date) {
        HttpMyService.getId('/payment/date/', date).then(
            function successCallback(response) {
                $scope.summ = response.data.body;
            }, function errorCallback(response) {
                console.log("error");
            });
    };
    $scope.getCount = function (date) {
        HttpMyService.getId('/countguestondate/', date).then(
            function successCallback(response) {
                $scope.count = response.data.body;
            }, function errorCallback(response) {
                console.log("error");
            });
    };

    $scope.sendStat = function () {
        HttpMyService.create('/analitics', $scope.data).then(function successCallback(response) {
        }, function errorCallback(response) {
            console.log("Error add statistics");
        });
    }

    $scope.account = function () {
        $scope.data.date = moment($scope.dateAccount).format("YYYY-MM-DD");
        $scope.getSum($scope.data.date);
        $scope.getCount($scope.data.date);
        $scope.data.summPeriod = $scope.summ;
        $scope.data.countGuest = $scope.count;
        setTimeout($scope.sendStat(),4000);


    }
}]);

