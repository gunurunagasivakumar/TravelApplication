angular.module("myApp")
       .controller("editRequestCtrl", function ($scope, $location,$state, userAuthentication, travelRequest,$filter,editRequestJsonTransfer,jsonTransfer){
    	   var self = this;
    	   self.isosData = "isosData";
    	   //$scope.isos.isosCardexist = parseInt($scope.isos.isosCardexist);
    	   $scope.cell = {};
    	   self.service = {};
    	   $scope.empObj = {'project':{}};
    	   $scope.isos = {};
           $scope.accomodationObj={};
           $scope.flightObj={};
           $scope.forexObj={};
           $scope.visaObj = {};
           $scope.minDate = new Date();
    	   
           $scope.classValue1 = "active";
           $scope.accomodationCheckInDate;
           $scope.accomodationCheckOutDate;
           $scope.accomodationCheckInTime;
              $scope.accomodationCheckOutTime;
              $scope.forexObj.forexNoOfDays;
        //approverList
           $scope.user={};
           $scope.projectList={};
           $scope.user=jsonTransfer.getInfo();
            $scope.editRequestId;
            $scope.errMsg = "errMsg";
            $scope.checkObjIsEmpty = function(obj){
            	if(obj) return Object.keys(obj).length;
            	else return null;
            }
            $scope.travelRequest= {
                requestId: "",
                requestedBy: $scope.user.employeeId,
                //requestType: $scope.self,
                //requestedFor : $scope.empObj.employeeName,
                //projectName : $scope.projectName,
                currentStatus: "Manager Approval Pending",                
                 remark: "",
                actionOnRequest: "Edit",
              accomodationRequest : null,
              flightRequest : null,
              forexRequest : null,
              visaRequestVO : null
                   }
            
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
                   console.log(err)
             })
               
         //All the error messages and dropdown fields data is comming from here
          userAuthentication.errMsg().then(function (res){
              $scope.err = res;
              $scope.requestObject = res.data;
              $scope.intervals = $scope.requestObject.timeIntervals;
              $scope.personalDetails = $scope.requestObject.personalDetails;
              $scope.requestDetails = $scope.requestObject.requestDetails;
              $scope.accomodation = $scope.requestDetails.accomodation;
              $scope.taxi = $scope.requestDetails.taxi;
              $scope.flight = $scope.requestDetails.flight;
              $scope.forex = $scope.requestDetails.forex;
              $scope.visa = $scope.requestDetails.visa;

              // console.log("err:", $scope.forex);

              // $scope.city = res.data.requestDetails.accomodation.city;
              // console.log($scope.city)

          });
           
           
           
          //Null or send object

           $scope.resetForm = function(formType){
           	switch (formType) {
   	            case 'hotel':
   	            	angular.copy({},$scope.accomodationObj);
   	                break;
   	            case 'flight':
   	            	Object.keys($scope.flightObj).forEach(function(itm){
	            		if(itm != "tourType") delete $scope.flightObj[itm];
            		});
   	                break;
   	            case 'forex':
   	            	angular.copy({},$scope.forexObj);
   	                break;
   	            case 'visa':
   	            	angular.copy({},$scope.visaObj);
   	                break;
   	            case 'Self':
   	            	delete $scope.empObj.employeeLocation;
	            	delete $scope.empObj.approver;
	            	delete $scope.empObj.project;
	            	delete $scope.empObj.employeeMobileNumber;
   	            case 'Other':
   	            	angular.copy({},$scope.empObj);
   	            	break;
           	}
           }
           

           $scope.collapseAccordion = function (accordionName) {


               if (accordionName == "accomodationAccordion") {
                   
                   if ($scope.accomodationAccordion == "panel-collapse collapse") {
                       $scope.accomodationAccordion = "panel-collapse collapse in";
                       $scope.accomodationStatus = true;
                   } else {
                       $scope.accomodationAccordion = "panel-collapse collapse";
                       $scope.accomodationStatus= false;
                   }
               }

               if (accordionName == "taxiAccordion") {

                   if ($scope.taxiAccordion == "panel-collapse collapse") {
                       $scope.taxiAccordion = "panel-collapse collapse in";
                       $scope.taxiStatus = true;
                   } else {
                       $scope.taxiAccordion = "panel-collapse collapse";
                       $scope.taxiStatus = false;
                   }
               }

               if (accordionName == "flightAccordion") {

                   if ($scope.flightAccordion == "panel-collapse collapse") {
                       $scope.flightAccordion = "panel-collapse collapse in";
                       $scope.flightStatus = true;
                   } else {
                       $scope.flightAccordion = "panel-collapse collapse";
                       $scope.flightStatus = false;
                   }
               }

               if (accordionName == "forexAccordion") {

                   if ($scope.forexAccordion == "panel-collapse collapse") {
                      $scope.forexAccordion = "panel-collapse collapse in";
                       $scope.forexStatus = true;
                   } else {
                       $scope.forexAccordion = "panel-collapse collapse";
                       $scope.forexStatus = false;
                   }
               }
               
               if (accordionName == "visaAccordion") {

                   if ($scope.visaAccordion == "panel-collapse collapse") {
                       $scope.visaAccordion = "panel-collapse collapse in";
                       $scope.visaStatus = true;
                   } else {
                       $scope.visaAccordion = "panel-collapse collapse";
                       $scope.visaStatus = false;
                   }
               }


           }


          
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
                         //to append 0 for time less than 12 noon
                         if(hours<12){
                            hours="0"+hours;
                         }
                         var timeIn12 = hours + ":" + minutes +" "+ampm;
                         console.log(timeIn12,"in 12 hours format");
                         return timeIn12;
                     } 
         

                //alert(editRequestJsonTransfer.getEditRequestInfo());
              $scope.travelRequest = editRequestJsonTransfer.getEditRequestInfo();
                console.log("after transfer edit",$scope.travelRequest);
                //emp object
                $scope.empObj.employeeName=$scope.travelRequest.requestedFor;
                $scope.empObj.employeeMobileNumber=parseInt($scope.travelRequest.mobileNumber) || parseInt($scope.travelRequest.empData[0].employeeMobileNumber)//$scope.travelRequest.
                $scope.empObj.employeeLocation=$scope.travelRequest.employeeLocation || $scope.travelRequest.empData[0].employeeLocation;
                $scope.empObj.project.projectName=$scope.travelRequest.projectName;
                $scope.empObj.project.projectCode=$scope.travelRequest.projectCode;
                $scope.empObj.project.activityCode=$scope.travelRequest.activityCode;
                $scope.empObj.purposeOfTravel=$scope.travelRequest.purposeOfTravel;
                $scope.empObj.typeOfProject=$scope.travelRequest.typeOfProject;
                $scope.empObj.approverName=$scope.travelRequest.empData[1].employeeName;
                $scope.cell.evaluator=$scope.travelRequest.requestType;
                $scope.travelRequest.isos=parseInt($scope.travelRequest.isos);
                $scope.travelRequest.isos ? $scope.isos.isosCardexist = 1 : $scope.isos.isosCardexist = 0;
                $scope.editRequestId=$scope.travelRequest.requestId;
              // alert($scope.empObj.x);
                //accomodation obj
                if($scope.travelRequest.accomodationRequest!=null){
	                self.service.type = 'hotel';
	                $scope.accomodationObj=$scope.travelRequest.accomodationRequest;
	                $scope.accomodationObj.checkInDate=new Date($scope.travelRequest.accomodationRequest.checkInDate);
	                $scope.accomodationObj.checkOutDate=new Date($scope.travelRequest.accomodationRequest.checkOutDate);
	                $scope.accomodationObj.budget = parseInt($scope.travelRequest.accomodationRequest.budget);
	                $scope.accommodationCheckBox=true;
	                $scope.checkInTime=timeConversionTo12Hrs($scope.travelRequest.accomodationRequest.checkInTime);
	                $scope.checkOutTime=timeConversionTo12Hrs($scope.travelRequest.accomodationRequest.checkOutTime);
	                //console.log("checkin time",  $scope.checkInTime);
                }
                //flightObj
                if( $scope.travelRequest.flightRequest!=null){
                	self.service.type = 'flight';
                    $scope.flightObj=$scope.travelRequest.flightRequest;
                    $scope.travelRequest.flightRequest.departureDate=new Date($scope.travelRequest.flightRequest.departureDate);
	                $scope.travelRequest.flightRequest.returnDate=new Date($scope.travelRequest.flightRequest.returnDate);
	                $scope.travelRequest.flightRequest.dateOfBirth=new Date($scope.travelRequest.flightRequest.dateOfBirth);
	                $scope.travelRequest.flightRequest.passportIssueDate=new Date($scope.travelRequest.flightRequest.passportIssueDate);
	                $scope.travelRequest.flightRequest.passportExpiryDate=new Date($scope.travelRequest.flightRequest.passportExpiryDate);
	                $scope.flightObj.departureDate=new Date($scope.travelRequest.flightRequest.departureDate);
	                $scope.flightObj.returnDate=new Date($scope.travelRequest.flightRequest.returnDate);
	                $scope.flightObj.dateOfBirth=new Date($scope.travelRequest.flightRequest.dateOfBirth);
	                $scope.flightCheckBox=true;
                }
                //forexObj
                if( $scope.travelRequest.forexRequest!=null){
                	self.service.type = 'forex';
                    $scope.forexObj=$scope.travelRequest.forexRequest;
	                $scope.travelRequest.forexRequest.forexFromDate=new Date($scope.travelRequest.forexRequest.forexFromDate);
	                $scope.travelRequest.forexRequest.forexToDate=new Date($scope.travelRequest.forexRequest.forexToDate);
	                $scope.forexObj.forexFromDate=new Date($scope.travelRequest.forexRequest.forexFromDate);
	                $scope.forexObj.forexToDate=new Date($scope.travelRequest.forexRequest.forexToDate);
	                $scope.forexCheckBox=true;
	                $scope.forexObj.forexCountry=$scope.travelRequest.forexRequest.forexCountry;
	                console.log("ForexObj edit",$scope.forexObj)
                }
                
                
                $scope.checkedOrNotVisa = function (checkVal) {
                        if (checkVal == true) {
                            $scope.travelRequest.visaRequestVO = $scope.visaObj;
                            console.log($scope.travelRequest.visaRequestVO);
                        } else {
                            $scope.travelRequest.visaRequestVO = null;
                        }
                        //console.log($scope.travelRequest);
                    }
                
                //visaObj
                if($scope.travelRequest.visaRequestVO!=null){
                	self.service.type = 'visa';
                	$scope.visaObj=$scope.travelRequest.visaRequestVO;
                	$scope.visaObj.toDate = $scope.visaObj.toDate ? new Date($scope.visaObj.toDate) : null;
                	$scope.visaObj.fromDate = $scope.visaObj.fromDate ? new Date($scope.visaObj.fromDate) :  null;
                	$scope.visaCheckBox=true;
                	console.log($scope.visaCheckBox);
                	//document.getElementById("visaCheckBox").checked = true;
                	$scope.checkedOrNotVisa($scope.visaCheckBox);
                }
                
                //flightObj
              
                
                //forexObj
              
                
                //visaObj
              
                
                
                $scope.editRequest = function (requestId) {
                
             //$scope.travelRequest.approverId= $scope.empObj.approverId;
             $scope.travelRequest.requestedFor = $scope.empObj.employeeName;
             $scope.travelRequest.projectName = $scope.empObj.project.projectName;
             $scope.travelRequest.projectCode = $scope.empObj.project.projectCode;
             $scope.travelRequest.activityCode = $scope.empObj.project.activityCode;
         	 $scope.travelRequest.typeOfProject = $scope.empObj.typeOfProject;
         	 $scope.travelRequest.purposeOfTravel = $scope.empObj.purposeOfTravel;
            
             console.log("request id"+requestId)
             console.log($scope.q,'q',$scope.empObj.projectName,'w')
             console.log("Approver ID : " + $scope.travelRequest.approverId);
             console.log("Approver EMPID : " + $scope.empObj.approverName.approverId);
             console.log(" edit data-->>",$scope.travelRequest);
             travelRequest.editRecord($scope.travelRequest,$scope.travelRequest.requestId)
                  .then(function (data){
                      console.log("edit object ",data);
                      alert("Request Submitted");
                      $state.go('home');
                  },function(err){
                    console.log("error ",err)
                    alert("Request not submitted, Please enter all the fields in request details.");
                  });
          }
                
                //date validations
              /* --------------------------code from new request----------------------------*/
              
           
                           
                           var appendDateTime=function(date, time){
                                  var dateTime=date+" "+time;
                                  return dateTime;
                           }
                           //initiate date to todays date
//                         var dateOptions=function(){
//                                document.getElementById("checkIn").value=new Date();
//                                document.getElementById("checkOut").value=new Date();
       //
//                                }
//                                dateOptions();
                                  
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

                                	  	$scope.accomodationObj.checkInDate;
                      					$scope.inDate = $scope.accomodationObj.checkInDate.toLocaleDateString();
                      					$scope.checkIn=$scope.inDate; 
                                         /*var inDate=document.getElementById("checkIn").value;
                                         
                                         var t=new Date();
                                         var today=formatDate(t);
                                         console.log(today);
                                         console.log(inDate);
                                         //minimum date
                                         if(inDate < today){
                                         document.getElementById("checkIn").value="";
                                         $scope.checkInErr="Please enter date greater or equal to today";
                                         }else{
                                         document.getElementById("checkOut").value=inDate;
                                         $scope.checkIn=inDate;
                                         $scope.checkInErr="";
                                         }
                                         */
                                  }


                                  //accomodation check out date
                                  $scope.chooseCheckOutDate=function(){

                                         
                                         var outDate=document.getElementById("checkOut").value;
                                         
                                         //var t=new Date();
                                         var today=document.getElementById("checkIn").value;
                                         console.log(today);
                                         console.log(outDate);
                                         //minimum date
                                         if(outDate <= today){
                                         document.getElementById("checkOut").value=today;
                                         //alert("waring!");
                                         $scope.checkOutErr="Please enter date greater to Check-in date";
                                         }else{
                                         //document.getElementById("toDate").value=d;
                                         $scope.checkOut=outDate;
                                         $scope.checkOutErr="";
                                         }

                                  }
                                  
                                  //flight departure date
                                  $scope.showFirstErrorMessage = false;
								  $scope.DepartureDateErr = "";
								  //flight departure date
								  $scope.chooseDepartureDate=function(){
                                  var inDate=document.getElementById("departureDate").value;
//                              
                                  var temp=new Date();
                                  var today=formatDate(temp);
                                  console.log(today);
                                  console.log(inDate);
//                              //minimum date
                                  if(inDate < today){
                                  document.getElementById("departureDate").value="";
                                  $scope.DepartureDateErr="Please enter date greater or equal to today";
                                  }else{
                                  $scope.DepartureDateErr="";
                                  }
                                  
                           }


                           //flight return date

                           $scope.chooseReturnDate=function(){

                                  
                                  var outDate=document.getElementById("returnDate").value;
                                  
                                  //var t=new Date();
                                  var today=document.getElementById("departureDate").value;
                                  console.log(today);
                                  console.log(outDate);
                                  
                         
                          
                          $scope.flightObj.returnDate;
//                     console.log($scope.flightObj.returnDate);
                          $scope.flightObj.departureDate;
                         // $scope.flightObj.departureDate=$filter('date')($scope.flightObj.departureDate, "yyyy-MM-dd"); 
                          $scope.flightObj.returnDate=$filter('date')($scope.flightObj.returnDate, "yyyy-MM-dd");
                          $scope.returnDate=$scope.flightObj.returnDate; 
                          $scope.departureDate=$scope.flightObj.departureDate;
                          var dropdt = new Date($scope.returnDate);
                          console.log(dropdt,"drptdsdsdasda");
                          var pickdt = new Date($scope.departureDate);
                          var noOfDays = ((dropdt-pickdt ) / (24 * 3600 * 1000));
                          console.log(noOfDays,"no of dayssssss");
                                  //minimum date
                                  if(outDate < today){
                                         $scope.returnDateErr="Please enter date greater than departure date";
                                  }
                                  else if(noOfDays<2){
                              $scope.returnDateErr = "Return Date must be 2 days later from Departure Date.";
                                  }
                                  else{
                                         $scope.returnDateErr = "";
                                  }
                                  
//                        
                                  
                                  
                                  
//                              document.getElementById("returnDate").value=today;
//                              $scope.showFirstErrorMessage = true;
//                              //alert("waring!");
                                  
//                              }else{
//                              //document.getElementById("toDate").value=d;
////                                 $scope.returnDate=outDate;
//                              $scope.returnDateErr="";
                                  
                                  
                           }

                                  //flight DOB
                                  $scope.chooseDOB=function(){

                                         //document.getElementById("sample").min=new Date();
                                         var date=document.getElementById("dob").value;
                                         
                                         var t=new Date();
                                         var today=formatDate(t);
                                         //var tISO=d.toISOString();
                                         console.log(today);
                                         console.log(date);
                                         
                                         if(date > today){
                                         document.getElementById("dob").value="";
                                         //alert("waring!");
                                         $scope.dobErr="Please select valid date of birth";
                                         }
                                         $scope.flightObj.dateOfBirth=date+" 00:00:00";
                                         $scope.dobErr="";
                                         
                                  }
                                  //flight passport issue date
                                  
                                  
                                  var today = new Date();
//                                  $scope.accomodationObj.checkInDate = new Date();
//                                  $scope.accomodationObj.checkOutDate = new Date();
                                  $scope.ExpireDate = new Date();
                                  $scope.dateFormat = 'yyyy-MM-dd';
                                  $scope.akshay = new Date();
                                  $scope.rushi = new Date();
                                  $scope.checkDate = function(){
                                      $scope.akshay = $scope.accomodationObj.checkInDate.toLocaleDateString();
                                      $scope.rushi = $scope.accomodationObj.checkOutDate.toLocaleDateString();
                                      
                                  }
                                  $scope.badal = function(){
                                      $scope.cIn=$filter('date')($scope.akshay, $scope.dateFormat);
                                      $scope.cOut=$filter('date')($scope.rushi, $scope.dateFormat);
                                      
                                  }
                                  
                                  $scope.availableDateOptions = {
                                      formatYear: 'yy',
                                      startingDay: 1,
                                      minDate: today,
                                      maxDate: new Date(2030, 5, 22)
                                  };
                                  $scope.expireDateOptions = {
                                      formatYear: 'yy',
                                      startingDay: 1,
                                      minDate: today +1,
                                      maxDate: new Date(2030, 5, 22)
                                  };
                                  $scope.availableDatePopup = {
                                      opened: false
                                  };
                                  $scope.expireDatePopup = {
                                      opened: false
                                  };
                                  $scope.ChangeExpiryMinDate = function(availableDate) {
                                      if (availableDate != null) {
                                          var expiryMinDate = new Date(availableDate);
                                          $scope.expireDateOptions.minDate = expiryMinDate;
                                          $scope.ExpireDate = expiryMinDate;
                                     }
                                  };
                                  $scope.ChangeExpiryMinDate();
                                  $scope.OpenAvailableDate = function() {
                                      $scope.availableDatePopup.opened = !$scope.availableDatePopup.opened;
                                  };
                                  $scope.OpenExpireDate = function() {
                                      $scope.expireDatePopup.opened = !$scope.expireDatePopup.opened;
                                  };
                                  

                                  $scope.choosePassportIssueDate=function(){

                                         
                                         var inDate=document.getElementById("passportIssueDate").value;
                                         
                                         var temp=new Date();
                                         var today=formatDate(temp);
                                         console.log(today);
                                         console.log(inDate);
                                         //minimum date
                                         if(inDate > today){
                                         document.getElementById("passportIssueDate").value="";
                                         $scope.passportIssueDateErr="Please enter date greater or equal to today";
                                         }else{
                                         document.getElementById("passportExpiryDate").value=inDate;
                                         $scope.flightObj.passportIssueDate=inDate+ " 00:00:00";
                                         $scope.passportIssueDateErr="";
                                         }
                                         
                                  }



                                  $scope.choosePassportExpiryDate=function(){

                                         
                                         var outDate=document.getElementById("passportExpiryDate").value;
                                         
                                         //var t=new Date();
                                         var today=document.getElementById("passportIssueDate").value;
                                         console.log(today);
                                         console.log(outDate);
                                         //minimum date
                                         if(outDate < today){
                                         document.getElementById("passportExpiryDate").value=today;
                                         //alert("waring!");
                                         $scope.passportExpiryDateErr="Please enter date greater or equal to passport issue date";
                                         }else{
                                         //document.getElementById("toDate").value=d;
                                         $scope.flightObj.passportExpiryDate=outDate+" 00:00:00";
                                         $scope.passportExpiryDateErr="";
                                         }
                                         
                                  }
                                  
                                  $scope.chooseToDate=function(serviceType,fromDate,toDate){
                  					var outDate=$filter('date')(toDate, "yyyy-MM-dd");
                  					
                  					//var t=new Date();
                  					var fromDate=$filter('date')(fromDate, "yyyy-MM-dd");
//                  					console.log(today);
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
                                  
//                                  //forex from date
//                                  $scope.chooseToDate=function(serviceType,fromDate,toDate){
//                  					var outDate=$filter('date')(toDate, "yyyy-MM-dd");
//                  					
//                  					//var t=new Date();
//                  					var fromDate=$filter('date')(fromDate, "yyyy-MM-dd");
////                  					console.log(today);
//                  					console.log(outDate);
//                  					//minimum date
//                  					if(fromDate!=null){
//                  						if(outDate < fromDate){
//              								if(serviceType == 'forex'){
//              		        					$scope.forexToDateErr="Please enter date greater or equal to from date";
//              		        					self.mainForm.forexToDate.$setValidity('forexToDate',false);
//              								}else if(serviceType == 'visa'){
//              									$scope.visaToDateErr="Please enter date greater or equal to from date";
//              		        					self.mainForm.visaToDate.$setValidity('visaToDate',false);
//              								}
//              	        				}else{
//              	        					if(serviceType == 'forex'){
//              		        					$scope.forexToDateErr="";
//              		        					self.mainForm.forexToDate.$setValidity('forexToDate',true);
//              	        					}else if(serviceType == 'visa'){
//              									$scope.visaToDateErr="";
//              		        					self.mainForm.visaToDate.$setValidity('visaToDate',true);
//              								}
//              	        				}
//                      					
//                  					}
//                  					else{
//                  						if(serviceType == 'forex'){
//              	    						$scope.forexToDateErr="";
//              	    						self.mainForm.forexToDate.$setValidity('forexToDate',true);
//                  						}else if(serviceType == 'visa'){
//                  							$scope.visaToDateErr="";
//              	    						self.mainForm.visaToDate.$setValidity('visaToDate',true);
//                  						}
//                  					}
//                  					if(serviceType == 'visa' && self.mainForm.visaToDate.$valid && self.mainForm.visaFromDate.$valid){
//                  						$scope.visaObj.travellingDays=$scope.GetDays(fromDate,toDate);
//                  					}else if(serviceType == 'forex' && self.mainForm.forexToDate.$valid && self.mainForm.forexFromDate.$valid){
//                  						$scope.forexObj.forexNoOfDays=$scope.GetDays(fromDate,toDate);
//                  					}
//                  					
//                  					
//                  				}



                                //forex from date
//                  				$scope.chooseFromDate=function(serviceType,fromDate,toDate){
//                  					var inDate=$filter('date')(fromDate, "yyyy-MM-dd");
//                  					var outDate=$filter('date')(toDate, "yyyy-MM-dd");
//                  					
//                  					var temp=new Date();
//                  					var today=formatDate(temp);
//                  					console.log(today);
//                  					console.log(inDate);
//                  					//minimum date
//                  					if(outDate==null){
//                  						if(inDate!=null){
//                      						if(inDate <= today){
////                      	    					document.getElementById("forexFromDate").value="";
//                      							if(serviceType == 'forex'){
//              	        	    					$scope.forexFromDateErr="Please enter date greater or equal to today";
//              	        	    					self.mainForm.forexFromDate.$setValidity('forexFromDate',false);
//                      							}else if(serviceType == 'visa'){
//                      								$scope.visaFromDateErr="Please enter date greater or equal to today";
//              	        	    					self.mainForm.visaFromDate.$setValidity('visaFromDate',false);
//                      							}
//                  							}else{
//                  								if(serviceType == 'forex'){
//              		    	    					$scope.forexFromDateErr="";
//              		    	    					self.mainForm.forexFromDate.$setValidity('forexFromDate',true);
//                  								}else if(serviceType == 'visa'){
//                  									$scope.visaFromDateErr="";
//              	        	    					self.mainForm.visaFromDate.$setValidity('visaFromDate',true);
//                  								}
//                  	    					}
//                      					}
//                      					else{
//                      						if(serviceType == 'forex'){
//              	        						$scope.forexFromDateErr="";
//              	        						self.mainForm.forexFromDate.$setValidity('forexFromDate',true);
//                      						}else if(serviceType == 'visa'){
//                      							$scope.visaFromDateErr="";
//                      	    					self.mainForm.visaFromDate.$setValidity('visaFromDate',true);
//                      						}
//                      					}
//                  					}
//                  					else{
//                  						if(outDate < inDate){
//                  							if(serviceType == 'forex'){
//              	    							$scope.forexToDateErr="Please enter date greater or equal to from date";
//              	    							self.mainForm.forexToDate.$setValidity('forexToDate',false);
//                  							}else if(serviceType == 'visa'){
//                  								$scope.visaToDateErr="Please enter date greater or equal to from date";
//              	    							self.mainForm.visaToDate.$setValidity('visaToDate',false);
//                  							}
//                  						}
//                  						else{
//                  							if(serviceType == 'forex'){
//              	        						$scope.forexFromDateErr="";
//              	        						self.mainForm.forexFromDate.$setValidity('forexFromDate',true);
//                      						}else if(serviceType == 'visa'){
//                      							$scope.visaFromDateErr="";
//                      	    					self.mainForm.visaFromDate.$setValidity('visaFromDate',true);
//                      						}
//                  						}
//                  						
//                  					}
//                  					if(serviceType == 'visa' && self.mainForm.visaToDate.$valid && self.mainForm.visaFromDate.$valid){
//                  						$scope.visaObj.travellingDays=$scope.GetDays($scope.visaObj.fromDate,$scope.visaObj.toDate);
//                  					}else if(serviceType == 'forex' && self.mainForm.forexToDate.$valid && self.mainForm.forexFromDate.$valid){
//                  						$scope.forexObj.forexNoOfDays=$scope.GetDays($scope.forexObj.forexFromDate,$scope.forexObj.forexToDate);
//                  					}
//                  				}
                                  
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
//                      	    					document.getElementById("forexFromDate").value="";
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



       // Get all records for the approver to take action
                   $scope.approverId=$scope.user.employeeId;
                    $scope.requestToApprove=[];            
                    
                   travelRequest.getAllRecords($scope.approverId).then(function(res){
                           if(res.data.requestListVO.length==0){
//                                alert("No requests found");
//                                $state.go('home')
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
//                  console.log("request to approve Details ",$scope.requestToApproveDetails);
                    
                    

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
                           $scope.flightObj.dateOfBirth=new Date($scope.flightObj.dateOfBirth);
                           $scope.flightObj.dateOfBirth=$filter('date')($scope.flightObj.dateOfBirth, "yyyy-MM-dd");
                           console.log($scope.flightObj.dateOfBirth,"$scope.flightObj.DOB");
                           $scope.DOB=$scope.flightObj.dateOfBirth;
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
                           }
                           else if($scope.age<18)
                               {
                                  
                                   $scope.invalidDOB="Your not eligible for passport";
                                   console.log($scope.invalidDOB);
                               }
                               else{
                                  $scope.invalidDOB="";
                               }
//                       $scope.dob=date;
//                       $scope.dobErr="";
                          
                    }

                    
                    
                
               /*    $scope.addDays = function (fromDate, toDate) {
                   var to = new Date(toDate);
                   var from = new Date(fromDate);
                   var oneDay=24*60*60*1000; // hr * min * sec * millisec  
                    var days = Math.round(Math.abs((to.getTime()-from.getTime())/(oneDay)));
                   $scope.forexObj.forexNoOfDays=days;
                   alert($scope.forexObj.forexNoOfDays)
                   if($scope.forexObj.forexCountry=="USA"){
                           $scope.currency="USD";
                           $scope.amount=60;
                   }
                   $scope.forexObj.forexAmount=appendDateTime(days * $scope.amount)
                   console.log("forexAmount=",$scope.forexObj.forexAmount)
//                 var d1 = new Date(fromDate);
//                 var d2 = new Date(toDate);
//                 var d1 = moment(fromDate);
//                 var d2 = moment(toDate);
//                 var days = moment.duration(d2.diff(d1)).asDays();
//                 $scope.forexObj.forexNoOfDays=days;
//                 alert($scope.forexObj.forexNoOfDays);
                   } */
                   
                   



                  
       // Confirm modal
                   
                  /* $scope.showConfirmModal = false;
                   $scope.unCheckAcco = function(){
                       if($scope.accommodationForm.$dirty){
                           //show confirmation popup to ask yes or no
                           $scope.showConfirmModalAcco = true;
                       }

                    }

                    $scope.unCheckFlight = function(){
                       if($scope.flightForm.$dirty){
                           //show confirmation popup to ask yes or no
                           $scope.showConfirmModalFlight = true;
                       }

                    }

                    $scope.unCheckCab = function(){
                       if($scope.cabForm.$dirty){
                           //show confirmation popup to ask yes or no
                           $scope.showConfirmModalCab = true;
                       }

                    }

                    $scope.unCheckForex = function(){
                       if($scope.forexForm.$dirty){
                           //show confirmation popup to ask yes or no
                           $scope.showConfirmModalForex = true;
                       }
                    }


                    $scope.confirmDiscardAccoYes = function(){
                       $scope.accommodationCheckBox = {}
                    }
                    $scope.confirmDiscardAccoNo = function(){
                       $scope.accommodationCheckBox = true;
                   }



                   $scope.confirmDiscardFlightYes = function(){
                       $scope.accommodationCheckBox = {}
                    }

                    $scope.confirmDiscardFlightNo = function(){
                       $scope.accommodationCheckBox = true;
                   }



                   $scope.confirmDiscardCabYes = function(){
                       $scope.accommodationCheckBox = {}
                    }

                    $scope.confirmDiscardCabNo = function(){
                       $scope.accommodationCheckBox = true;
                   }


                   $scope.confirmDiscardForexYes = function(){
                       $scope.accommodationCheckBox = {}
                    }

                    $scope.confirmDiscardForexkNo = function(){
                       $scope.accommodationCheckBox = true;
                   }
       */

//                 $scope.travelRequest = {
//                     requestId: "",
//                     requestedBy: 16290,
//                     requestedFor: "Self",
//                     approverId: 16294,
//                     currentStatus: "submitted",
//                     createdOn: "2017-11-02 00:00:00",
//                     modifiedOn: "2017-11-02 00:00:00",
//                     remark: "new Request",
//                     actionOnRequest: "",
//                     //empData:$scope.empObj
//                     // flightRequest:$scope.flightObj,
//                     // cabRequest:$scope.cabObj,
//                     // accomodationRequest:$scope.accomodationObj,
//                     // forexRequest:$scope.forexObj
//                 }
       //
                   //Approver id from approver name:
                   
//                  Countries validation
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
                   
//                 Cities validation
                   $scope.checkCity=function(){
                   var originCity=$scope.flightObj.departureLocation.toUpperCase();
                   var destinationCity=$scope.flightObj.destinationLocation.toUpperCase();
                   if(originCity == destinationCity){
                           $scope.originCityErr="From and to city should be different";
                           $scope.flightObj.departureLocation=originCity;
                           $scope.flightObj.destinationLocation=destinationCity;
                   }
                   else{
                           $scope.originCityErr="";
                   }
                   if($scope.flightObj.departureLocation=="" || $scope.flightObj.destinationLocation==""){
                           $scope.originCityErr="";
                   }
                   }
                   
                   
                   
                   
                    
                   
                    
//                  $scope.travelRequest.accomodationRequest = null;
//                  $scope.travelRequest.cabRequest = null;
//                  $scope.travelRequest.flightRequest = null;
//                  $scope.travelRequest.forexRequest=null;
//                  $scope.travelRequest.visaRequestVO=null;
                    
                    
                    
//                 $scope.travelRequest.empData = $scope.empObj;
//                 $scope.travelRequest.cabRequest = $scope.cabObj;
//                 //$scope.travelRequest.flightRequest = $scope.flightObj;
//                 $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
//                 $scope.travelRequest.forexRequest = $scope.forexObj;
                   //$scope.travelRequest.approverId=$scope.travelRequest.empData.approverName;
                   //$scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
//                 $scope.cabObj.fromDate="2017-11-20 04:30:00"
//                 $scope.cabObj.toDate="2017-11-20 04:30:00"
               

                   
                   $scope.checkedOrNotFlight = function (checkVal) {
                       if (checkVal == true) {
                           $scope.travelRequest.flightRequest = $scope.flightObj;
                       } else {
                           $scope.travelRequest.flightRequest = null;
                       }
//                     console.log($scope.travelRequest);
                   }
                 
                   $scope.checkedOrNotAccomodation = function (checkVal) {
                       if (checkVal == true) {
                           $scope.travelRequest.accomodationRequest = $scope.accomodationObj;
                       } else {
                           $scope.travelRequest.accomodationRequest=null;
                       }
//                     console.log($scope.travelRequest);
                   }
                   
                   $scope.checkedOrNotForex = function (checkVal) {
                   
                       if (checkVal == true) {
                           $scope.travelRequest.forexRequest = $scope.forexObj;
                       } else {
                           $scope.travelRequest.forexRequest = null;
                       }
                       //console.log($scope.travelRequest);
                   }
                   
                          
                   
                   /* $scope.forexObj.forexAmount="100";
                    //advance amount
                    $scope.forexObj.forexToDate="2018-01-01 12:00:00";*/
//                   $scope.send = function () {
//                   $scope.travelRequest.approverId= $scope.empObj.approverName.approverId;
//                   $scope.travelRequest.requestedFor = $scope.empObj.employeeName;
//                   $scope.travelRequest.projectName = $scope.empObj.projectName;
//                   $scope.travelRequest.projectCode = $scope.empObj.projectCode;
//                   //$scope.travelRequest.cabRequest.pickUpTime = $scope.cabObj.pickUpTime;
//                   console.log($scope.q,'q',$scope.empObj.projectName,'w')
//                   console.log("Approver ID : " + $scope.travelRequest.approverId);
//                   console.log("Approver EMPID : " + $scope.empObj.approverName.approverId);
//                   console.log("data--",$scope.travelRequest);
//                   travelRequest.createRecord($scope.travelRequest)
//                           .then(function (data) {
//                               console.log(data);
//                               alert("Request Submitted");
//                               $state.go('home');
//                           },function(err){
//                                 console.log("error ",err)
//                                 alert("Request not submitted, Please enter all the fields in request details.");
//                           });
//                   }
                           
                           $scope.send = function (status) {
                        	   if($scope.travelRequest.tourType == "international"){
	                          		self.isosData.$setSubmitted();
                        	   }
                        	   if(self.mainForm.$valid && self.isosData.$valid != false){
	                                 $scope.travelRequest.requestedFor = $scope.empObj.employeeName;
	                                 $scope.travelRequest.projectName = $scope.empObj.project.projectName;
	                                 $scope.travelRequest.projectCode = $scope.empObj.project.projectCode;
	                                 $scope.travelRequest.typeOfProject = $scope.empObj.typeOfProject;
	                                 $scope.travelRequest.purposeOfTravel = $scope.empObj.purposeOfTravel;
	                                 $scope.travelRequest.activityCode = $scope.empObj.project.activityCode;
	                                 $scope.travelRequest.mobileNumber = $scope.empObj.employeeMobileNumber;
	                                 $scope.travelRequest.employeeLocation = $scope.empObj.employeeLocation;
	                                 $scope.travelRequest.isos = $scope.isos.isosCardexist ?  $scope.travelRequest.isos : null;
	                                 if($scope.checkObjIsEmpty($scope.travelRequest.forexRequest) && !$scope.forexObj.isActive){
	                                	$scope.forexObj.cardNumber = null;
	                                	$scope.forexObj.cardHolderName = null;
	                                	$scope.forexObj.bank = null;
	                                 }
	                                 if(status == 'draft'){
	             	            		$scope.travelRequest.currentStatus = "Draft";
	             	            	} else {
	             	            		$scope.travelRequest.currentStatus = "Manager Approval Pending";
	             	            	}
	                                 //$scope.travelRequest.isos = $scope.isos.isosNumber;
	                                 //$scope.isos.isosCardexist = parseInt($scope.isos.isosCardexist);
	                                 //if(!$scope.isos.isosCardexist) $scope.isos.isos = null;
	                                
	                                /* if($scope.forexCheckBox){
	                             		var forCountry=$scope.forexObj.forexCountry.country;
	                             		$scope.forexObj.forexCountry=forCountry;
	                             	}*/
	                                 $scope.travelRequest.actionOnRequest = "Edit";
	                                 $scope.travelRequest.requestId = $scope.editRequestId;
	                                 console.log($scope.q,'q',$scope.empObj.projectName,'w')
	                                 console.log("Approver ID : " + $scope.travelRequest.approverId);
	                                 console.log("Approver EMPID : " + $scope.empObj.approverName.approverId);
	                                 console.log($scope.editRequestId+"data--",$scope.travelRequest);
	                                  travelRequest.editRecord($scope.travelRequest,$scope.editRequestId)
	                                   .then(function (data) {
	                                       console.log(data);
	                                   	   $scope.serverResponse = "Request sent to Approver 1";
	                                   	   //$('#myModal').modal('show');
	                                   	   //$state.go('home');
	                                   	   $state.go('myrequests');
	                                	   
	                                   },function(err){
	                                	   $scope.serverResponse = "Something went wrong. Please try again";
	                                	   $('#myModal').modal('show');
	                                   });
                        	   }
                           }


                           $scope.closeMsgPopup = function(){
               				$state.go('home');
               			}


                   $scope.colorChangePersonal = function () {
                       $scope.tabClassPersonal = "whiteTab";
                       $scope.tabClassRequest = "greyTab";
                       $scope.tabClassConfirm = "greyTab";
                   }

                   $scope.colorChangeRequest = function () {
                       $scope.tabClassPersonal = "greyTab";
                       $scope.tabClassRequest = "whiteTab";
                       $scope.tabClassConfirm = "greyTab";
                       $scope.progressClass1 = "progress-bar";
                   }

                   $scope.colorChangeConfirm = function () {
                       $scope.tabClassPersonal = "greyTab";
                       $scope.tabClassRequest = "greyTab";
                       $scope.tabClassConfirm = "whiteTab";
                       $scope.progressClass2 = "progress-bar";
                   }

                   $scope.openNextForm = function (accordionName) {

                       if (accordionName == "accomodationAccordion") {

                           if ($scope.accomodationAccordion == "panel-collapse collapse in") {
                               $scope.accomodationAccordion = "panel-collapse collapse";
                               $scope.accomodationHeading += "back-green";
                           }
                       }


                   }

                   $scope.checkClick=function(temp){
                       console.log("temp"+temp);
                       console.log($scope.flightObj.tourType);

                   }
                   
                   
                   

                   //Need this js to open and close accordion after clicking next
                   // $scope.selectForm1 = function (formName) {


                   //     $scope.checkBox = "$scope." + formName;
                   //     //  alert( $scope.checkBox);
                   //     if (formName == 'accommodationCheckBox') {
                   //         if ($scope.accommodationCheckBox && !$scope.activeForm.includes("accommodationCheckBox")) {
                   //             $scope.activeForm.push("accommodationCheckBox");
                   //             console.log("if");
                   //         } else if ($scope.activeForm.includes("accommodationCheckBox")) {
                   //             $scope.activeForm.splice("accommodationCheckBox");
                   //             console.log("elseif");
                   //         }
                   //     }

                   //     if (formName == 'taxiCheckBox') {

                   //         if ($scope.taxiCheckBox && !$scope.activeForm.includes("taxiCheckBox")) {
                   //             $scope.activeForm.push("taxiCheckBox");
                   //         } else if ($scope.activeForm.includes("taxiCheckBox")) {
                   //             $scope.activeForm.splice("taxiCheckBox");
                   //         }
                   //     }

                   //     if (formName == 'flightCheckBox') {
                   //         if ($scope.flightCheckBox && !$scope.activeForm.includes("flightCheckBox")) {
                   //             $scope.activeForm.push("flightCheckBox");
                   //         } else if ($scope.activeForm.includes("flightCheckBox")) {
                   //             $scope.activeForm.splice("flightCheckBox");
                   //         }
                   //     }

                   //     if (formName == 'forexCheckBox') {
                   //         if ($scope.forexCheckBox && !$scope.activeForm.includes("forexCheckBox")) {
                   //             $scope.activeForm.push("forexCheckBox");
                   //         } else if ($scope.activeForm.includes("forexCheckBox")) {
                   //             $scope.activeForm.splice("forexCheckBox");
                   //         }
                   //     }

                   //     console.log($scope.activeForm);
                   // }
                   
                   $scope.nextPreviosButton=function(value){
                   if(true){
                   $("#"+value).click();
                   }
                   } 

                   //javascript bootstrap tooltip
                   $(document).ready(function(){
                       $('[data-toggle="tooltip"]').tooltip();   
                   });
                   
                   $scope.empName="";
                   $scope.setPersonalData=function(){
                    if($scope.travelRequest.requestType=="Self"){
                   $scope.empObj.employeeName=$scope.user.employeeName;
                   $scope.empObj.employeeMobileNumber=$scope.user.employeeMobileNumber;
                   //$scope.empObj.employeeLocation=$scope.user.employeeLocation;
                    $scope.empObj.projectCode=$scope.user.projectCode;
                    $scope.empName="Employee Name:";
                     $scope.getProjectName();
                     console.log($scope.travelRequest.requestType);
                   }else if($scope.travelRequest.requestType=="Other"){
                   $scope.empObj.employeeName="";
                   $scope.empObj.employeeMobileNumber="";
                   //$scope.empObj.employeeLocation="";
                   $scope.empObj.projectCode="";
                   $scope.empName="Requested For:";
                   console.log($scope.travelRequest.requestType);
                   }
                   }
                   
                   $scope.getProjectName=function(){
	                   console.log("projectList",$scope.projectList);
	                   for(var i=0;i<$scope.projectList.listOfProjectDetails.length;i++){
	               		if($scope.projectList.listOfProjectDetails[i].projectCode==$scope.empObj.project.projectCode){
	               			$scope.empObj.project.projectName = $scope.projectList.listOfProjectDetails[i].projectName;
	               			$scope.empObj.project.activityCode = $scope.projectList.listOfProjectDetails[i].activityCode;
	               		}
	               		
	               	}
//                    for(var i = 0; i<$scope.projectList.listOfApprovers.length; i++){
//                            if($scope.projectList.listOfApprovers[i].approverName==$scope.empObj.approverName){
//                                  
//                                  $scope.empObj.approverId=$scope.projectList.listOfApprovers[i].approverId;
//                                  
//                           }
//
//                        }
//                        console.log($scope.empObj);
                   }
                   
                   $scope.GetDays=function(from,toDate){
                	   console.log(from,toDate)
                       var dropdt = new Date(from);
                       var pickdt = new Date(toDate);
                       var noOfDays = ((pickdt- dropdt) / (24 * 3600 * 1000));
                       noOfDays = noOfDays+1;//including day of drop
                       noOfDays = noOfDays+"";
                       noOfDays = Math.round(noOfDays)
                       console.log("noOfDays",noOfDays)
                       return noOfDays;
               }

               $scope.cal=function(){
                    $scope.perdiem=0;
                     $scope.forexObj.forexFromDate=new Date($scope.forexObj.forexFromDate);
                     console.log($scope.from);
                    console.log($scope.forexObj.forexToDate,"pppppp");
//                  $scope.ss=$scope.forexObj.forexToDate;
//                  console.log($scope.ss,"pppppp");
                    $scope.forexObj.forexToDate=new Date($scope.forexObj.forexToDate);
                   $scope.from=$filter('date')($scope.forexObj.forexFromDate, "yyyy-MM-dd");
                   $scope.forexObj.forexFromDate=$scope.from;
                   console.log($scope.from);
                   $scope.toDate=$filter('date')($scope.forexObj.forexToDate, "yyyy-MM-dd"); 
                   $scope.forexObj.forexToDate=$scope.toDate;
                   console.log($scope.toDate);
               if($scope.toDate){
                    $scope.forexObj.forexNoOfDays=$scope.GetDays();        
               } 
               for(var i = 0; i<$scope.projectList.perDeimList.length; i++){
                     if($scope.projectList.perDeimList[i].country==$scope.forexObj.forexCountry.country){
                           
                           $scope.perdiem=$scope.projectList.perDeimList[i].perdiem;
                           $scope.currency=$scope.projectList.perDeimList[i].currency;
                     }
                    
               }
               $scope.amount=$scope.forexObj.forexNoOfDays * $scope.perdiem ;
               $scope.forexObj.forexAmount=$scope.amount;
               //$scope.forexObj.forexCountry=$scope.forexObj.forexCountry.country;
               console.log("forex perdiem ",$scope.forexObj.forexAmount)
           }

                   
               $scope.myFormName = "personal";
               $scope.form1Name = "form1";
//                  $scope.classValue1 = "active";
               $scope.firstTabClass = true;
               $scope.secondTabClass = false;
               $scope.thirdTabClass = false;
               $scope.tabsArr = [];
               $scope.addToArray = function(oForm){
               debugger;
               alert();
//                  $scope.tabsArr.push(oForm);
               //console.log($scope.tabsArr);
               }
               
               
               self.personal = "personal";
               self.accommodationForm = "accommodationForm";
               self.visaForm = "visaForm";
               self.mainForm = "mainForm";
               self.forexForm = "forexForm";
               self.flightForm = "flightForm";
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
       		        if(formName == 'personal' && self.personal.$valid){
       			        self.activePersonalForm = 1;
       		        }
       		        if(formName != 'personal' && self.mainForm.$valid){
			        	self.activePersonalForm = 2;
			        }
       	        }

               }
              
               $scope.flag1 = false;
               $scope.accommodationCheckBox;
               $scope.flag2 = false;
               $scope.taxiCheckBox;
               $scope.flag3 = false;
               $scope.flightCheckbox;
               $scope.flag4 = false;
               $scope.forexCheckbox;
               $scope.flag5 = false;
               $scope.visaCheckbox ;
               console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'0');
               
               
               $scope.accomodationValid = function(val){
                    //console.log(self.visaForm,'self 1');
                    if(val == true){
                    //console.log(self.visaForm," self 2")
                    if(self.visaForm.$valid == true){
                    $scope.flag1 = true;
                    $scope.flag2 = true;
                    $scope.flag3 = true;
                    $scope.flag4 = true;
                    $scope.flag5 = true;
                      console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'1');
                    }
                    //console.log("valid form");

                    else if(val == false){
                    $scope.flag1 = true;
                    $scope.flag2 = true;
                    $scope.flag3 = true;
                    $scope.flag4 = true;
                    $scope.flag5 = true;
                      console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'2');
                    }
                    else{
                      console.log($scope.flag1,$scope.flag2,$scope.flag3,$scope.flag4,$scope.flag5,'3');
                    }
                    }
                    }
               
               $scope.masterFlag;
               $scope.onClickingConfirm = function(){
               if(($scope.flag1 == true)&& ($scope.flag2 == true) && ($scope.flag3 == true) && ($scope.flag3 == true) && ($scope.flag5 == true)){
               $scope.masterFlag = true;
               $scope.secondTabClass = false;
               $scope.firstTabClass = false;
               $scope.thirdTabClass = true;
               }
               else{
               $scope.secondTabClass = true;
               $scope.firstTabClass = false;
               $scope.thirdTabClass = false;
               }
               }

               $scope.function1 = function(formName1){
               console.log('key is pressed');
               console.log(formName1.$valid);
               if(formName1.$valid == true){
               $scope.flag1 = true;
               console.log($scope.flag1,'flag1');
               }
               }
               
//            $scope.function2 = function(formName){
//            console.log('key is pressed');
//            console.log(formName.$valid);
//            if(formName.$valid == true){
//            $scope.flag1 = true;
//            console.log($scope.flag1,'flag1');
//            }
//            }

//            $scope.function3 = function(formName){
//            console.log('key is pressed');
//            console.log(formName.$valid);
//            if(formName.$valid == true){
//            $scope.flag1 = true;
//            console.log($scope.flag1,'flag1');
//            }
//            }

           $scope.function4 = function(formName){
           console.log('key is pressed');
           console.log(formName.$valid);
           if(formName.$valid == true){
           $scope.flag1 = true;
           console.log($scope.flag1,'flag1');
           }
           }

           $scope.function5 = function(formName){
           console.log('key is pressed');
           console.log(formName.$valid);
           if(formName.$valid == true){
           $scope.flag1 = true;
           console.log($scope.flag1,'flag1');
           }
           }

       })

