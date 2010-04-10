goog.provide('douchebag');

goog.require('douchebag.hashutil'); // This should also pull douchebag.util


douchebag.main = function() {
  var d = douchebag.hashutil.testit('abacus', 'batman');
  //alert(d);
  document.getElementById('p1').innerHTML = d;
};

window['_main_'] = douchebag.main;
