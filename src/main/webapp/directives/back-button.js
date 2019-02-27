angular.module('myApp').directive( 'backButton', function() {
    return {
        restrict: 'A',
        link: function( scope, element, attrs ) {
            element.on( 'click', function () {
            	console.log("hhhhhhhh")
                history.back();
                scope.$apply();
            } );
        }
    };
} );