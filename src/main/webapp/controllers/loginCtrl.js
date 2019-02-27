angular.module('myApp')
.controller("loginCtrl",function ($scope,$location,userAuthentication,jsonTransfer,travelRequest) {
	$scope.user={};
	$scope.form = {};
	$scope.loginError = "";
	//$scope.user.istravelView = false;
	
	$scope.removeSpan = function(){
		$scope.loginError = "";
	}
	
	$scope.login=function(){
		console.log("aa"+$scope.form.loginForm.$submitted,"bb"+JSON.stringify($scope.form.loginForm.name.$error), "cc"+$scope.form.loginForm.name.$invalid)
		if($scope.form.loginForm.$valid){
		$scope.showLoading = true;
		console.log("user ",$scope.user);
		userAuthentication.loginUser($scope.user).then(function(res){
			console.log("Login response ",JSON.stringify(res))
			jsonTransfer.setInfo(res.data)
			if(res.data!=""){
				//debugger;
				if( res.data.capcoUserId == "TRDK" || res.data.capcoUserId == "FINC" || res.data.capcoUserId == "HCOP" ){
					//$scope.user.istravelView = true;
					$location.path('/pendingRequests');
				}else{
					$scope.showLoading = false;
					$location.path('/home');
				}
			}
			else{
				
				$scope.loginError = "Invalid Username or Password";
				$location.path('/');
			}

			
			
		},function(err){
			$location.path('/');
			$scope.loginError = "Invalid Username or Password";
			
		});
		
		}
			
	}

	/*$scope.returnHome = function(){
		$location.path('/home');
	}*/
})