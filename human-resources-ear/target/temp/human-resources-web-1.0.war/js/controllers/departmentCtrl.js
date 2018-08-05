angular.module('departmentCtrl', [])

.controller('departments', ['$scope','departmentFactory', 'departmentsFactory', '$location', function($scope, departmentFactory,  departmentsFactory, $location) {

  //Get method to render Departments List in show_employees
  $scope.departments = departmentsFactory.query();

  //Redirection to update_employee with id of department selected as parameter
  $scope.editDepartment = function (departmentId) {
    $location.path('/update_department/' + departmentId);
  };

  // callback for ng-click 'deleteDepartment':
  $scope.deleteDepartment = function (departmentId) {
      departmentFactory.delete({ id: departmentId });
      $scope.departments = departmentsFactory.query();
  };

}])

.controller('createDepartment', ['$scope','departmentsFactory', '$location', function($scope, departmentsFactory, $location) {

  $scope.department = { 
    name : '',
    address: {
      city : '',
      country : '',
      street : '',
      streetNumber : '',
      zipCode: '' 
    }
  };

  $scope.registerDepartment = function() {
    departmentsFactory.create($scope.department);
    $location.path('/show_departments');
    $scope.departments = departmentsFactory.query();
  };
  
}])

.controller('updateDepartment', ['$scope', 'departmentFactory', 'departmentsFactory', '$location', '$routeParams', function($scope, departmentFactory, departmentsFactory, $location, $routeParams) {

  //Get by id method to bring employee with id preselect as parameter from web service
  $scope.department = departmentFactory.show({id: $routeParams.id});

  // callback for ng-submit 'updateDepartment':
  $scope.updateDepartment = function () {
    departmentsFactory.update($scope.department);
    $location.path('/show_departments');
  };

  // callback for ng-click 'cancel':
  $scope.cancel = function () {
    $location.path('/show_departments');
  };

}]);

