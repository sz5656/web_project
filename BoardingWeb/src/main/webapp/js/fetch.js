/*
fetch.js (비동기처리: 서버상의 리소스 요청)
*/
let bno = 148;
let writer = 'user01';
console.log(fields);

/*
  예제 fetch 연습
*/

fetch('js/data.js') // promise 반환 -> 정상실행일 경우 then(함수)
                    //            -> 비정상실행일 경우 catch(함수)
.then(function(resolve) {
  console.log(resolve); // 응답객체(response)
  return resolve.text();
})
.then(function(result) {
  console.log(result);
  let json = result.substring(result.indexOf('['),result.indexOf[']']+1); // {의 위치}의 위치의 사이의 시간
  console.log(JSON.parse(json));
})
.catch(function(err) {
  console.log('에러가 발생' + err);
})
//서버의 댓글목록 요청작업
const list = document.querySelector('tbody.list');
fetch('replyList.do?bno=' + bno)
.then(resolve => {
  return resolve.json();
})
.then(result => {
 console.log(result);
 result.forEach(reply => {
   let tr = makeRow(reply);
   list.appendChild(tr);
 }) 
})
.catch(err => {
  console.log('catch예외 err:'+err);
  
})

/*
  삭제처리를 위한 변수
*/
function deleteRowFunc(e) {
  let rno = e.target.parentElement.parentElement.dataset.rno; // <tr data-rno=23
  
  fetch('removeReply.do?rno='+rno)
  .then(resolve => resolve.json())
  .then(result => {
    if (result.retCode=='OK') {
      alert('삭제완료');
      e.target.parentElement.parentElement.remove();
     } else {
      alert('삭제처리중 예외발생'); 
     }
  })
}

/*
 * 이벤트(등록) 
 */
document.querySelector('#addReply').addEventListener('click', addReplyFunc);

/*
 * 함수들 
 */
 function addReplyFunc(e) {
   let reply = document.querySelector('#reply').value;
   
   fetch('addReply.do',{
     method : 'post',
     headers : {'Content-Type' : 'application/x-www-form-urlencoded'},
     body : 'bno='+bno+'&reply='+reply+'&replyer='+writer
   })     // 주소표시줄 addReply.do?bno=?&reply=?&replyer=?
   .then(resolve => resolve.json())
   .then(result => {
     console.log(result);
     if(result.retCode=='OK') {
       list.appendChild(makeRow(result.retVal));
     } else {
       alert('처리중 예외 발생');
     }
   })
 }