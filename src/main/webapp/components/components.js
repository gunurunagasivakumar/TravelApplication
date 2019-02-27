angular.module('myApp')
	.config(['$urlRouterProvider', '$stateProvider', '$locationProvider', function ($urlRouterProvider, $stateProvider, $locationProvider) {

		$stateProvider
			.state('login', {
				url: '/',
				templateUrl: 'views/loginTemplate.html',
				controller: 'loginCtrl'
			})

			.state('home', {
				url: '/home',
				templateUrl: 'views/homeTemplate.html',

				controller: 'homeCtrl'
			})

			.state('newRequest', {
				url: '/newRequest',
				templateUrl: 'views/newRequest.html',
				controller: 'newRequestCtrl'
			})


			.state('myrequests', {
				url: '/myrequests',
				templateUrl: 'views/myRequestsList.html',
				controller: 'requestListCtrl'
			})
			.state('approval', {
				url: '/approval',
				templateUrl: 'views/newRequest.html',
				controller: 'newRequestCtrl'
			})
			.state('pendingrequests', {
				url: '/pendingRequests',
				templateUrl: 'views/requestList.html',
				controller: 'newRequestCtrl'
			})
			.state('actionOnRequest', {
				url: '/level1Approve',
				templateUrl: 'views/approveRequest.html',
				controller: 'approverController'
			})
			.state('editRequest', {
				url: '/editRequest',
				templateUrl: 'views/editRequest.html',

			})
		$urlRouterProvider.otherwise('/');
		$locationProvider.html5Mode(true);
/*
		$locationProvider.html5Mode({
			enabled: true,
			requireBase: false
		});
*/

	}])

	.component('sidebarComponent', {
		templateUrl: 'views/sideMenuBarTemplate.html',

	})

	.component('headerComponent', {
		templateUrl: 'views/headerTemplate.html',
		controller: 'headerCtrl'
	})

	.component('loginHeaderComponent', {
		templateUrl: 'views/loginHeaderTemplate.html'
	})


	.component('homeComponent', {
		templateUrl: 'views/homeTemplate.html',
		controller: 'homeCtrl'
	})
	.component('myRequestComponent', {
		template: '<h1>myRequestComponent</h1>'
	})
	.component('newRequestComponent', {
		templateUrl: '../views/newRequest.html',

		controller: 'newRequestCtrl'
	})
	.component('personalDetailsComponent', {
		templateUrl: '../views/tabs.html',

	})
	.component('deletePopupComponent', {
		templateUrl: '../views/deletePopup.html',
		controller: 'DeleteModalInstanceCtrl'
	})
	.component('requestDetailsComponent', {
		templateUrl: '../views/requestDetails.html',
		controller: 'newRequestCtrl'
	})
	.component('confirmComponent', {
		templateUrl: '../views/confirmView.html',
		controller: 'newRequestCtrl'
	})
	.component('requestListComponent', {
		templateUrl: 'views/requestList.html',
		controller: 'newRequestCtrl'
	})
	.component('approvalComponent', {
		templateUrl: 'views/confirmView.html',
	})
	.component('requestListComponent', {
		templateUrl: '../views/requestList.html',
		controller: 'newRequestCtrl'
	})


	.component('footerComponent', {
		templateUrl: 'views/footerTemplate.html'
	})

	.component('loginComponent', {
		templateUrl: 'views/loginTemplate.html',
		controller: 'loginCtrl'
	})
	.component('personalDetailsComponent', {
		templateUrl: 'views/firstTab.html'
	})

	