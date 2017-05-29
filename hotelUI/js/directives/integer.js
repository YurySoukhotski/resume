/**
 * Created by Admin on 25.03.2017.
 */
var INTEGER_REGEXP = /^\-?\d+$/;
angular.module('hotelApp').directive('integer', function() {
    return {
        require: 'ngModel',
        link: function(scope, elm, attrs, ctrl) {
            ctrl.$validators.integer = function(modelValue, viewValue) {
                if (ctrl.$isEmpty(modelValue)) return true;
                if (INTEGER_REGEXP.test(viewValue)) return true;
                return false;
            };
        }
    };
});