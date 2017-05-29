/**
 * Created by yury.soukhotski on 15.04.2017.
 */
hotelApp.controller('StatCtrl', ['HttpMyService', '$scope', function (HttpMyService, $scope) {
    $scope.stat = [];
    $scope.guests = [];
    $scope.myMoneyData = {
        chart: {
            caption: "Money statistic",
            numberPrefix: "$",
            theme: "ocean"
        },
        data: []
    };

    $scope.myGuestData = {
        chart: {
            caption: "Guest count filling by date",
            numberPrefix: "Prs. ",
            theme: "ocean"
        },
        data: []
    };
    $scope.myAvgData = {
        chart: {
            caption: "Average Person/Money spent",
            numberPrefix: "$",
            theme: "ocean"
        },
        data: []
    };

    $scope.myGuestSpent = {
        chart: {
            caption: "Guest billing",
            numberPrefix: "$",
            theme: "ocean"
        },
        data: [],
        trendlines: [
            {
                "line": [
                    {
                        "startvalue": "100",
                        "color": "#1aaf5d",
                        "valueOnRight": "1",
                        "tooltext": "Limit on discount 5%",
                        "displayvalue": "Target - 100$"
                    }
                ]
            }
        ]
    };

    HttpMyService.get('/analitics/').then(
        function successCallback(response) {
            $scope.stat = response.data;

            for (var i = 0; i < $scope.stat.length; i++) {
                $scope.myMoneyData.data.push({
                    label: $scope.stat[i].date,
                    value: $scope.stat[i].summPeriod
                });
                $scope.myGuestData.data.push({
                    label: $scope.stat[i].date,
                    value: $scope.stat[i].countGuest
                });

                $scope.myAvgData.data.push({
                    label: $scope.stat[i].date,
                    value: ($scope.stat[i].summPeriod / $scope.stat[i].countGuest).toFixed(2)
                });

            }
        }, function errorCallback(response) {
            console.log("error");
        });

    HttpMyService.get('/guest/').then(
        function successCallback(response) {
            $scope.guests = response.data;
            for (var i = 0; i < $scope.guests.length; i++) {
                $scope.myGuestSpent.data.push({
                    label: $scope.guests[i].name + $scope.guests[i].surname,
                    value: $scope.guests[i].amount
                });
            }
        }, function errorCallback(response) {
            console.log("error");
        });

}]);