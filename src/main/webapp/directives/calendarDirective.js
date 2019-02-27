angular.module('myApp')
.directive('managerRequestList',function () {
	return{
		restrict:'A',
		link:function(){
			$scope.user={};
			 $scope.approverId=$scope.user.employeeId;
	        	$scope.requestToApprove=[];        	
	        	
	            $scope.checkList = function(){
	            	travelRequest.getAllRecords($scope.approverId).then(function(res){
	            
	            	console.log("get all records exectuted");
	        		if(res.data.requestListVO.length==0){
	        			//alert("No requests found");
	        			//$state.go('home');
	        		}
	        		else{
	        			for(var i = 0; i<res.data.requestListVO.length;i++){
	        				if(res.data.requestListVO[i].currentStatus=="Manager Approval Pending" || 
	        						res.data.requestListVO[i].currentStatus=="Partner Approval Pending" || 
                                    res.data.requestListVO[i].currentStatus=="Hold" || 
                                    res.data.requestListVO[i].currentStatus=="Pending With Finance" ||
                                    res.data.requestListVO[i].currentStatus=="In Progress" ||
                                    res.data.requestListVO[i].currentStatus=="Pending With Hc-ops"){
	        					
	        					$scope.requestToApprove.push(res.data.requestListVO[i]);
	        				}
	        			}
	        			console.log($scope.requestToApprove)
	        		}
	        	},function(err){
	        		console.log("error: ",err);
	        	})
	            }

	        	
	 


	        	// Get request to take action on         	
	        	$scope.requestToApproveDetails;
	        	$scope.aq;
	        	$scope.getRequestToApproveDetails=function(requestId){
	        		
	            	travelRequest.getRequestDetails(requestId).then(function(res){
	            		$scope.requestToApproveDetails=res.data;
	            		$state.go('actionOnRequest')
	            		
	            		
	            		console.log("request to approve Details inside function",$scope.requestToApproveDetails);
	            		travelRequest.request = $scope.requestToApproveDetails;
	            	},function(err){

	            	});
	            }
	        	console.log("service variable ",travelRequest.request)
		}
		
	};

});