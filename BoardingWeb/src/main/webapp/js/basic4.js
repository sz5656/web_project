console.log(json); // 문자열
let data = JSON.parse(json); // 문자열 -> 객체 변경
console.log(data); // 객체
console.clear();
let result = data.forEach(function(item,idx,ary) { // 객체,인덱스,배열전체
  if(item.salary >= 8000 && item.gender=='Female') {
    console.log(item);
  }
});

console.log(result);
let result2 = data.filter(function(item,idx,ary) { // ture값에 해당하는것을 배열에 저장해줌
  if(item.salary >= 8000 && item.gender=='Female') {
    console.log(item);
    return true;
  }
});
console.log(result2);