hotelApp.controller('NewsController', function ($scope, HttpMyService) {
        $scope.sortType = 'date';
        $scope.sortReverse = true;
        $scope.searchName = '';
        $scope.fullNews;
        $scope.newsList = [];
        HttpMyService.get('/news/').then(
            function successCallback(response) {
                $scope.newsList = response.data;
            }, function errorCallback(response) {
                console.log("error");
            });

        $scope.currentPage = 0;
        $scope.itemsPerPage = 4;

        $scope.showFullNews = function (news) {
            $scope.fullNews = news;

        }

        $scope.firstPage = function () {
            return $scope.currentPage == 0;
        }
        $scope.lastPage = function () {
            var lastPageNum = Math.ceil($scope.newsList.length / $scope.itemsPerPage - 1);
            return $scope.currentPage == lastPageNum;
        }

        $scope.numberOfPages = function () {
            return Math.ceil($scope.newsList.length / $scope.itemsPerPage);
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

