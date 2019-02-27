<!DOCTYPE html>
<html ng-app="myApp" style="height:100%">
<head>
  <title></title>
  <link rel="stylesheet" type="text/css" href="assets/dist/css/bootstrap.css">
  
  <link rel="stylesheet" type="text/css" href="assets/dist/css/font-awesome.css">
  <link rel="stylesheet" type="text/css" href="assets/dist/css/cust-Travel-Portal.css">
  <link rel="stylesheet" type="text/css" href="assets/login/css/main.css">
  <link rel="stylesheet" href="assets/js/calendar/bootstrap_calendar.css" type="text/css" />
  <link rel="stylesheet" type="text/css" href="assets/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
  <base href="/CapcoTravelAssistance/" />
</head>
<body class="homeBg">
  <div class="tp-footerfixed" ui-view></div>
<!--   <footer-component></footer-component> -->
  
  
        <!-- Angular Scripts -->
        <script src="assets/js/angular/angular.min.js"></script>
        <script src="assets/js/angular/angular-ui-router.js"></script>
       <!--  <script src="assets/js/angular/component-router.js"></script> -->
        <script src="assets/js/angular/angular-messages.js"></script>
         
        
       
        <!--Bootstrap and Jquery Scripts  -->
        <script src="assets/js/jquery/jquery.min.js"></script>
        <script src="assets/js/bootstrap/bootstrap.min.js"></script>
		<script src="assets/js/bootstrap/ui-bootstrap-tpls.js"></script>
        <script src="modules/module.js"></script>
        <script src="controllers/controller.js"></script>
        <script src="controllers/loginCtrl.js"></script>
        <script src="controllers/newRequest.js"></script>
        <script src="controllers/editRequestCtrl.js"></script>
        <script src="directives/back-button.js"></script>
        <script src="services/factory.js"></script>
      	<script src="components/components.js"></script>
        <!-- <script src="directives/calendarDirective.js"></script> -->
         <!-- Calendar Block Scripts -->
        <script src="assets/js/calendar/bootstrap_calendar.js"></script>
        <!-- <script src="assets/js/calendar/demo.js"></script>-->
        
        


</body>
</html>