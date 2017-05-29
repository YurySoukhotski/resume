hotelApp.controller('AboutCtrl', function ($scope) {
    $scope.showPopUpMsg = false;
    $scope.openPopUp = function (text) {
        $scope.showPopUpMsg = true;
        $scope.popUpMsgContent = text;
    }


})
