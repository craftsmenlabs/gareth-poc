angular.module('experimentApp', []).controller('ExperimentController', function ($scope, $http) {
    $http.get('http://localhost:9999/experiments').success(function (data) {
        $scope.experiments = data;
    });
});