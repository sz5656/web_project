let replyer = 'user01';
let bno = 148;

const xhttp = new XMLHttpRequest();
xhttp.open('get', 'replyList.do?bno=148');
xhttp.send();


xhttp.onload = function () {
  console.log(xhttp.responseText);
  let result = JSON.parse(xhttp.responseText);
  console.log(result); 
  result.forEach(reply => {
    document.querySelector('.list').appendChild(makeRow(reply));
  });
}

//댓글작성
let replyBtn = document.querySelector('#addReply');
replyBtn.addEventListener('click',function(e) {
  // let reply = document.querySelector('#reply');
  let reply = document.getElementById('reply').value;
  const addHttp = new XMLHttpRequest();
  let param = "addReply.do?replyer="+replyer+"&bno="+bno+"&reply="+reply;
  addHttp.open('get',param);
  addHttp.send();
  addHttp.onload = function() {
    let result = JSON.parse(addHttp.responseText);
      console.log(result);
    if(result.retCode == 'OK') {
    } else if(result.retCode == 'NG') {
      alert('알 수 없는 예외 발생');
    } else {
      alert('잘못된 반환 코드');
    }
  }
})
//댓글정보 -> tr->td*4 생성반환
const fields = ['replyNo','reply','replyer','replyDate'];
function makeRow(reply={}) {
  let tr = document.createElement('tr');
  //checkbox
  let chk = document.createElement('input');
  chk.setAttribute('type','checkbox');
  

  let td = document.createElement('td');
  td.appendChild(chk);
  tr.appendChild(td);

  fields.forEach(field => {
    let td = document.createElement('td');
    td.innerHTML = reply[field];
    tr.appendChild(td);
  })
  let btn = document.createElement('button'); //<button>삭제</button>
  btn.innerHTML = '삭제';
  btn.addEventListener('click',deleteRowFunc);
  td = document.createElement('td');
  td.appendChild(btn);
  tr.appendChild(td);
      
  return tr;
}
document.querySelector('thead input[type="checkbox"]').addEventListener('change',function(e) {
  document.querySelectorAll('.list input[type="checkbox"]').forEach(item => {
  item.checked = this.checked;
  })
});

// 삭제처리할 함수
function deleteRowFunc(e) {
  console.log(e.target.parentElement.parentElement.firstChild.innerHTML);
  let rno = e.target.parentElement.parentElement.firstChild.innerHTML;
  console.log(e.target.parentElement.parentElement.children[1].innerHTML);
  rno = e.target.parentElement.parentElement.children[1].innerHTML;
  const delHttp = new XMLHttpRequest();
  delHttp.open('get','removeReply.do?rno='+rno); // 컨트롤 지정
  delHttp.send();
  delHttp.onload = function() {
    let result = JSON.parse(delHttp.responseText);
    if(result.retCode == 'OK') {
      e.target.parentElement.parentElement.remove();
    } else if(result.retCode == 'NG') {
      alert('알 수 없는 예외 발생');
    } else {
      alert('잘못된 반환 코드');
    }
  }
}
//선택 삭제
document.querySelector('#delChecked').addEventListener('click',delCheckFunc1);

//선택 삭제 함수 실행
function delCheckFunc1(e) {
  // ?rno=21&rno=22...
  let params = "rno=";
  document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
    let rno = item.parentElement.nextElementSibling.innerHTML;
    params += rno + "&rno=";
  })
  
  const delHttp = new XMLHttpRequest();
  delHttp.open('get',"removeReplys.do?" + params);
  delHttp.send();
  delHttp.onload = function() {
    let result = JSON.parse(delHttp.responseText);
    
    if(result.retCode == 'OK') {
      // 화면상에 체크된 input 을 삭제
      document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
        item.parentElement.parentElement.remove();
      })
    } else {
      alert('처리중 예외 발생');
    }
  }
  
}

//선택 삭제 함수 반복실행
function delCheckFunc(e) {
  document.querySelectorAll('.list input[type="checkbox"]:checked').forEach(item => {
    let rno = item.parentElement.nextElementSibling.innerHTML;
    //nextElementSibling 동생
    const delHttp = new XMLHttpRequest();
    delHttp.open('get','removeReply.do?rno='+rno); // 컨트롤 지정
    delHttp.send();
    delHttp.onload = function() {
      let result = JSON.parse(delHttp.responseText);
      if(result.retCode == 'OK') {
        item.parentElement.parentElement.remove();
      } else if(result.retCode == 'NG') {
        alert('알 수 없는 예외 발생');
      } else {
        alert('잘못된 반환 코드');
      }
    }
  })
}

