function UsersController($scope, $http) {
    $scope.allusers = function() {
        $http.get('allUsers').
            success(function(data) {
                $scope.alluser = data;
            });
    }

    $scope.adduser = function() {
        $http.get('addUser', {params: {username: $scope.user.username,firstname: $scope.user.firstname,email: $scope.user.email}}).
            success(function(data) {
                $scope.user = {};
            });
    }
    $scope.delete = function(id) {
        $http.get( 'deleteUser', {params: {id: id}}).success(function( id ){
            $scope.allusers();
        })
    }

    $scope.allusers();
}