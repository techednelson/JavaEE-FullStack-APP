angular.module('employeeCtrl', [])

.controller('employees', ['$scope','employeesFactory', 'employeeFactory', '$location' , function($scope, employeesFactory, employeeFactory, $location) {

  //Get method to render Employees List in show_employees
  $scope.employees = employeesFactory.query();

  //Redirection to update_employee with id of employee selected as parameter
  $scope.editEmployee = function (employeeId) {
    $location.path('/update_employee/' + employeeId);
  };

  // callback for ng-click 'deleteEmployee':
  $scope.deleteEmployee = function (employeeId) {
      employeeFactory.delete({ id: employeeId });
      $scope.employees = employeesFactory.query();
  };

}])

.controller('createEmployee', ['$scope', 'employeesFactory', '$location' , function($scope, employeesFactory, $location) {

  $scope.employee = { 
    firstName : '',
    lastName : '',
    birthDate : '',
    address: {
      city : '',
      country : '',
      street : '',
      streetNumber : '',
      zipCode: '' 
    },
    phoneNumber : '',
    email : '',
    salary : '',
    department : {
      id : ""
    },
    joinDate : ''
  };

  // callback for ng-submit 'registerEmployee'
  $scope.registerEmployee = function() {
    employeesFactory.create($scope.employee);
    $scope.employees = employeesFactory.query();
    $location.path('/show_employees');
  };

}])

.controller('updateEmployee', ['$scope', 'employeeFactory', 'employeesFactory', '$location', '$routeParams', function($scope, employeeFactory, employeesFactory ,$location, $routeParams) {

  //Get by id method to bring employee with id preselect as parameter from web service
  $scope.employee = employeeFactory.show({id: $routeParams.id});

  // callback for ng-submit 'updateEmployee':
  $scope.updateEmployee = function () {
    employeesFactory.update($scope.employee);
    $location.path('/show_employees');
  };

  // callback for ng-click 'cancel':
  $scope.cancel = function () {
    $location.path('/show_employees');
  };

  
}]);
