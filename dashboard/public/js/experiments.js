angular.module('experimentApp', []).controller('ExperimentController', function ($scope, $http) {
    $http.get('data/experiments.json').success(function (data) {
        $scope.experiments = data;
    });
});