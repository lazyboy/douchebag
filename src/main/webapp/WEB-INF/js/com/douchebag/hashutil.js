goog.provide('douchebag.hashutil');

goog.require('douchebag.util');

douchebag.hashutil.testit = function(a, b) {
  return douchebag.util.concat(a, b);
};