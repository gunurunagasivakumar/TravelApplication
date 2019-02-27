angular.module('myApp')

.controller("headerCtrl",['$scope','$http','$state','userAuthentication','jsonTransfer',function($scope,$http,$state,userAuthentication,jsonTransfer){
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	
	//var jsonObj=jsonTransfer.getHeaderInfo();
	/*console.log(jsonObj,"headerCtrl")
	$scope.userName=jsonObj;*/

	$scope.logout=function(){
		userAuthentication.logoutUser().then(function(res){
			console.log("logout message ",res.data)
			$state.go("login")
		},function(err){
			
			console.log("logout error ",err)
		})
	}
	
	$scope.home = function(){
		$state.go('home');
	}
}])


.controller("homeCtrl",function ($scope,$state,$http,$filter,userAuthentication,jsonTransfer,travelRequest,requestTypeParameterTransfer, firstApprover) {
	$scope.user={};
	$scope.user=jsonTransfer.getInfo();
	console.log("get Info ",$scope.user)
	userAuthentication.requestCount($scope.user.employeeId).then(function(res){
		console.log("resp from jsonplaceholder",res.data);
		$scope.count = res.data.requestCountVO;
		// jsonTransfer.setJson($scope.z);
	},function(err){
		alert("Enter valid credentials");
		$state.go('login')
	})

	$scope.tripsObj={};
	$scope.countabcd = +0;
	$scope.accomodationFlag=false;
	$scope.flightFlag=false;
	$scope.cabFlag=false;
	$scope.iconVisible = "display_visible";
	$scope.iconInvisible = "display_hidden";
	userAuthentication.requestCount($scope.user.employeeId).then(function(trips){
//		console.log(trips.data.mainRequestVOList,"trips");
		$scope.tripsObj=trips.data.mainRequestVOList;


		console.log($scope.user.employeeId);
		var count=-1;	
		angular.forEach($scope.tripsObj,function(obj){
			count++;
					})
		console.log(count);
		$scope.i;
		for(i=0;i<=count;i++)
		{
			
			if($scope.tripsObj[i].accomodationRequest!=null)
				{
					$scope.accomodationIconClass = "display_visible";
					$scope.tripsObj[i].accomodationRequest.category;
				$scope.tripsObj[i].accomodationRequest.createdOn=new Date($scope.tripsObj[i].accomodationRequest.createdOn);
					$scope.createdOn=$filter('date')($scope.tripsObj[i].accomodationRequest.createdOn, 'MMM d, y h:mm a');
					$scope.accomodationFlag=true;
					console.log($scope.accomodationFlag,"!null");
//					console.log($scope.createdOn);
				}
//			else{
//				$scope.accomodationFlag=false;
//				console.log($scope.accomodationFlag,"Null");
//			}
			if($scope.tripsObj[i].cabRequest!=null)
			{
				$scope.cabIconClass="display_visible";
				$scope.tripsObj[i].cabRequest.createdOn=new Date($scope.tripsObj[i].cabRequest.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].cabRequest.createdOn, 'MMM d, y h:mm a');
				$scope.cabFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.cabFlag=false;
//			}
			if($scope.tripsObj[i].flightRequest!=null)
			{
				$scope.flightIconClass="display_visible";
				$scope.tripsObj[i].flightRequest.createdOn=new Date($scope.tripsObj[i].flightRequest.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].flightRequest.createdOn, 'MMM d, y h:mm a');
				$scope.flightFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.flightFlag=false;
//			}
			if($scope.tripsObj[i].forexRequest!=null)
			{
				$scope.forexIconClass="display_visible";
				$scope.tripsObj[i].forexRequest.createdOn=new Date($scope.tripsObj[i].forexRequest.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].forexRequest.createdOn, 'MMM d, y h:mm a');
				$scope.forexFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.forexFlag=false;
//			}
			if($scope.tripsObj[i].visaRequestVO!=null)
			{
				$scope.tripsObj[i].visaRequestVO.createdOn=new Date($scope.tripsObj[i].visaRequestVO.createdOn);
				$scope.createdOn=$filter('date')($scope.tripsObj[i].visaRequestVO.createdOn, 'MMM d, y h:mm a');
				$scope.visaFlag=true;
//				console.log($scope.createdOn);
			}
//			else{
//				$scope.visaFlag=false;
//			}
		
			
			
		}
		
	},function(err){
		if(err.status==400){
			console.log(err.data)
			$state.go('login')
		}
		console.log(err,"eroooooooorrrrrrrrrrrr");
	})
	

	$scope.getRequestDetails=function(requestId){
    	travelRequest.getRequestDetails(requestId).then(function(res){
    		$scope.requestDetails=res.data;
    		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
    		$scope.cabObj=$scope.requestDetails.travelRequest.cabRequest;
    		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
    		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
    		$scope.visaObj=$scope.requestDetails.travelRequest.visaRequestVO;

    		console.log("request Details ",$scope.requestDetails);
    	},function(err){
    		if(err.status==400){
    			console.log(err.data)
    			$state.go('login')
    		}
    	})
    }
	
//	console.log(userLgData);
	
	$scope.newrequest=function(){
		$state.go('newRequest');
		
	}
	$scope.requestList=function(){
		$state.go('pendingrequests')
	}
	

	$scope.myRequestList = function(){
		$state.go('myrequests');
	}
	$scope.requestDetailsByType=function(requestDetails){
		$scope.requestDetailsType=requestDetails;
		requestTypeParameterTransfer.setInfo($scope.requestDetailsType);
		console.log($scope.requestDetailsType,"$scope.requestDetailsType");
		$state.go('myrequests');
	}
	console.log("Z=",$scope.z)
	// var jsonObj=jsonTransfer.getJson();
	// $scope.a = jsonObj[0].userId;


	// console.log(self.a,"dashboardCtrl Json");

		$(document).ready( function(){
  		var cTime = new Date(), month = cTime.getMonth()+1, year = cTime.getFullYear();
  		console.log("cTime:",cTime);
		theMonths = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

		theDays = ["S", "M", "T", "W", "T", "F", "S"];
	    events = [
	      [
	        "1/"+"/"+month+"/"+year, 
	        'Meet a friend', 
	        '#', 
	        '#177bbb', 
	        'Contents here'
	      ],
	      [
	        "7/"+month+"/"+year, 
	        'Kick off meeting!', 
	        '#', 
	        '#1bbacc', 
	        'Have a kick off meeting with .inc company'
	      ],
	      [
	        "17/"+month+"/"+year, 
	        'Milestone release', 
	        '#', 
	        '#fcc633', 
	        'Contents here'
	      ],
	      [
	        "19/"+month+"/"+year, 
	        'A link', 
	        'http://www.google.com', 
	        '#e33244'
	      ]
	    ];
	    $('#calendar').calendar({
	        months: theMonths,
	        days: theDays,
	        events: events,
	        popover_options:{
	            placement: 'top',
	            html: true
	        }
	    });
	});
		
})

.controller('approverController',['$scope','$state','$location','travelRequest','firstApprover','userAuthentication','jsonTransfer','travelAdmin', 'emailNotification', '$q', function($scope,$state,$location,travelRequest,firstApprover,userAuthentication,jsonTransfer,travelAdmin, emailNotification, $q){
	 $scope.level = userAuthentication.level;
	 $scope.user={};
	 $scope.selVal = {
			 hcops:{},
			 finance: {}
	 };
 	 $scope.user=jsonTransfer.getInfo();
	 $scope.empObj = {}
	 $scope.flightObj = {}
	 $scope.cabObj = {}
	 $scope.accomodationObj = {}
	 $scope.forexObj = {};
	 var mainRequest = travelRequest.request.travelRequest;	
	 $scope.home = function(){
 		$state.go("home");
 	}
 	
 	$scope.newrequest=function(){
 		$state.go('newRequest');
 		
 	}
 	$scope.requestList=function(){
 		$state.go('pendingrequests')
 	}
 	

 	$scope.myRequestList = function(){
 		$state.go('myrequests');
 	}
 	 	
 	$scope.hcexpanded = false;
 
 	$scope.hcreset = function(){
 		//debugger;
 		$scope.hcexpanded = false;
 		$scope.selVal.hcops.hcopsSelVal = "";
 	}
 	
 	$scope.finreset = function(){
 		//debugger;
 		$scope.finexpanded = false;
 		$scope.selVal.finance.financeSelVal = "";
 	}
 	
 	$scope.selfreset = function(){
 		//debugger;
 		$scope.selfexpanded = false;
 		$scope.selVal.traveldesk.selfSelVal = "";
 	}

 	$scope.hcshowCheckboxes = function() {
 		//debugger;
 		$scope.hccheckboxes = document.getElementById("hccheckboxes");
 		  if (!$scope.hcexpanded) {
 		    hccheckboxes.style.display = "block";
 		    $scope.hcexpanded = true;
 		  } else {
 		    hccheckboxes.style.display = "none";
 		    $scope.hcexpanded = false;
 		  }
 		  
 		  
 		}
 	
 	
 	$scope.finexpanded = false;
 	 

 	$scope.finshowCheckboxes = function() {
 		//debugger;
 		$scope.fincheckboxes = document.getElementById("fincheckboxes");
 		  if (!$scope.finexpanded) {
 		    fincheckboxes.style.display = "block";
 		    $scope.finexpanded = true;
 		  } else {
 		    fincheckboxes.style.display = "none";
 		    $scope.finexpanded = false;
 		  }
 		  
 		  
 		}
 	

 	$scope.selfexpanded = false;
	 

 	$scope.selfshowCheckboxes = function() {
 		//debugger;
 		$scope.selfcheckboxes = document.getElementById("selfcheckboxes");
 		  if (!$scope.selfexpanded) {
 		    selfcheckboxes.style.display = "block";
 		    $scope.selfexpanded = true;
 		  } else {
 		    selfcheckboxes.style.display = "none";
 		    $scope.selfexpanded = false;
 		  }
 		  
 		  
 		}
 	

 	
 	$scope.travelDeskList = [
 	      {name:'Release ISOS'},
 	      {name:'Flight ticket'},
 	      {name:'Hotel accommodation'},
 	      {name:'Travel Insurance- ISOS'},
 	     
 	    ];
 	
 	 $scope.gettravelSelectedItems = function(item){
 		
 		$scope.selVal.traveldesk.selfSelVal = $scope.travelDeskList.filter(function(x) {
		  return x.selected;
		});
 		
     };
     
     $scope.financeList = [
	      {name:'Issue Forex card'},
	      {name:'Issue Travel advance'},
	      	     
	    ];
	
	 $scope.getfinanceSelectedItems = function(item){
		
		$scope.selVal.finance.financeSelVal = $scope.financeList.filter(function(x) {
		  return x.selected;
		});
		
    };
    
    $scope.hcopsList = [
	      {name:'Invitation letter'},
	      {name:'Covering letter'},
	      {name:'Company profile'},
	      {name:'Company financial statement'},
	      	     
	    ];
	
	 $scope.gethcopsSelectedItems = function(item){
		
		$scope.selVal.hcops.hcopsSelVal = $scope.hcopsList.filter(function(x) {
			//debugger;
		  return x.selected;
		});
		
  };
 	
 	$scope.updateStatusOfMainRecord = function(action, reqId){
 		var result;
 		if(action == 'hold'){
 			mainRequest.currentStatus = 'On-Hold';
 			result = travelRequest.editRecord(mainRequest, reqId);
 		} else if(action == 'complete'){
 			mainRequest.currentStatus = 'Completed';
 			result = travelRequest.editRecord(mainRequest, reqId);
 		}
 		result.then(function(){
 			$location.path('/pendingRequests');
 		}, function(err){
 			$('#myModal').modal('show');
 			$scope.serverError = "Something went wrong while updating status";
 		});
    	
 	}	
 	
 	$scope.updateStatus = function(selval,reqId){
 		var result = [];
	    console.log(selval);
	    if(selval.hcops.hcopschecked && selval.hcops.hcopsSelVal.length){
	    	$scope.hcItems = [];
	    	for (var x in selval.hcops.hcopsSelVal) {
	    		$scope.hcItems.push(selval.hcops.hcopsSelVal[x].name);
	    	}
		    $scope.hcdata = {
		    	requestId:reqId,
		    	actionId:"HC",
		    	actionStatus: "Pending with HC",
		    	pendingItems: ($scope.hcItems).join()	    	
		    }
		    
		    result.push(travelAdmin.updateHcRecords($scope.hcdata));
		    
	    } 
	    if(selval.finance.finchecked && selval.finance.financeSelVal.length){
	    	$scope.finItems = [];
	    	for (var x in selval.finance.financeSelVal) {
	    		$scope.finItems.push(selval.finance.financeSelVal[x].name);
	    	}
		    $scope.fincdata = {
		    	requestId:reqId,
		    	actionId:"FINC",
		    	actionStatus: "Pending with Finance",
		    	pendingItems: ($scope.finItems).join()		    	
		    }
		    
		    result.push(travelAdmin.updateFincRecords($scope.fincdata));
//			  travelAdmin.updateFincRecords($scope.fincdata).then(function(res){
//				  travelRequest.editRecord(mainRequest, reqId);
//				  sendEmail('finance');
//				  console.log("Finance record updated");
//				    	
//			   },function(err){
//						if(err.status==400){
//						console.log(err.data)
//						
//					}
//			    });
//			    $location.path('/pendingRequests');
		    }
	    if(result.length){
	    	$q.all(result).then(function(){
		    	if($scope.finItems && !$scope.hcItems){
		    		mainRequest.currentStatus = "Pending with Finance";
		    		sendEmail('finance');
		    	} else if($scope.hcItems && !$scope.finItems){
		    		mainRequest.currentStatus = 'Pending with HC';
		    		sendEmail('hcops');
		    	} else if($scope.finItems && $scope.hcItems){
		    		mainRequest.currentStatus = 'Pending with HC,Finance';
		    		sendEmail('hcops');
		    		sendEmail('finance');
		    	}
		    	travelRequest.editRecord(mainRequest, reqId).then(function(){
		    		$location.path('/pendingRequests');
		    	}, function(err){
		    		$('#myModal').modal('show');
			    	$scope.serverError = "Something went wrong while updating status";
			    });
		    }, function(err){
	    		$('#myModal').modal('show');
		    	$scope.serverError = "Something went wrong while updating status";
		    });
	    } else{
	    	$('#myModal').modal('show');
	    	$scope.serverError = "No items selected ! Please select the action items";
	    }
	    
	    
 	}
	    
	      
	    
	    $scope.updateHCStatus = function(selval,req){
		    $scope.hcissueddata = {
		    	requestId:req.requestId,
		    	actionId:"HC",
		    	actionStatus: "Issued by HC",
		    	pendingItems:"null"
		    			    	
		    }
		    if(mainRequest.currentStatus == 'Issued by Finance') mainRequest.currentStatus = "Issued by HC,Finance";
		    else mainRequest.currentStatus = "Issued by HC";
			travelAdmin.updateHcIssuedRecords($scope.hcissueddata).then(function(res){
				travelRequest.editRecord(mainRequest, req.requestId);
                console.log("HcOPs record updated");
			    sendEmail('HCBP',selval);	
			    $location.path('/pendingRequests');
			},function(err){
				$('#myModal').modal('show');
	 			$scope.serverError = "Something went wrong while updating status";
			});
	   }
	   
	    
	   // Call the api and filter the record based on the reqest Id passed
	   function getRecordDetailsAndUpdate(reqId){
		  return travelAdmin.getHcOpsRecords().then(function(res){
		    	for(var i=0; i < res.data.length; i++){
		    		console.log(res.data[i].requestId)
		    		if(res.data[i].requestId == reqId){
	    				 if(res.data[i].actionStatus == 'Issued by HC') mainRequest.currentStatus = "Issued by HC,Finance";
	    				 else mainRequest.currentStatus = "Issued by Finance";
    				  	 return travelAdmin.updateFincIssuedRecords($scope.finissueddata).then(function(res){
    				  		 console.log("Finance record updated");
    				  		 return travelRequest.editRecord(mainRequest, reqId);
    				  	});
	    					
		    		}
		    	}
		    });
	   }
	    
	    $scope.updateFinanceStatus = function(selval,req){
		    console.log(selval);
		    $scope.finissueddata = {
		    	requestId:req.requestId,
		    	actionId:"FINC",
		    	actionStatus: "Issued by Finance",
		    	pendingItems:""
		    			    	
		    }
		    if(mainRequest.currentStatus == 'Issued by HC') mainRequest.currentStatus = "Issued by HC,Finance";
		    else mainRequest.currentStatus = "Issued by Finance";
		    travelAdmin.updateFincIssuedRecords($scope.finissueddata).then(function(){
		    	travelRequest.editRecord(mainRequest, req.requestId);
		    	sendEmail('finance',selval);
		    	$location.path('/pendingRequests');
		    }, function(err){
		    	$('#myModal').modal('show');
	 			$scope.serverError = "Something went wrong while updating status";
		    });
		}
	
	
		 
	 
	 $scope.billable=[{type:"Billable"},{type:"Non-Billable"}]
	        $scope.getBillable=function(){
		 return $scope.billable;
	 }
	 $scope.cell={
			 evaluator:"Billable"
	 }

	 $scope.projectList = travelRequest.approverAndProjectList;

	 $scope.requestToApproveDetails=travelRequest.request; 
	 $scope.flightObj=$scope.requestToApproveDetails.travelRequest.flightRequest;
		$scope.cabObj=$scope.requestToApproveDetails.travelRequest.cabRequest;
		$scope.accomodationObj=$scope.requestToApproveDetails.travelRequest.accomodationRequest;
		$scope.forexObj=$scope.requestToApproveDetails.travelRequest.forexRequest;
		$scope.visaObj=$scope.requestToApproveDetails.travelRequest.visaRequestVO;
		$scope.currentStatus;
			 $scope.rejectRemark="";
			 $scope.action;
			 $scope.secondApprover={};
//			 $scope.secondApprover.name={};
//			 $scope.secondApprover.name.approverId;
			 
			 
			 $scope.forwardToApprove=function(requestId){
				 $scope.userData={}
				$scope.billable;
				$scope.currentStatus="Partner Approval Pending";
				$scope.userData={
						 requestId:requestId,
						 currentStatus: $scope.currentStatus,
						 remark: $scope.rejectRemark,
//						 billable: true
				}
				
				//console.log("first billable",$scope.userData.billable)
		if($scope.cell.evaluator=="Billable"){
			$scope.userData.billable=true;
		}		
		else{
			$scope.userData.billable=false;
		}
				$scope.userData.actionOnRequest="Approved";
				$scope.userData.approverId=$scope.secondApprover.name.approverId;
				// $scope.userData.billable=""//$scope.billable;
				console.log("userData ",$scope.userData,"Billable ",$scope.billable)
				firstApprover.sendRequest($scope.userData).then(function(res){
					sendEmail('approve');
					console.log("first approver",res.data);
					$state.go('pendingrequests');
				},function(err){
					$('#myModal').modal('show');
		 			$scope.serverError = "Something went wrong while updating status";
				})
			}
			 
			  
				$scope.l1Reject=function(requestId){
					$scope.currentStatus="Rejected";
					$scope.action="Rejected";
					$scope.l1Approver=$scope.user.employeeId;
						$scope.userData={
							 requestId:requestId,
							 approverId: $scope.l1Approver,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.rejectRemark,
							 actionOnRequest: $scope.action,
							 
					}
						$scope.userData.billable;
						console.log($scope.userData);
						firstApprover.sendRequest($scope.userData).then(function(res){
							sendEmail('reject');
							console.log(res.data);
							$state.go('pendingrequests');
						},function(err){
							$('#myModal').modal('show');
				 			$scope.serverError = "Something went wrong while updating status";
						})
				}
				
				
				
				//hold request
				
				$scope.l1Hold=function(requestId){
					$scope.currentStatus="On Hold";
					$scope.action="On Hold";
					$scope.l1Approver=$scope.user.employeeId;
						$scope.userData={
							 requestId:requestId,
							 approverId: $scope.l1Approver,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.holdRemark,
							 actionOnRequest: $scope.action,
							 
					}
						//debugger;
						$scope.userData.billable;
						console.log($scope.userData);
						firstApprover.sendRequest($scope.userData).then(function(res){
							//debugger;
							//sendEmail('reject');
							console.log(res.data);
							$state.go('pendingrequests');
						},function(err){
							$('#myModal').modal('show');
				 			$scope.serverError = "Something went wrong while updating status";
						})
				}
				

				
// Second level approve and reject
				
				travelRequest.getApproverAndProjectList().then(function(res){
					$scope.projectList=res.data;
					travelRequest.approverAndProjectList=$scope.projectList;
					console.log("projectList: ",$scope.projectList)
				},function(err){
					$('#myModal').modal('show');
			    	$scope.serverError = "Something went wrong while fetching data";
					console.log(err);
				});
				
				function getApproverName(id){
					for(var i=0;i<$scope.projectList.listOfApprovers.length;i++){
	            		if($scope.projectList.listOfApprovers[i].approverId==id){
	            			console.log($scope.projectList.listOfApprovers[i].approverName)
	            			return $scope.projectList.listOfApprovers[i].approverName;
	            		}
	            		
	            	}
				}
				
				function sendEmail(actionType,hcbp){
					var updatedPayload = {};
					var emailPayload = {'employeeregd':{},'approver':{}, "partner": {}, "HCBP": {} , "hcops":{} , "finance":{}};
					emailPayload.employeeregd.name = $scope.user.employeeName;
					emailPayload.employeeregd.employeeNumber = $scope.user.employeeId;
					emailPayload.employeeregd.employeeEmailID = $scope.user.emailId;
					emailPayload.employeeregd.projectName = travelRequest.request.travelRequest.projectName;
					emailPayload.approver.approverName = getApproverName(travelRequest.request.approverId || 16312);
					emailPayload.approver.approverEmailID = $scope.user.approverEmail || "anupam.basu@capco.com";
					emailPayload.partner.partnerName = $scope.secondApprover.name || "Rajnish Dalmia";
					emailPayload.partner.partnerEmailID = $scope.secondApprover.email || "rajnish.dalmia@capco.com";
					if(actionType == 'approve'){
						updatedPayload = emailPayload; 
						delete updatedPayload.employeeregd.employeeEmailID;
						delete updatedPayload.HCBP;
						return emailNotification.approverequest(updatedPayload);
					}else if(actionType == 'reject'){
						updatedPayload = emailPayload;
						delete updatedPayload.partner;
						delete updatedPayload.HCBP;
						return emailNotification.rejectrequest(updatedPayload);
					}else if(actionType == 'partnerApprove'){
						updatedPayload = emailPayload;
						delete updatedPayload.HCBP;
						delete updatedPayload.employeeregd.employeeEmailID;
						delete updatedPayload.partner.partnerEmailID;
						return emailNotification.partnerApproval(updatedPayload);
					}else if(actionType == 'HCBP'){
						updatedPayload = emailPayload;
						updatedPayload.HCBP.hcBPName =  hcbp.hcops.businessPtnr;
						updatedPayload.HCBP.hcBPEmailID = hcbp.hcops.emailId || "dharani.medappa@capco.com";
						delete updatedPayload.employeeregd.employeeEmailID;
						delete updatedPayload.partner;
						delete updatedPayload.approver;
						return emailNotification.hcbpMail(updatedPayload);
					}
					else if(actionType == 'hcops'){
						updatedPayload = emailPayload;
						//updatedPayload.hcops.hcBPName =  hcbp.hcops.businessPtnr;
						updatedPayload.hcops.hcopsEmailID =  "dharani.medappa@capco.com";
						delete updatedPayload.employeeregd.employeeEmailID;
						delete updatedPayload.partner;
						delete updatedPayload.approver;
						return emailNotification.hcopsMail(updatedPayload);
					}
					else if(actionType == 'finance'){
						updatedPayload = emailPayload;
						//updatedPayload.HCBP.hcBPName =  hcbp.hcops.businessPtnr;
						updatedPayload.finance.financeEmailID =  "dharani.medappa@capco.com";
						delete updatedPayload.employeeregd.employeeEmailID;
						delete updatedPayload.partner;
						delete updatedPayload.approver;
						return emailNotification.financeMail(updatedPayload);
					}
				}
				
				$scope.secondLevelApproveRequest=function(requestId){
					$scope.currentStatus="Approved";
					$scope.action="Approved";
					$scope.userData={
							 requestId:requestId,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.rejectRemark,
							 actionOnRequest: $scope.action
					}
					$scope.userData.billable;
					$scope.userData.approverId=$scope.user.employeeId;
					firstApprover.sendRequest($scope.userData).then(function(res){
						sendEmail('partnerApprove');
						console.log("second approver",res.data);
						$state.go('pendingrequests');
					},function(err){
						$('#myModal').modal('show');
				    	$scope.serverError = "Something went wrong while updating status";
					})
				}
				
				$scope.secondLevelRejectRequest=function(requestId){
					$scope.currentStatus="Rejected";
					$scope.action="Rejected";
					$scope.userData={
							 requestId:requestId,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.rejectRemark,
							 actionOnRequest: $scope.action
					}
					$scope.userData.billable;
					$scope.userData.approverId=$scope.user.employeeId;
					firstApprover.sendRequest($scope.userData).then(function(res){
						sendEmail('reject');
						console.log("second approver",res.data);
						$state.go('pendingrequests');
					},function(err){
						$('#myModal').modal('show');
				    	$scope.serverError = "Something went wrong while updating status";
					})
				}
				
				
				
				//second level hold
				
				$scope.secondLevelHoldRequest=function(requestId){
					$scope.currentStatus="On Hold";
					$scope.action="On Hold";
					$scope.userData={
							 requestId:requestId,
							 currentStatus: $scope.currentStatus,
							 remark: $scope.holdRemark,
							 actionOnRequest: $scope.action
					}
					$scope.userData.billable;
					$scope.userData.approverId=$scope.user.employeeId;
					firstApprover.sendRequest($scope.userData).then(function(res){
						//sendEmail('reject');
						console.log("second approver",res.data);
						$state.go('pendingrequests');
					},function(err){
						$('#myModal').modal('show');
				    	$scope.serverError = "Something went wrong while updating status";
					})
				}
				
				$scope.reqList = function(){
					$state.go('pendingrequests');
				}
			
			
 		
}])



//Don't need it now because of ui-routing so we can remove the below state.go code
//=============================
// .controller('requestController',function($scope,$state){
// 	// $location.path('/myrequests/personalDetails.html')
// 	$scope.showPersonalDetails = function(){
// 		$state.go('home.newrequest.personalDetails');
// 	}
// 	$scope.showRequestDetails = function(){
// 		$state.go('home/newrequest/requestDetails');
// 	}
// 	$scope.showConfirmPage = function(){
// 		$state.go('home/newrequest/confirm');
// 	}
// })



// .controller('masterCtrl',function($scope,$location,userAuthentication,jsonTransfer){
// 	$scope.login=function(){
// 		userAuthentication.dummy().then(function(res){
// 		console.log("resp from jsonplaceholder",res.data[0]);
// 		$scope.z = res;
		
// 		jsonTransfer.setJson($scope.z);
// 		console.log('z',$scope.z)
// 	})
// 		$location.path(`/home`);
// 	}
// })