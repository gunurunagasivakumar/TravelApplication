"use strict";

var adder = {
  base: 1,

  add: function add(a) {
    var _this = this;

    var f = function f(v) {
      return v + _this.base;
    };
    return f(a);
  },

  addThruCall: function addThruCall(a) {
    var _this2 = this;

    var f = function f(v) {
      return v + _this2.base;
    };
    var b = {
      base: 2
    };

    return f.call(b, a);
  }
};

console.log(adder.add(1)); // This would log to 2
console.log(adder.addThruCall(1)); // This would log to 2 still
//# sourceMappingURL=app1.js.map
