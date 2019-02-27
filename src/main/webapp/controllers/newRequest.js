angular.module("myApp")
	.controller("requestListCtrl", function ($scope, $location,$state,$uibModal, userAuthentication, travelAdmin, travelRequest, jsonTransfer,$filter,editRequestJsonTransfer, requestTypeParameterTransfer){
		$scope.user={};
    	$scope.user=jsonTransfer.getInfo();
    	$scope.pagination = {
		    currentPage: 1,
		    maxSize: 5,
		    itemsPerPage: 7
		};
		// get all requests of user
    	$scope.allUserRequest = [];
        var requestDetailsType=requestTypeParameterTransfer.getInfo();
        console.log(requestTypeParameterTransfer.getInfo(),"hello");
        $scope.typeOfReq=requestDetailsType.type;
        console.log(requestDetailsType.type,"$scope.requestDetailsType");
        $scope.setPagingData = function() {
        	var page = $scope.pagination.currentPage;
    		var pagedData = $scope.allUserRequest.slice(
    		  (page - 1) * $scope.pagination.itemsPerPage,
    		  page * $scope.pagination.itemsPerPage
    		);
    		$scope.userRequest = pagedData;
    	}
        
        if(requestDetailsType.type==null){
        travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
               console.log(res.data,"ByUserId");
               if(res.data.length==0){
                     //alert("No requests found");
                    // $state.go('home')
               }
               else{
                     for(var i = 0; i<res.data.length;i++){
                            if(res.data[i].currentStatus!="Cancelled"){
                                   $scope.allUserRequest.push(res.data[i]);
                            }
                     }
               }
               $scope.totalItems = $scope.allUserRequest.length;
               $scope.setPagingData($scope.pagination.currentPage);

        },function(err){
               if(err.status==400){
                     console.log(err.data)
                     $state.go('login')
               }
        })
        }
        else{
               travelRequest.getRequestDetailsByType(requestDetailsType.type).then(function(res){
                     requestDetailsType.type=null;
                     console.log(res,"ByType"); 
               if(res.data.requestListVO.length==0){
                     //alert("No requests found");
                     //$state.go('home')
               }
               else{
                     for(var i = 0; i<res.data.requestListVO.length;i++){
                            if(res.data.requestListVO[i].currentStatus!="Cancelled"){
                                   $scope.allUserRequest.push(res.data.requestListVO[i]);
                            }
                     }
               }
//            console.log(res.data);
               },function(err){
                   if(err.status==400){
                       console.log(err.data)
                       $state.go('login')
                 }
          })
        }

    			 
//        $scope.reqListDetailsAfterView = function(){
//        	debugger;
//        	travelRequest.getRequestDetailsByUserId($scope.requestDetails.travelRequest.requestId).then(function(res){//$scope.user.employeeId
//                console.log(res.data,"ByUserId");       
//                $scope.userRequest=[];
//                if(res.data.length==0){
//                      alert("No requests found");
//                      $state.go('home')
//                }
//                else{
//                      for(var i = 0; i<res.data.length;i++){
//                             if(res.data[i].currentStatus!="Cancelled"){
//                                    $scope.userRequest.push(res.data[i]);
//                             }
//                      }
//                }
//                
//
//         },function(err){
//                if(err.status==400){
//                      console.log(err.data)
//                      $state.go('login')
//                }
//         })
//         

//        }

    	
    	/*travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
    		$scope.userRequest=[];
    	  		if(res.data.length==0){
    			alert("No requests found");
    			$state.go('home')
    		}
    		else{
    			for(var i = 0; i<res.data.length;i++){
    				if(res.data[i].currentStatus!="Cancelled"){
    					$scope.userRequest.push(res.data[i]);
    				}
    			}
    		}
   		

    	},function(err){
    		if(err.status!=200){
    			console.log(err.data)
    			$state.go('login')
    		}
    	})*/
        
      //code for side bar click end
    	
    	//returning home/dashboard from Request list
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

//code for side bar click end
    	
    	$scope.backBtnClick = function(){
    		$scope.reqViewClicked = false;
    		//alert($scope.reqViewClicked);
    	}
//Get Request details on click of specific request in request list
    	$scope.requestDetails;
    	$scope.getRequestDetails=function(requestId){
    		//debugger;
    		$scope.reqViewClicked = true; 
        	travelRequest.getRequestDetails(requestId).then(function(res){
        		$scope.requestDetails=res.data;
        		
        		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
        		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
        		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
        		$scope.visaObj=$scope.requestDetails.travelRequest.visaRequestVO;
        		$scope.empDetails=$scope.requestDetails.travelRequest.empData;
        		console.log("request Details ",$scope.requestDetails);
        		console.log("emp Date", $scope.empDetails);
        	},function(err){
        		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
        	});
    	}
        
	
    	
    	// deleting request by request Id
    	$scope.deleteRequest=function(requestId){
    		
    		// new pop-up code for delete record
    		
    		$scope.deleteRequestId = requestId;
    		
    		//debugger;
    		$scope.modalInstance = $uibModal.open({
       	     
      	     
      	      templateUrl: 'views/deletePopup.html',
      	      controller: 'DeleteModalInstanceCtrl'  ,
      	      resolve: {
                params: function () {
                   return {requestId:$scope.deleteRequestId,userRequest:$scope.userRequest};
                   
                }
             }

      	      


      	      
      	    }).result.then(function(){
      	    	//debugger;
      	    	console.log($scope);
      	    });

    		
    		
    		
    		
    		
//    		travelRequest.deleteRecord(requestId).then(function(){
//    			console.log("request deleted ",requestId)
//    			travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
//    		$scope.userRequest=[];
//    		if(res.data.length==0){
//    			alert("No requests found");
//    			$state.go('home')
//    		}
//    		else{
//    			for(var i = 0; i<res.data.length;i++){
//    				if(res.data[i].currentStatus!="Cancelled"){
//    					$scope.userRequest.push(res.data[i]);
//    				}
//    			}
//    		}
//
//    	},function(err){
//    		if(err.status==400){
//    			console.log(err.data)
//    			$state.go('login')
//    		}
//    	})
//    			travelRequest.getRequestDetailsByUserId($scope.user.employeeId).then(function(res){//$scope.user.employeeId
//    		$scope.userRequest=[];
//    		if(res.data.length==0){
//    			alert("No requests found");
//    			$state.go('home')
//    		}
//    		else{
//    			for(var i = 0; i<res.data.length;i++){
//    				if(res.data[i].currentStatus!="Cancelled"){
//    					$scope.userRequest.push(res.data[i]);
//    				}
//    			}
//    		}
//
//    	},function(err){
//    		if(err.status==400){
//    			console.log(err.data)
//    			$state.go('login')
//    		}
//    	})
//    		},function(err){
//    			if(err.status==400){
//    				console.log(err.data)
//    				$state.go('login')
//    			}
//    		});
    		
    	}
    	
    	  $scope.empObj = {}
  	    //$scope.empObj.approverName={};
          $scope.flightObj = {};
          $scope.visaObj={};
          $scope.flightNull = null;

          $scope.accomodationObj = {}
          $scope.accomodationNull = null;

          $scope.forexObj = {}
          $scope.forexNull = null
          $scope.empObj.employeeNameq = "";
    	 $scope.editRequest=function(requestId){
        	  $scope.abc="abc";
        	 
         	travelRequest.getRequestDetails(requestId).then(function(res){
         		$scope.requestDetails=res.data.travelRequest;
         		console.log("before transfer",$scope.requestDetails);
         		editRequestJsonTransfer.setInfo($scope.requestDetails);
         		 $state.go("editRequest");
//         		console.log($scope.requestDetails.travelRequest.requestedFor,'requested for');
//         		$scope.empObj.employeeNameq = $scope.requestDetails.travelRequest.requestedFor;
//         		$scope.flightObj=$scope.requestDetails.travelRequest.flightRequest;
//         		$scope.accomodationObj=$scope.requestDetails.travelRequest.accomodationRequest;
//         		$scope.forexObj=$scope.requestDetails.travelRequest.forexRequest;
//         		
//         		console.log("request Details for edit ",$scope.empObj.employeeNameq,$scope.requestDetails);
         	},function(err){
         		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
         	});
         	
         }
	})
	
	.controller('DeleteModalInstanceCtrl', function ( $scope, $location,$state, userAuthentication, travelRequest,travelAdmin, jsonTransfer,$filter, $uibModalInstance , params) {
    		
    		  //var $ctrl = this;
    		  //$ctrl.requestId = $scope.requestId;
    		  
    		  var data = params;
    		  //debugger;

    		  $scope.ok = function () {
    			 // debugger;
    			  travelRequest.deleteRecord(data.requestId).then(function(){
           			//debugger;
           			console.log("request deleted ",data.requestId)
           			$uibModalInstance.dismiss();
//           			$scope.updatedList=[];
//           			for(var i = 0; i < data.userRequest.length;i++){
//						if(data.userRequest[i].requestId != data.requestId){		
//							$scope.updatedList.push(data.userRequest[i]);
//						}
//					}
//           			
//           			$scope.userRequest = $scope.updatedList;
           			//location.reload();
           			$state.go('home');
           	}).catch(function (error) {
                // pass the error the the error service
                return errorService.handleError(error);
            });
        		
    	}

    		  $scope.cancel = function () {
    		    $uibModalInstance.dismiss('cancel');
    		  };
    		  
    		  
       })

  
	
	
    .controller("newRequestCtrl", function ($filter, $scope, $location,$state, userAuthentication, travelRequest, travelAdmin, jsonTransfer,$filter, emailNotification) {
    	$scope.user={};
    	$scope.projectList={};
    	$scope.isos = {};
    	var emailPayload = {'employeeregd':{},'approver':{}};
    	$scope.user=jsonTransfer.getInfo();
    	$scope.classValue1 = "active";
    	$scope.minDate = new Date();
    	
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
    	
    	console.log("get Info newrequestctrl ",$scope.user)
    	if($scope.user.isApprover){
    		userAuthentication.level=2
    	}
    	else{
    		userAuthentication.level=1
    	}
    	travelRequest.getApproverAndProjectList().then(
				function(res){
				$scope.projectList=res.data;
				travelRequest.approverAndProjectList=$scope.projectList;
				console.log("projectList: ",$scope.projectList)
				},function(err){
					if(err.status==400){
						console.log(err.data)
						$state.go('login')
					}
					console.log(err)
				}) 


            $scope.empObj = {}
    	
    	    //$scope.empObj.approverName = {};
    		   	
            $scope.flightObj = {};
    	
            $scope.accomodationObj = {}
                        
            $scope.forexObj = {}
     
                      
            $scope.visaObj = {};
            
            $scope.activeForm = []


    			//to convert 12 hrs to 24 hrs
    			var timeConversionTo24Hrs = function(timeIn12){
    				 // var timeIn12 = "11:30 pm"
    				console.log(timeIn12,"tIn12");
    				  var arr = timeIn12.split('');
    				  var hours = arr[0]+arr[1];
    				  var minutes = arr[3]+arr[4];
    				 // var seconds = arr[6]+arr[7];
    				  var ampm = arr[6];
    				  hours = +hours;
    				  if(hours<13){
    				    if(ampm=="p"){
    				      hours += 12;
    				    }
    				  }
    				  var timeIn24 = hours +":" + minutes+":00";
    				  console.log(timeIn24,"in 24");
    				  return timeIn24;
    				}
    			
    			
    			// to convert 24 to 12 hrs
    			var timeConversionTo12Hrs = function(timeIn24){
    			   // var timeIn24 = "17:30:10";
    			    console.log(timeIn24," in 24 hours format");
    			    var ampm = "am";
    			    var time = timeIn24.split(':');
    			    var hours = parseInt(time[0]);
    			    var minutes = time[1];
    			    var seconds = time[2];
    			    
    			    if(hours>12){
    			      hours = hours-12;
    			      ampm = "pm";
    			    }
    			    var timeIn12 = hours + ":" + minutes + ":" + seconds + " "+ampm;
    			    console.log(timeIn12,"in 12 hours format");
    			} 
    			
    			var appendDateTime=function(date, time){
    				var dateTime=date+" "+time;
    				return dateTime;
    			}
    			//initiate date to todays date
//    			var dateOptions=function(){
//    				document.getElementById("checkIn").value=new Date();
//    				document.getElementById("checkOut").value=new Date();
//
//    				}
//    				dateOptions();
    				
    				// change date format to yyyy-MM-dd
    				var formatDate=function(date) {
    				    var d = new Date(date),
    				        month = '' + (d.getMonth() + 1),
    				        day = '' + d.getDate(),
    				        year = d.getFullYear();

    				    if (month.length < 2) month = '0' + month;
    				    if (day.length < 2) day = '0' + day;

    				    return [year, month, day].join('-');
    				}
    				
    				
    				//accomodation check in date

    				$scope.chooseCheckInDate=function(){
    					var inDate=$filter('date')($scope.accomodationObj.checkInDate, "yyyy-MM-dd");
    					var outDate=$filter('date')($scope.accomodationObj.checkOutDate, "yyyy-MM-dd");
    					var t=new Date();
    					var today=formatDate(t);
    					console.log(today);
    					console.log(inDate);
    					if(inDate < today){
    	    				$scope.checkInErr="Please enter date greater or equal to today";
    	    				self.accommodationForm.checkIn.$setValidity('checkIn',false);
    	    			} else if(outDate != null){
    	    				if(inDate > outDate){
    							$scope.checkInErr="Please enter date before Check-out date";
    							self.accommodationForm.checkOut.$setValidity('checkIn',false);
    						}else{
    							$scope.checkIn=inDate;
        	    				$scope.checkInErr="";
        	    				self.accommodationForm.checkIn.$setValidity('checkIn',true);
    						}
    	    			}else{
    	    				$scope.checkIn=inDate;
    	    				$scope.checkInErr="";
    	    				self.accommodationForm.checkIn.$setValidity('checkIn',true);
    	    			}
    					//minimum date
//    					if(outDate==null){
//    						if(inDate!=null){
//        						if(inDate < today){
////        	    				$scope.accomodationObj.checkInDate="";
//        	    				$scope.checkInErr="Please enter date greater or equal to today";
//        	    				self.accommodationForm.checkIn.$setValidity('checkIn',false);
//        	    				}
//        						else{
////        	    				$scope.tomorrow = new Date();
////        	    				console.log($scope.accomodationObj.checkInDate,"chekinDate")
////        	    			    $scope.tomorrow.setDate($scope.accomodationObj.checkInDate.getDate() + 1);
////        	    					    						
////        	    				console.log( $scope.accomodationObj.checkInDate,"$scope.accomodationObj.checkInDate");
////        	    				console.log( $scope.tomorrow," $scope.tomorrow");
//        	    				    						
////        	    				$scope.tomorrow.setDate(inDate.getDate() + 1);
////        	    			    $scope.tomorrow=$filter('date')($scope.tomorrow, "yyyy-MM-dd");;
//        	    					    					    
////        	    			   $scope.accomodationObj.checkOutDate= $scope.tomorrow;
//        	    				$scope.checkIn=inDate;
//        	    				$scope.checkOut=$filter('date')($scope.tomorrow, "yyyy-MM-dd");
//        	    				$scope.checkInErr="";
//        	    				self.accommodationForm.checkIn.$setValidity('checkIn',true);
//        	    				}
//        					}
//        					else{
//        						$scope.checkInErr="";
//        						self.accommodationForm.checkIn.$setValidity('checkIn',true);
//        						}
//    					}
//    					else{
//    						if(outDate < inDate){
//    							$scope.checkOutErr="Please enter date greater to Check-in date";
//    							self.accommodationForm.checkOut.$setValidity('checkOut',false);
//    						}
//    						else{
//    							$scope.checkInErr="";
//    							self.accommodationForm.checkOut.$setValidity('checkOut',false);
//    						}
//    					}
    				} 
    					 


    				//accomodation check out date
    				$scope.chooseCheckOutDate=function(){
    					var outDate=$filter('date')($scope.accomodationObj.checkOutDate, "yyyy-MM-dd");
    					
    					//var t=new Date();
    					var inDate=$filter('date')($scope.accomodationObj.checkInDate, "yyyy-MM-dd");
    					console.log(typeof(inDate),"inDate in accomodation");
    					var t=new Date();
    					var today=formatDate(t);
    					console.log(inDate,"inDate")
    					console.log(today);
    					console.log(outDate,"outDate");
    					//minimum date
    					if(outDate!=null){
    						if(outDate <= inDate){
        						console.log($scope.tomorrow);
//        						$scope.accomodationObj.checkOutDate=$scope.tomorrow;
        					//alert("waring!");
        					$scope.checkOutErr="Please enter date greater to Check-in date";
        					self.accommodationForm.checkOut.$setValidity('checkOut',false);
        					}else{
        					//document.getElementById("toDate").value=d;
        						
        					$scope.checkOut=outDate;
        					console.log($scope.checkOut,"checkOut date of accomoddation");
        					$scope.checkOutErr="";
        					self.accommodationForm.checkOut.$setValidity('checkOut',true);
        					}
    					}
    					else{
    						if(outDate < today){
    							$scope.checkOutErr="Please enter date greater than today";
    							self.accommodationForm.checkOut.$setValidity('checkOut',false);
    						}
    						else{
    							$scope.checkOutErr="";
    							self.accommodationForm.checkOut.$setValidity('checkOut',true);
    						}
    						
    					}
    					

    				} 
 

    				//flight departure date
//    				$scope.showFirstErrorMessage = false;
                    $scope.DepartureDateErr = "";
                    //flight departure date
                    $scope.chooseDepartureDate=function(){
                    	
                           var inDate=$filter('date')($scope.flightObj.departureDate, "yyyy-MM-dd");
                           var outDate=$filter('date')($scope.flightObj.returnDate, "yyyy-MM-dd");
                           
                           var passportIssueDate=$filter('date')($scope.flightObj.passportIssueDate, "yyyy-MM-dd");
                           
                           var temp=new Date();
                           var today=formatDate(temp);
                           console.log(today);
                           console.log(inDate,"inDate");
                           console.log(passportIssueDate,"passportIssueDate");
                         
//                         //minimum date
                           if(outDate==null){
                        	   if(inDate==null){
                                   $scope.DepartureDateErr="";
                                   self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',true);
                               }
                               else{
                                  if(inDate < today){
                                      $scope.DepartureDateErr="Please enter date greater or equal to today";
                                      self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',false);
                                  }else if(inDate == today){
                                	  $scope.DepartureDateErr="";
                                      self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',true);
                                  }
                                  else{
                                           if(passportIssueDate!=null){
                                              if(passportIssueDate > inDate){
                                                 $scope.DepartureDateErr="Depature Date can not be earlier than Passport Issue Date";
                                                 self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',false);
                                                 if(inDate < today){
                                                    $scope.DepartureDateErr="Please enter date greater or equal to today";
                                                    self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',false);
                                                 }
                                                 else{
                                                    $scope.DepartureDateErr="";
                                                    self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',true);
                                                 }
                                              }
                                              else{
                                                  $scope.DepartureDateErr="";
                                                  self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',true);
                                              }
                                           }
                                           else{
                                               $scope.DepartureDateErr="";
                                               self.flightForm.flightDepartureDate.$setValidity('flightDepartureDate',true);
                                           }
                                  }
                               }
                           }
                           else{
                        	   if(outDate < inDate){
                        		   $scope.returnDateErr="Please enter date greater than departure date";
                        	   }
                        	   else{
                        		   $scope.returnDateErr="";
                        		   self.flightForm.flightReturnDate.$setValidity('flightReturnDate',true);
                        	   }
                        	   
                           }
                           
                    }


                    //flight return date

                    $scope.chooseReturnDate=function(){

                    	var passportExpiryDate=$filter('date')($scope.flightObj.passportExpiryDate, "yyyy-MM-dd");
                    	var outDate=$filter('date')($scope.flightObj.returnDate, "yyyy-MM-dd");
                          
                   var today=$filter('date')($scope.flightObj.departureDate, "yyyy-MM-dd");
                          
                   $scope.returnDate=$filter('date')($scope.flightObj.returnDate, "yyyy-MM-dd");
                   $scope.departureDate=$filter('date')($scope.flightObj.departureDate, "yyyy-MM-dd"); 
                   $scope.months = new Date($scope.flightObj.returnDate);
//					
				    $scope.months.setDate($scope.flightObj.returnDate.getDate() + 120);
				    var fourMonths=$filter('date')( $scope.months, "yyyy-MM-dd");
                   var dropdt = new Date($scope.returnDate);
                   var pickdt = new Date($scope.departureDate);
                   var noOfDays = ((dropdt-pickdt ) / (24 * 3600 * 1000));
//                  
                   if(passportExpiryDate==null){
                    	if(today!=null){       
                            if(outDate < today){
                                   $scope.returnDateErr="Please enter date greater than departure date";
                                   self.flightForm.flightReturnDate.$setValidity('flightReturnDate',false);
                            }
                            else if($scope.flightObj.tourType=="international"){
                         	   if(noOfDays<2){
                                    $scope.returnDateErr = "Return Date must be 2 days later from Departure Date.";
                                    self.flightForm.flightReturnDate.$setValidity('flightReturnDate',false);
                                        }
                         	   else{
                         		   $scope.returnDateErr = "";
                         		  self.flightForm.flightReturnDate.$setValidity("flightReturnDate",true);
                         	   }
                            }
                            else if($scope.flightObj.tourType=="domestic"){
                         	   $scope.returnDateErr = "";
                         	  self.flightForm.flightReturnDate.$setValidity("flightReturnDate",true);
                            }
                           else{
                         	   
                                   $scope.returnDateErr = "";
                                   self.flightForm.flightReturnDate.$setValidity("flightReturnDate",true);
                            }
                    	}
                    	else{
                    		if(outDate < today){
                    		 $scope.returnDateErr="Please enter date greater than departure date";
                    		 self.flightForm.flightReturnDate.$setValidity("flightReturnDate",false);
                    		}
                    		else{
                    			$scope.returnDateErr = "";
                    			self.flightForm.flightReturnDate.$setValidity("flightReturnDate",true);
                    		}
                    	}
                   }
                   else{
                	   if(passportExpiryDate <fourMonths){
							$scope.passportExpiryDateErr="Passport expiry date must be 120 days after Return date";
							self.flightForm.passExpiryDate.$setValidity('passExpiryDate',false);
                	   }
                	   else{
                		   $scope.returnDateErr = "";
                		   self.flightForm.flightReturnDate.$setValidity("flightReturnDate",true);
                	   }
                	   
                   }
              
                           
//                         var dropdt = new Date($scope.returnDate);
//                var pickdt = new Date($scope.departureDate);
//                var noOfDays = ((dropdt-pickdt ) / (24 * 3600 * 1000));
//                console.log(noOfDays,"noOfDays");
//               if((noOfDays<2) && ($scope.departureDateErr != "Please enter date greater or equal to today")){
//                    $scope.departureDateErr = "Return Date must be 2 days later from Departure Date.";   
//                    return $scope.departureDateErr;
//               }
                           
                           
                           
//                         document.getElementById("returnDate").value=today;
//                         $scope.showFirstErrorMessage = true;
//                         //alert("waring!");
                           
//                         }else{
//                         //document.getElementById("toDate").value=d;
////                              $scope.returnDate=outDate;
//                         $scope.returnDateErr="";
                           
                           
                    }

//    				//flight DOB
//    				$scope.chooseDOB=function(){
//
//    					//document.getElementById("sample").min=new Date();
//    					var date=document.getElementById("dob").value;
//    					
//    					var t=new Date();
//    					var today=formatDate(t);
//    					//var tISO=d.toISOString();
//    					console.log(today);
//    					console.log(date);
//    					
//    					if(date > today){
//    					document.getElementById("dob").value="";
//    					//alert("waring!");
//    					$scope.dobErr="Please select valid date of birth";
//    					}
//    					$scope.flightObj.dateOfBirth=date+" 00:00:00";
//    					$scope.dobErr="";
//    					
//    				}
    				//flight passport issue date
    				

    				$scope.choosePassportIssueDate=function(){

    				
    					var inDate=$filter('date')($scope.flightObj.passportIssueDate, "yyyy-MM-dd");
    					var departureDate=$filter('date')($scope.flightObj.departureDate, "yyyy-MM-dd");
    					
    				
//    					console.log(departureDate);
//    					console.log(inDate,"$scope.flightObj.passportIssueDate");
    					if($scope.flightObj.departureDate){
	    					  $scope.dayBefore = new Date($scope.flightObj.departureDate);
	     						
	     					  $scope.dayBefore.setDate($scope.flightObj.departureDate.getDate() - 1);
	     					  console.log( $scope.dayBefore," $scope.dayBefore");
	     					 var early=$filter('date')($scope.dayBefore, "yyyy-MM-dd");
    					}
     					 console.log( early,"early")
     					 var temp=new Date();
                           var today=formatDate(temp);
    					//minimum date
    					if(inDate!=null){
    						$scope.years = new Date();
//    						console.log($scope.accomodationObj.checkInDate,"chekinDate")
    					    $scope.years.setDate($scope.flightObj.passportIssueDate.getDate() + (365*10));
    						$scope.flightObj.passportExpiryDate= $scope.years;
    						console.log($scope.years,"$scope.years");
    						console.log($scope.flightObj.passportExpiryDate,"$scope.flightObj.passportExpiryDate");
    						
    						if(departureDate!=null){
    							if(inDate > today){
//        	    					document.getElementById("passportIssueDate").value="";
        	    					$scope.passportIssueDateErr="Invalid issue date";
        	    					self.flightForm.passIssueDate.$setValidity('passIssueDate',false);
        	    					}else{
//        	    					document.getElementById("passportExpiryDate").value=inDate;
//        	    					$scope.flightObj.passportIssueDate=inDate+ " 00:00:00";
        	    						

			    						
        	    					$scope.passportIssueDateErr="";
        	    					self.flightForm.passIssueDate.$setValidity('passIssueDate',true);
        	    					}
    						}
    						else{
    							$scope.passportIssueDateErr="";
    							self.flightForm.passIssueDate.$setValidity('passIssueDate',true);
    						}
    					}
    					else{
    						$scope.passportIssueDateErr="";
    						self.flightForm.passIssueDate.$setValidity('passIssueDate',true);
    					}
    					
    					
    				}



    				$scope.choosePassportExpiryDate=function(){

    					
    					var outDate=$filter('date')($scope.flightObj.passportExpiryDate, "yyyy-MM-dd");
    					
    					//var t=new Date();
    					var today=$filter('date')($scope.flightObj.passportIssueDate, "yyyy-MM-dd");
    					var returnDate=$filter('date')($scope.flightObj.returnDate, "yyyy-MM-dd");
    					
    					console.log(today);
    					console.log(outDate);
    					$scope.months = new Date($scope.flightObj.returnDate);
//						console.log($scope.accomodationObj.checkInDate,"chekinDate")
					    $scope.months.setDate($scope.flightObj.returnDate.getDate() + 120);
					    var fourMonths=$filter('date')( $scope.months, "yyyy-MM-dd");
					    
					    console.log(fourMonths,"fourMonths");
					    
					    $scope.years = new Date();
//						console.log($scope.accomodationObj.checkInDate,"chekinDate")
					    $scope.years.setDate($scope.flightObj.passportIssueDate.getDate() + (365*10));
					    var tenYears=$filter('date')(  $scope.years, "yyyy-MM-dd");
					    console.log(tenYears,"tenYears");
					    
    					//minimum date
					    
    					if(outDate!=null){
    						if(outDate < today){
    						$scope.passportExpiryDateErr="Please enter date greater than passport issue date";
    						self.flightForm.passExpiryDate.$setValidity('passExpiryDate',false);
        					}
    						else if(outDate <fourMonths){
    							$scope.passportExpiryDateErr="Passport expiry date must be 120 days after Return date";
    							self.flightForm.passExpiryDate.$setValidity('passExpiryDate',false);
    						}
    						else if(outDate>tenYears){
        						$scope.passportExpiryDateErr="Passport Expired."
        							
        					}
    						else{
        					$scope.passportExpiryDateErr="";
        					self.flightForm.passExpiryDate.$setValidity('passExpiryDate',true);
        					}
        				}
    					else{
    						$scope.passportExpiryDateErr="";
    						self.flightForm.passExpiryDate.$setValidity('passExpiryDate',true);
    					}
    					
    				}
    				
    				//forex from date
    				$scope.chooseFromDate=function(serviceType,fromDate,toDate){
    					var inDate=$filter('date')(fromDate, "yyyy-MM-dd");
    					var outDate=$filter('date')(toDate, "yyyy-MM-dd");
    					
    					var temp=new Date();
    					var today=formatDate(temp);
    					console.log(today);
    					console.log(inDate);
    					//minimum date
    					
    					
    					if(outDate==null){
    						if(inDate!=null){
        						if(inDate < today){
//        	    					document.getElementById("forexFromDate").value="";
        							if(serviceType == 'forex'){
	        	    					$scope.forexFromDateErr="Please enter date greater or equal to today";
	        	    					self.forexForm.forexFromDate.$setValidity('forexFromDate',false);
        							}else if(serviceType == 'visa'){
        								$scope.visaFromDateErr="Please enter date greater or equal to today";
	        	    					self.VisaForm.visaFromDate.$setValidity('visaFromDate',false);
        							}
    							}else{
    								if(serviceType == 'forex'){
		    	    					$scope.forexFromDateErr="";
		    	    					self.forexForm.forexFromDate.$setValidity('forexFromDate',true);
    								}else if(serviceType == 'visa'){
    									$scope.visaFromDateErr="";
	        	    					self.VisaForm.visaFromDate.$setValidity('visaFromDate',true);
    								}
    	    					}
        					}
        					else{
        						if(serviceType == 'forex'){
	        						$scope.forexFromDateErr="";
	        						self.forexForm.forexFromDate.$setValidity('forexFromDate',true);
        						}else if(serviceType == 'visa'){
        							$scope.visaFromDateErr="";
        	    					self.VisaForm.visaFromDate.$setValidity('visaFromDate',true);
        						}
        					}
    					}
    					else{
    						if(outDate < inDate){
    							if(serviceType == 'forex'){
	    							$scope.forexToDateErr="Please enter date greater or equal to from date";
	    							self.forexForm.forexToDate.$setValidity('forexToDate',false);
    							}else if(serviceType == 'visa'){
    								$scope.visaToDateErr="Please enter date greater or equal to from date";
	    							self.VisaForm.visaToDate.$setValidity('visaToDate',false);
    							}
    						}
    						else{
    							if(serviceType == 'forex'){
	        						$scope.forexFromDateErr="";
	        						self.forexForm.forexFromDate.$setValidity('forexFromDate',true);
        						}else if(serviceType == 'visa'){
        							$scope.visaFromDateErr="";
        	    					self.VisaForm.visaFromDate.$setValidity('visaFromDate',true);
        						}
    						}
    						
    					}
    					if(serviceType == 'visa' && self.VisaForm.visaToDate.$valid && self.VisaForm.visaFromDate.$valid){
    						$scope.visaObj.travellingDays=$scope.GetDays($scope.visaObj.fromDate,$scope.visaObj.toDate);
    					}else if(serviceType == 'forex' && self.forexForm.forexToDate.$valid && self.forexForm.forexFromDate.$valid){
    						$scope.forexObj.forexNoOfDays=$scope.GetDays($scope.forexObj.forexFromDate,$scope.forexObj.forexToDate);
    					}
    				}

    	
    				$scope.chooseToDate=function(serviceType,fromDate,toDate){
    					var outDate=$filter('date')(toDate, "yyyy-MM-dd");
    					
    					//var t=new Date();
    					var fromDate=$filter('date')(fromDate, "yyyy-MM-dd");
//    					console.log(today);
    					console.log(outDate);
    					//minimum date
    					if(fromDate!=null){
    						if(outDate < fromDate){
								if(serviceType == 'forex'){
		        					$scope.forexToDateErr="Please enter date greater or equal to from date";
		        					self.forexForm.forexToDate.$setValidity('forexToDate',false);
								}else if(serviceType == 'visa'){
									$scope.visaToDateErr="Please enter date greater or equal to from date";
		        					self.VisaForm.visaToDate.$setValidity('visaToDate',false);
								}
	        				}else{
	        					if(serviceType == 'forex'){
		        					$scope.forexToDateErr="";
		        					self.forexForm.forexToDate.$setValidity('forexToDate',true);
	        					}else if(serviceType == 'visa'){
									$scope.visaToDateErr="";
		        					self.VisaForm.visaToDate.$setValidity('visaToDate',true);
								}
	        				}
        					
    					}
    					else{
    						if(serviceType == 'forex'){
	    						$scope.forexToDateErr="";
	    						self.forexForm.forexToDate.$setValidity('forexToDate',true);
    						}else if(serviceType == 'visa'){
    							$scope.visaToDateErr="";
	    						self.VisaForm.visaToDate.$setValidity('visaToDate',true);
    						}
    					}
    					if(serviceType == 'visa' && self.VisaForm.visaToDate.$valid && self.VisaForm.visaFromDate.$valid){
    						$scope.visaObj.travellingDays=$scope.GetDays($scope.visaObj.fromDate,$scope.visaObj.toDate);
    					}else if(serviceType == 'forex' && self.forexForm.forexToDate.$valid && self.forexForm.forexFromDate.$valid){
    						$scope.forexObj.forexNoOfDays=$scope.GetDays($scope.forexObj.forexFromDate,$scope.forexObj.forexToDate);
    					}
    					
    					
    				}
    				
    				
    				$scope.selectCheckInTime=function(checkInTime){
    					var fullTime=timeConversionTo24Hrs(checkInTime);
    					$scope.accomodationObj.checkIn=appendDateTime($scope.checkIn, fullTime);
    					//alert($scope.accomodationObj.checkIn);
    					
    				}
    				
    				
    				$scope.selectCheckOutTime=function(checkOutTime){
    					var fullTime=timeConversionTo24Hrs(checkOutTime);
    					$scope.accomodationObj.checkOut=appendDateTime($scope.checkOut, fullTime);
    					//alert($scope.accomodationObj.checkOut);
    					
    				}

        $scope.clickHand = function() {
            console.log($scope);
        }




            //All the error messages and dropdown fields data is coming from here
       $scope.daysessions=[];
            userAuthentication.errMsg().then(function (res) {
                $scope.err = res;
                $scope.requestObject = res.data;
                $scope.intervals = $scope.requestObject.timeIntervals;
                // $scope.intervals=$scope.err.data.timeIntervals[0];
//                $scope.checkInTime = $scope.requestObject.timeIntervals[0];
//                $scope.checkOutTime = $scope.requestObject.timeIntervals[0];

                $scope.requestObject = res.data;
                $scope.personalDetails = $scope.requestObject.personalDetails;

                $scope.requestDetails = $scope.requestObject.requestDetails;
                $scope.accomodation = $scope.requestDetails.accomodation;
                $scope.taxi = $scope.requestDetails.taxi;
                $scope.flight = $scope.requestDetails.flight;
                $scope.forex = $scope.requestDetails.forex;
                $scope.daysessions=$scope.requestDetails.daySessions;
                 console.log("daysessions:", $scope.daysessions);

                // console.log("err:", $scope.forex);

                // $scope.city = res.data.requestDetails.accomodation.city;
                // console.log($scope.city)

            });

            $scope.errMsg = "errMsg";
         

// Get all records for the approver to take action
            $scope.approverId=$scope.user.employeeId;
        	$scope.requestToApprove=[]; 
        	
        	 $scope.getTravelDeskRecords = function(){
    			$scope.travelDeskObjPending = [], $scope.travelDeskObjCompleted = [], $scope.travelDeskObjHold = [];
				 //debugger;
				 travelAdmin.getTravelDeskRecords().then(function(res){
					 //debugger;
					 console.log("travel Desk Records",res.data);
					 $scope.mainObj = res.data.listBO;
					 $scope.allTravelDeskObj = $scope.mainObj.actionDetailsBO || $scope.mainObj.mainRequestBO;
					 
					 if($scope.mainObj.actionDetailsBO){
						 $scope.mainObj.mainRequestBO.map(function(key, index) {
							 $scope.allTravelDeskObj[index].requestId = key.requestId;
							 $scope.allTravelDeskObj[index].createdOn = new Date(key.createdOn);
							 $scope.allTravelDeskObj[index].employeeName = $scope.mainObj.employeeDetailsBO[index].employeeName;
						 });
				 	 }
					 $scope.allTravelDeskObj.map(function(key, index) {
						 if(key.currentStatus == 'On-Hold'){
							 $scope.travelDeskObjHold.push($scope.allTravelDeskObj[index]);
						 }else if(key.currentStatus == 'Completed'){
							 $scope.travelDeskObjCompleted.push($scope.allTravelDeskObj[index]);
						 }else if(key.currentStatus == 'Pending with Travel' || key.currentStatus == 'Approved' || 
								 key.currentStatus == 'Issued by HC' || key.currentStatus == 'Issued by Finance' || key.currentStatus == 'Issued by HC,Finance'){
							 $scope.travelDeskObjPending.push($scope.allTravelDeskObj[index]);
						 }
					 });
					 
					 $scope.totalItems = $scope.allTravelDeskObj.length;
					 console.log("Travel Object",$scope.allTravelDeskObj)
					 
						
					},function(err){
						if(err.status==400){
							console.log(err.data)
							$state.go('login')
						}
					})
			 }
			 
			 $scope.getHcOpsRecords = function(){
				 travelAdmin.getHcOpsRecords().then(function(res){
					 console.log("Hc-OPs Records",res.data);
					 $scope.HcopsObj = res.data;
					},function(err){
						if(err.status==400){
							console.log(err.data)
							$state.go('login')
						}
					})
			 }
			 
			 $scope.getFinanceRecords = function(){
				 travelAdmin.getFinanceRecords().then(function(res){
					 console.log("Finance Records",res.data);
					 $scope.FinanceObj = res.data;
					},function(err){
						if(err.status==400){
							console.log(err.data)
							$state.go('login')
						}
					})
			 }
			
			
        	
        	
        	function filterResults(data){
        		for(var i = 0; i<data.length;i++){
        			console.log(data[i].currentStatus,$scope.user.grade)
        			switch(data[i].currentStatus) {
        			  	case 'Partner Approval Pending':
        			  		if($scope.user.grade == 'M7') $scope.requestToApprove.push(data[i]);
        			  		break;
        			  	case 'Manager Approval Pending':
        			  		if($scope.user.grade == 'M5' || $scope.user.grade == 'M6') $scope.requestToApprove.push(data[i]);
        			  		break;
        			}
        		}
			
        	}
        	
            $scope.checkList = function(){
            	travelRequest.getAllRecords($scope.approverId).then(function(res){
            
            	console.log("get all records exectuted");
        		if(res.data.requestListVO.length==0){
        			//alert("No requests found");
        			//$state.go('home');
        		}
        		else{
        			filterResults(res.data.requestListVO);
        		}
        	},function(err){
        		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
        		console.log("error: ",err);
        	})
            }
            
            $scope.checkTravelList = function(){
            	//debugger;
            	travelRequest.getAllRecords($scope.approverId).then(function(res){
                $scope.isTravelView = $scope.user.istravelView;
            	console.log("get all records exectuted");
        		if(res.data.requestListVO.length==0){
        			//alert("No requests found");
        			//$state.go('home');
        		}
        		
        		
        		else{
        			for(var i = 0; i<res.data.requestListVO.length;i++){
        				if(res.data.requestListVO[i].currentStatus=="Approved" || res.data.requestListVO[i].currentStatus == "Completed" ||
        						res.data.requestListVO[i].currentStatus=="On Hold" ||  res.data.requestListVO[i].currentStatus=="Pending With Hc-ops" || res.data.requestListVO[i].currentStatus=="Pending With Finance"){
        					
        					$scope.requestToApprove.push(res.data.requestListVO[i]);
        				}
        			}
        			console.log($scope.requestToApprove)
        		}
        	},function(err){
        		if(err.status==400){
        			console.log(err.data)
        			$state.go('login')
        		}
        		console.log("error: ",err);
        	})
            }

        	
 


        	// Get request to take action on 
            $scope.pendingItems;
        	$scope.requestToApproveDetails;
        	$scope.aq;
        	$scope.getRequestToApproveDetails=function(requestId){
        		
            	travelRequest.getRequestDetails(requestId).then(function(res){
            		
            		$scope.requestToApproveDetails=res.data;
            		$scope.requestToApproveDetails.pendingItems = $scope.pendingItems;
            		$state.go('actionOnRequest')
            		//$("rejectRequestModal").modal("show");
            		
            		console.log("request to approve Details inside function",$scope.requestToApproveDetails);
            		travelRequest.request = $scope.requestToApproveDetails;
            	},function(err){
            		if(err.status==400){
            			console.log(err.data)
            			$state.go('login')
            		}
            	});
            }
        	
        	
        	$scope.getpendingItems= function(pendingItems){
        		$scope.pendingItems = pendingItems;
        	}
        	console.log("service variable ",travelRequest.request)
//        	console.log("request to approve Details ",$scope.requestToApproveDetails);
        	
        	

        	$scope.noAccessMsg="";
          
             $scope.gradeCheckForFlight = function(prefClass){
                   switch($scope.user.grade)
                   {
                    case "M0":
                    case "M1":
                    case "M2":
                    case "M3":
                    case "M4":
                    case "M5":
                    case "M6": if(prefClass!="Economy"){
                                 $scope.noAccessMsg="Please take authorisation from Manager";
                                                    }
                                                   else{
                                                        $scope.noAccessMsg="";
                                }break;
                   
                   }


            }

           //flight DOB
             $scope.chooseDOB=function(){
//            	$scope.flightObj.dateOfBirth=$filter('date')($scope.flightObj.dateOfBirth, "yyyy-MM-dd");
            	  $scope.DOB=$filter('date')($scope.flightObj.dateOfBirth, "yyyy-MM-dd");
                  console.log($scope.DOB);
                  
                      $scope.age;
                       // $scope.invalidDOB="";

                      var today = new Date();
                      console.log(today);
                      var birthDate = new Date($scope.DOB);
                      console.log(birthDate)
                      
                     $scope.age = today.getFullYear() - birthDate.getFullYear();
                      console.log($scope.age);
                      var m = today.getMonth() - birthDate.getMonth();
                      
                      if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                          $scope.age--;
                      }
                      
                  //document.getElementById("sample").min=new Date();
                  var date=document.getElementById("dob").value;
                  
                  console.log(date,"date");
                  var t=new Date();
                  var today1=formatDate(t);
                  //var tISO=d.toISOString();
                  console.log(today1,"today1");
                  console.log(date);
                  
                  if($scope.DOB > today1){
                         console.log("in if");
                  document.getElementById("dob").value="";
                  //alert("waring!");
                  $scope.invalidDOB="Please select valid date of birth";
                  self.flightForm.$invalid=true;
                  }
                  else if($scope.age<18)
                      {
                         
                          $scope.invalidDOB="Your not eligible for passport";
                          self.flightForm.$invalid=true;
                          console.log($scope.invalidDOB);
                      }
                      else{
                         $scope.invalidDOB="";
                      }
//                $scope.dob=date;
//                $scope.dobErr="";
                 
           }


        	
        	
            $scope.accomodationCheckInDate;
            $scope.accomodationCheckOutDate;
            $scope.accomodationCheckInTime;
            $scope.accomodationCheckOutTime;
            $scope.forexObj.forexNoOfDays;
            $scope.addDays = function (fromDate, toDate) {
            	var to = new Date(toDate);
            	var from = new Date(fromDate);
            	var oneDay=24*60*60*1000; // hr * min * sec * millisec  
            	var days = Math.round(Math.abs((to.getTime()-from.getTime())/(oneDay)));
            	$scope.forexObj.forexNoOfDays=days;
            	alert($scope.forexObj.forexNoOfDays)
            	if($scope.forexObj.forexCountry=="USA"){
            		//$scope.currency="USD";
            		$scope.amount=60;
            	}
            	$scope.forexObj.forexAmount=(days * $scope.amount)
            	//$scope.forexObj.forexCountry=$scope.forexObj.forexCountry.country
            	console.log("forexAmount=",$scope.forexObj.forexAmount)
//            	var d1 = new Date(fromDate);
//            	var d2 = new Date(toDate);
//            	var d1 = moment(fromDate);
//            	var d2 = moment(toDate);
//            	var days = moment.duration(d2.diff(d1)).asDays();
//            	$scope.forexObj.forexNoOfDays=days;
//            	alert($scope.forexObj.forexNoOfDays);
            } 
            
           

            //Approver id from approver name:
            
//		Countries validation
            $scope.checkCountry=function(){
            	var origin=$scope.flightObj.originCountry.toUpperCase();
            	var destination=$scope.flightObj.destinationCountry.toUpperCase();
            	if(origin == destination){
            		$scope.originCountryErr="Origin and destination country should be different";
            		$scope.flightObj.originCountry=origin;
            		$scope.flightObj.destinationCountry=destination;
            	}
            	else{
            		$scope.originCountryErr="";
            	}
            	if($scope.flightObj.originCountry=="" || $scope.flightObj.destinationCountry==""){
            		$scope.originCountryErr="";
            	}
            }
            
//            Cities validation
            $scope.checkCity=function(){
            	if($scope.flightObj.departureLocation && $scope.flightObj.destinationLocation){
	            	var originCity=$scope.flightObj.departureLocation.toUpperCase();
	            	var destinationCity=$scope.flightObj.destinationLocation.toUpperCase();
	            	if(originCity == destinationCity){
	            		$scope.originCityErr="From and to city should be different";
	            		self.flightForm.flightFromCity.$setValidity('flightFromCity',false);
	            		self.flightForm.flightToCity.$setValidity('flightToCity',false);
	            		//$scope.flightObj.departureLocation=originCity;
	            		//$scope.flightObj.destinationLocation=destinationCity;
	            	}
	            	else{
	            		if($scope.flightObj.departureLocation==null || $scope.flightObj.destinationLocation==null){
	                		$scope.originCityErr="";
	                	}
	            		$scope.originCityErr="";
	            		self.flightForm.flightFromCity.$setValidity('flightFromCity',true);
	            		self.flightForm.flightToCity.$setValidity('flightToCity',true);
	            	}
            	}
            	
            }
            
            $scope.checkAccomodationCity=function(origin,destination){
            	var originCity=origin.toUpperCase();
            	if(destination) var destinationCity=destination.toUpperCase();
            	if(originCity == destinationCity){
            		$scope.accomodationCityErr="Country and City should be different";
            		self.accommodationForm.accomodationCountry.$setValidity('accomodationCountry',false);
            		self.accommodationForm.accommodationCity.$setValidity('accommodationCity',false);
            		$scope.accomodationObj.country=originCity;
            		$scope.accomodationObj.city=destinationCity;
            	}
            	else{
            		if($scope.accomodationObj.country==null || $scope.accomodationObj.city==null){
                		$scope.accomodationCityErr="";
                	}
            		$scope.accomodationCityErr="";
            		self.accommodationForm.accomodationCountry.$setValidity('accomodationCountry',true);
            		self.accommodationForm.accommodationCity.$setValidity('accommodationCity',true);
            	}
            	
            }
            
             $scope.travelRequest= {
             	    requestId: "",
             	    requestedBy: $scope.user.employeeId,
             	    //requestType: $scope.self,
             	    //requestedFor : $scope.empObj.employeeName,
             	    //projectName : $scope.projectName,
             	    currentStatus: "Manager Approval Pending",          	  
             	    remark: "",
             	    actionOnRequest: "",
             	    accomodationRequest : null,
             	    flightRequest : null,
             	    forexRequest : null,
             	    visaRequestVO : null
             }
             
             
             
 
			$scope.closeMsgPopup = function(){
				$state.go('home');
			}
			
			function sendEmail(empObj){
				
				emailPayload.employeeregd.name = empObj.employeeName;
				emailPayload.employeeregd.employeeNumber = $scope.empId;
				emailPayload.employeeregd.projectName = empObj.project.projectName;
				emailPayload.employeeregd.isosInfo = $scope.isos.isosCardexist ? true : false;
				emailPayload.approver.approverName = $scope.empObj.approver.approverName;
				emailPayload.approver.approverEmailID = $scope.empObj.approver.approverEmail || "kumari.soni@capco.com";
				return emailNotification.newRequest(emailPayload);
//				
			}
        
             $scope.send = function (status) {
            	 if($scope.travelRequest.tourType == "international"){
            		 self.isosData.$setSubmitted();
            	 }
            	 if(self.mainForm.$valid && self.isosData.$valid != false){
            		$scope.travelRequest.forexRequest = $scope.checkObjIsEmpty($scope.forexObj) ? $scope.forexObj : null;
            		$scope.travelRequest.flightRequest = $scope.checkObjIsEmpty($scope.flightObj) ? $scope.flightObj : null;
	            	$scope.travelRequest.visaRequestVO = $scope.checkObjIsEmpty($scope.visaObj) ? $scope.visaObj : null;
	            	$scope.travelRequest.accomodationRequest = $scope.checkObjIsEmpty($scope.accomodationObj) ? $scope.accomodationObj : null;
	            	$scope.travelRequest.approverId = $scope.empObj.approver.approverId;
	            	$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
	            	$scope.travelRequest.purposeOfTravel = $scope.empObj.purposeOfTravel;
	            	$scope.travelRequest.projectName = $scope.empObj.project.projectName;
	            	$scope.travelRequest.projectCode = $scope.empObj.project.projectCode;
	            	$scope.travelRequest.activityCode = $scope.empObj.project.activityCode;
	            	$scope.travelRequest.typeOfProject = $scope.empObj.typeOfProject;
	            	$scope.travelRequest.employeeLocation = $scope.empObj.employeeLocation;
	            	$scope.travelRequest.requestType = $scope.cell.evaluator;
	            	
	            	if(status == 'draft'){
	            		$scope.travelRequest.currentStatus = "Draft";
	            	} else {
	            		$scope.travelRequest.currentStatus = "Manager Approval Pending";
	            	}
	            	
	            	console.log("Approver ID : " + $scope.travelRequest.approverId);
	            	console.log("Approver EMPID : " + $scope.empObj.approver.approverId);
	            	console.log("data--",$scope.travelRequest);
	            	travelRequest.createRecord($scope.travelRequest)
	                    .then(function (data) {
	                    	//$('#myModal').modal('show');
	                    	if(status == 'draft'){
	                    		$scope.serverResponse = "Request has been saved";
	                    	}else{
	                    		$scope.serverResponse = "Request sent to Approver 1";
	                    		sendEmail($scope.empObj);
	                    	}
	                    	$state.go('myrequests');
	                    	
	                    	
	                    	//$state.go('home');
	                    	
	                    },function(err){
	                    	console.log("error ",err)
	                    	$scope.serverResponse = "Something went wrong. Please try again";
	                    	$('#myModal').modal('show');
	                    	if(err.status==400){
	                			console.log(err.data)
	                			//$state.go('login')
	                		}
	                    });
            	 }
            }
             	
             
             
             $scope.save = function () {
             	$scope.travelRequest.approverId= $scope.empObj.approver.approverId;
             	$scope.travelRequest.requestedFor = $scope.empObj.employeeName;
             	$scope.travelRequest.currentStatus = "Pending";
             	$scope.travelRequest.projectName = $scope.empObj.project.projectName;
             	$scope.travelRequest.projectCode = $scope.empObj.project.projectCode;
             	$scope.travelRequest.activityCode = $scope.empObj.project.activityCode;
             	if(!$scope.checkObjIsEmpty($scope.forexObj)){
            		$scope.forexObj.forexCountry=$scope.forexObj.forexCountry.country;
            	}
             	console.log("Approver ID : " + $scope.travelRequest.approverId);
             	console.log("Approver EMPID : " + $scope.empObj.approver.approverId);
             	console.log("data--",$scope.travelRequest);
             	travelRequest.createRecord($scope.travelRequest)
                     .then(function (data) {
                         console.log(data);
                         alert("Request Saved as draft ",data.requestId);
                         $state.go('home');
                     },function(err){
                     	console.log("error ",err)
                     	alert("Request not saved, Please enter all the fields in request details.");
                     	if(err.status==400){
                			console.log(err.data)
                			$state.go('login')
                		}
                     });
             }
             
             
             $scope.back = function(){
            	 $state.go('home');
             }
  	 
            $scope.getProjectName=function(){
            	console.log("projectList",$scope.projectList);
            	for(var i=0;i<$scope.projectList.listOfProjectDetails.length;i++){
            		if($scope.projectList.listOfProjectDetails[i].projectCode==$scope.empObj.project.projectCode){
            			$scope.empObj.project.projectName = $scope.projectList.listOfProjectDetails[i].projectName;
            			$scope.empObj.project.activityCode = $scope.projectList.listOfProjectDetails[i].activityCode;
            		}
            		
            	}
//            	 for(var i = 0; i<$scope.projectList.listOfApprovers.length; i++){
//                	 if($scope.projectList.listOfApprovers[i].approverName==$scope.empObj.approverName){
//             			
//             			$scope.empObj.approverId=$scope.projectList.listOfApprovers[i].approverId;
//             			
//             		}
//
//                 }
                 console.log($scope.empObj);
            }
            //javascript bootstrap tooltip
            $(document).ready(function(){
                $('[data-toggle="tooltip"]').tooltip();   
            });
            
            $scope.travelRequest.requestType="Self";
            $scope.requestType=[{type:"Self"},{type:"Other"}]
	        $scope.getrequestType=function(){
            	return $scope.requestType;
            }
            $scope.cell={
            	evaluator:"Self"
            }
	
            $scope.empName="Employee Name:";
            $scope.empObj.employeeName=$scope.user.employeeName;
            $scope.empId = $scope.user.employeeId;
        	$scope.empObj.employeeMobileNumber=$scope.user.employeeMobileNumber;
         	
         	
            $scope.setPersonalData=function(){
            	 if($scope.cell.evaluator=="Self"){
         			$scope.travelRequest.requestType="Self";
         		}		
         		else{
         			$scope.travelRequest.requestType="Other";
         		}
            	if($scope.cell.evaluator=="Self"){
            	$scope.empObj.employeeName=$scope.user.employeeName;
            	$scope.empObj.employeeMobileNumber=$scope.user.employeeMobileNumber;
            	
             	$scope.empName="Employee Name:";
             	$scope.empObj.employeeLocation=null;
             	$scope.empObj.approver=null;
             	$scope.getProjectName();
             	console.log($scope.empName);
            }else if($scope.cell.evaluator=="Other"){
            	$scope.empObj.employeeName="";
            	$scope.empObj.employeeMobileNumber="";
            	$scope.empObj.employeeLocation=null;
            	$scope.empObj.project="";
            	$scope.empObj.approver=null;
            	$scope.empName="Requested For:";
            	console.log($scope.empName);
            }
            }
            
            $scope.GetDays=function(fromDate, toDate){
            	fromDate = new Date($filter('date')(fromDate, "yyyy-MM-dd"));
            	toDate = new Date($filter('date')(toDate, "yyyy-MM-dd"));
                var dropdt = new Date(fromDate);
                var pickdt = new Date(toDate);
                var noOfDays = ((pickdt- dropdt) / (24 * 3600 * 1000));
                noOfDays = noOfDays+1;//including day of drop
                noOfDays = noOfDays+"";
                return noOfDays;
        }

        $scope.cal=function(){
        	$scope.perdiem=0;
            $scope.forexObj.forexFromDate=new Date($scope.forexObj.forexFromDate);
            $scope.forexObj.forexToDate=new Date($scope.forexObj.forexToDate);
            $scope.from=$filter('date')($scope.forexObj.forexFromDate, "yyyy-MM-dd");
            $scope.forexObj.forexFromDate=$scope.from;
            console.log($scope.from);
            $scope.toDate=$filter('date')($scope.forexObj.forexToDate, "yyyy-MM-dd"); 
            $scope.forexObj.forexToDate=$scope.toDate;
            console.log($scope.toDate);
	        if($scope.toDate){
	             $scope.forexObj.forexNoOfDays=$scope.GetDays($scope.forexObj.forexFromDate,$scope.forexObj.forexToDate);        
	        } 
	        for(var i = 0; i<$scope.projectList.perDeimList.length; i++){
	       	 if($scope.projectList.perDeimList[i].country==$scope.forexObj.forexCountry.country){
	    			
	    			$scope.perdiem=$scope.projectList.perDeimList[i].perdiem;
	    			$scope.currency=$scope.projectList.perDeimList[i].currency;
	    		}
	       	
	        }
	        $scope.amount=$scope.forexObj.forexNoOfDays * $scope.perdiem ;
	        $scope.forexObj.forexAmount=$scope.amount;
	        
	        console.log("forex perdiem ",$scope.forexObj.forexAmount)
    }

        $scope.activePersonalForm = 0;
        
        var self = this;
        self.personal = "personal";
        //self.accommodationForm = "accommodationForm";
        self.visaForm = "visaForm";
        self.mainForm = "mainForm";
        self.forexForm = "forexForm";
        self.taxiForm = "taxiForm";
        self.isosData = "isosData";
        self.flightForm = "flightForm";
        self.service = {};
        //self.savedData = {};
//        $scope.autoActiveTab = function(){
//        	if($scope.travelRequest.tourType == 'domestic'){
//        		self.service.type = "flight";
//        		angular.copy({},self.savedData);
//        	}else{
//        		if(self.savedData.visaAvailable){
//            		self.service.type = 'visa';
//            	}else if(self.savedData.flightAvailable){
//            		self.service.type = 'flight';
//            	}else if(self.savedData.forexAvailable){
//            		self.service.type = 'forex';
//            	}else if(self.savedData.hotelAvailable){
//            		self.service.type = 'hotel';
//            	}else {
//            		self.service.type = "";
//            	}
//        	}
//        }
        $scope.autoActiveTab = function(){
        	if($scope.travelRequest.tourType == 'domestic'){
        		self.service.type = "flight";
        	}else{
        		self.service.type = 'visa';
        	}
        }
        $scope.resetForm = function(formType){
        	switch (formType) {
	            case 'hotel':
	            	angular.copy({},$scope.accomodationObj);
	            	self.accommodationForm.$setPristine();
	                break;
	            case 'flight':
	            	angular.copy({},$scope.flightObj);
	            	self.flightForm.$setPristine();
	                break;
	            case 'forex':
	            	angular.copy({},$scope.forexObj);
	            	$scope.forexObj.previouslyTravelledwithcapco = null;
	            	self.forexForm.$setPristine();
	                break;
	            case 'visa':
	            	angular.copy({},$scope.visaObj);
	            	self.VisaForm.$setPristine();
	                break;
	            case 'Self':
	            	delete $scope.empObj.employeeLocation;
	            	delete $scope.empObj.approver;
	            	delete $scope.empObj.project;
	            	delete $scope.empObj.employeeMobileNumber;
	            	self.personal.$setPristine();
	            	break;
	            case 'Other':
	            	angular.copy({},$scope.empObj);
	            	break;
        	}
        }
       
        $scope.checkObjIsEmpty = function(obj){
        	return Object.keys(obj).length;
        }
        $scope.returnSessions=$scope.daysessions;
        $scope.changeSession=function(session){
    		$scope.retString=$scope.flightObj.returnDate.toDateString();
    		$scope.depString=$scope.flightObj.departureDate.toDateString();
    		if($scope.depString==$scope.retString /*&& $scope.flightObj.returnTime==session*/){
    			if(session!='Night'){
    				if($scope.flightObj.departureTime=='Afternoon'){
    					$scope.returnSessions=['Evening','Night'];
    				}
    				else if(session=='Evening'){
    					$scope.returnSessions=['Night'];
    				}
    				else if(session=='Morning'){
        				$scope.returnSessions=['Afternoon','Evening','Night'];
        			}
    			/*self.mainForm.flightReturnTime.$setValidity("flightReturnTime",false);
    			$scope.returnSameErr="Departure time and return time cannot be same.";*/
    			}
    			else {
    				$scope.tomorrow = new Date();
					console.log($scope.flightObj.departureDate,"$scope.flightObj.departureDate")
				    $scope.tomorrow.setDate($scope.flightObj.departureDate.getDate() + 1);
					
					console.log( $scope.flightObj.departureDate,"$scope.flightObj.departureDate");
					console.log( $scope.tomorrow," $scope.tomorrow");
					
					$scope.tomorrow.setDate($scope.flightObj.departureDate.getDate() + 1);
				    $scope.tomorrow=$filter('date')($scope.tomorrow, "yyyy-MM-dd");;
				    $scope.nextDay=new Date($scope.tomorrow);
				    $scope.flightObj.returnDate= $scope.nextDay;
				    $scope.returnSessions=['Morning','Afternoon','Evening','Night'];
    			}
            }
    		else if($scope.depString==$scope.retString){
    			self.flightForm.flightReturnTime.$setValidity("flightReturnTime",true);
    			$scope.returnSameErr="";
    		}
    		else{
    			$scope.returnSessions=['Morning','Afternoon','Evening','Night'];
    		}
   	
    }
        function checkFormValidity(formType){
        	switch (formType) {
        		case 'hotel':
        			self.accommodationForm.$setSubmitted();
        			break;
        		case 'taxi':
        			self.taxiForm.$setSubmitted();
        			break;
        		case 'flight':
        			self.flightForm.$setSubmitted();
        			break;
        		case 'forex':
        			self.forexForm.$setSubmitted();
        			break;
        		case 'visa':
	            	self.VisaForm.$setSubmitted();
	                break;
        		}
        }
        
        $scope.TabClick = function(formName){
        	checkFormValidity(formName);
	        if(formName!=undefined){
		        if(self.personal.$valid){
			        self.activePersonalForm = 1;
			        if(self.mainForm.$valid){
			        	self.activePersonalForm = 2;
			        }
		        }
	        }

        }
       
      
      $scope.bindDateAndTime=function(){
//    	  if($scope.accommodationCheckBox == true){
    	  if(self.service.type == 'hotel' && self.accommodationForm.$valid){
    		  		console.log("Helllllooooooooooo");
    		  		console.log($scope.accomodationObj.checkInTime,"$scope.accomodationObj.checkInTime");
    		  		console.log($scope.accomodationObj.checkOutTime,"$scope.accomodationObj.checkOutTime");
    		  		
    		  		console.log($scope.accomodationObj.checkInDate,"$scope.accomodationObj.checkIn");
    		  		console.log($scope.accomodationObj.checkOutDate,"$scope.accomodationObj.checkOut");
    		  		
    		  		$scope.inDate=$filter('date')($scope.accomodationObj.checkInDate, "yyyy-MM-dd"); 
    		  		$scope.outDate=$filter('date')($scope.accomodationObj.checkOutDate, "yyyy-MM-dd"); 
    		  		
    		  		var fullTime=timeConversionTo24Hrs($scope.accomodationObj.checkInTime);
    		  		$scope.accomodationObj.checkIn=appendDateTime($scope.inDate, fullTime);
					//alert($scope.accomodationObj.checkIn);
				
					var fullTime=timeConversionTo24Hrs($scope.accomodationObj.checkOutTime);
					$scope.accomodationObj.checkOut=appendDateTime($scope.outDate, fullTime);
					
					console.log($scope.accomodationObj.checkIn,"$scope.accomodationObj.checkIn");
					console.log($scope.accomodationObj.checkOut,"$scope.accomodationObj.checkOut");
					
					//alert($scope.accomodationObj.checkOut);
    	  }
    	}
      
      
      
    } )