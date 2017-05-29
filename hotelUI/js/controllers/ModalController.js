/**
 * Created by yury.soukhotski on 12.04.2017.
 */
hotelApp.controller('ModalController', function ($scope, close) {

    $scope.close = function (result) {
        close(result, 500); // close, but give 500ms for bootstrap to animate
    };

});
