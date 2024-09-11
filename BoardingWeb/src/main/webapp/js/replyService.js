/*
 * replyService.js 
 */
/*-------------------
 * 날짜포맷 : yyyy-MM-dd HH:mm:ss 반환하는 Date객체 추가"
 --------------------*/
Date.prototype.dateFormat = function() {
  let yyyy = this.getFullYear();
  let MM = ('0' + (this.getMonth()+1)).slice(-2); // 09,010 -> 09,10
  let dd = ('0'+this.getDate()).slice(-2);
  
  let hh = ('0'+this.getHours()).slice(-2);
  let mm = ('0'+this.getMinutes()).slice(-2);
  let ss = ('0'+this.getSeconds()).slice(-2);
  
  return yyyy+'-'+MM+'-'+dd+' '+hh+':'+mm+':'+ss; // 2024-09-12 12:33:33
}


/*-------------------
 * ReplyVO값을 tr 생성해주는 함수
 -------------------*/

const fields = ['replyNo','reply','replyer','replyDate'];
function makeRow(reply={}) {
  let tr = document.createElement('tr');
  console.log('reply=>',reply);
  tr.setAttribute('data-rno',reply.replyNo);
  //checkbox
  let chk = document.createElement('input');
  chk.setAttribute('type','checkbox');
  

  let td = document.createElement('td');
  td.appendChild(chk);
  tr.appendChild(td);

  fields.forEach(field => {
    let td = document.createElement('td');
    if(field=='replyDate') {
      let today = new Date(reply[field]); // 날짜문자 => 날짜객체.dateFormat()출력
      td.innerHTML = today.dateFormat();    
    } else {
      td.innerHTML = reply[field];
    }
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

//서비스 메소드를 통해서 ajax 기능을 실행
//1.목록 2.삭제 3.추가 4......
const svc = {
  replyList: function (param = {bno: 1,page: 1}, successCallback, errorCallback) {
    console.log(param.page);
    fetch('replyList.do?bno='+param.bno+'&page='+param.page)
    .then(resolve => resolve.json())
    .then(successCallback)
    .catch(errorCallback)
  },
  removeReply: function (rno = 1, successCallback, errorCallback) {
    fetch('removeReply.do?rno='+rno)
    .then(resolve => resolve.json())
    .then(successCallback)
    .catch(errorCallback)
  },
  addReply(param = {bno, reply, replyer}, successCallback, errorCallback) {
    fetch('addReply.do', {
      method: 'post',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      body: 'bno='+param.bno+'&reply='+param.reply+'&replyer='+param.replyer
    })
    .then(resolve => resolve.json())
    .then(successCallback)
    .catch(errorCallback)
  },
  // 댓글건수를 갖와서 페이징정보를 생성 replyPagingCount
  replyPagingCount(bno = 1, successCallback, errorCallback) {
    fetch('replyCount.do?bno='+bno)
    .then(resolve => resolve.json())
    .then(successCallback)
    .catch(errorCallback)
  }
}