/*
Asynchronous JavaScript And Xml
*/
setTimeout(function() {
  console.log('a');
}, 1000);

setTimeout(() => {
  console.log('b');
}, 1000);
setTimeout(() => {
  console.log('c');
}, 1000);