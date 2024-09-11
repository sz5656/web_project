/**
 * replyBoard.js
 * replayService에 정의된 메소드를 통해서 기능 실행
 */

// DOM 요소를 모두 읽어들인 다음 코드실행
let pagination;
document.addEventListener("DOMContentLoaded",function(e) {
  /**
   * 이벤트(댓글등록)
   */
  //댓글등록
  document.querySelector('#addReply').addEventListener('click',addReplyFunc);
  //페이지링크 클릭
  document.querySelectorAll('ul.pagination a').forEach(aTag => {
    aTag.addEventListener('click',showReplyListFunc);
  })
  pagination = document.querySelector('ul.pagination');
  //댓글출력
  refreshList();
}) // end of domcontentloaded

let page = 1; //페이지 변경될 때 마다 
makeLi();
/**-------------------=
 * 댓글정보 -> li 생성하는 함수
 ---------------------*/
 function makeLi(reply = {}) {
  let cloned = document.querySelector('#template').cloneNode(true);
  cloned.style.display = 'block';
  cloned.setAttribute('data-rno', reply.replyNo);
  cloned.querySelector('span').innerHTML = reply.replyNo;
  cloned.querySelector('span:nth-of-type(2)').innerHTML = reply.reply;
  cloned.querySelector('span:nth-of-type(3)').innerHTML = reply.replyer;
  cloned.querySelector('button').addEventListener('click',deleteLiFunc);
  console.log(cloned);
  return cloned;
 }

 /**
  * 함수: deleteLiFunc
  * 기능: 버튼이 포함된 row삭제(ajax포함)
  */
function deleteLiFunc(e) {
  let rno = e.target.parentElement.parentElement.dataset.rno;
  Swal.fire({
    title: "삭제하시겠습니까?",
    text: "해당 댓글을 정말로 삭제하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "삭제하겠습니다"
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire({
        title: "삭제!",
        text: "삭제되었습니다.",
        icon: "success"
      });
      svc.removeReply(rno, //삭제글번호
        function(result) {
          if(result.retCode=='OK') {
            // alert('삭제성공');
            refreshList();
            console.log('del result:'+result);
            if(result==null) {
              console.log('del result:'+result.length());
              page-=1;
              refreshList();
            }
          } else {
            alert('삭제처리 중 예외발생');
          }
        },
        function(err) {
          console.log(err);
        }
      );
    } else {
      Swal.fire({
        title: "취소하였습니다~",
        width: 600,
        padding: "3em",
        color: "#716add",
        background: "#fff url(https://sweetalert2.github.io/#examplesimages/trees.png)",
        backdrop: `
          rgba(0,0,123,0.4)
          url("https://sweetalert2.github.io/#examplesimages/nyan-cat.gif")
          left top
          no-repeat
        `
      });
    }
  });
}

/**
 * 댓글등록 이벤트핸들러
 */
function addReplyFunc(e) {
  // bno, replyer, reply
  let reply = document.querySelector('#reply').value;
  if (!writer || !reply) {
    alert('필수 입력값 입력');
    return;
  }

  let param = {bno: bno, replyer: writer, reply: reply};
  svc.addReply(param, function(result) {
    if(result.retCode=='OK'){
      // Swal.fire("등록성공!");
      Swal.fire({
        title: "성공",
        text: "댓글 등록이 완료되었습니다",
        icon: "success"
      });
      //document.querySelector('div.content ul').appendChild(makeLi(result.retVal));
      page=1;
      refreshList();
    } else {
      alert('추가중 예외발생');
    }
    document.querySelector('#reply').value = '';
  },
  function(err) {
    console.log(err);
  });
}
//목록 새로고침
function refreshList() {
  document.querySelectorAll('div.content li').forEach((li,idx) => {
    if(idx > 2) {
      li.remove();
    }
  })
  svc.replyList({bno,page},function(result) {
     console.log(result);
     result.forEach(reply => {
      document.querySelector('div.content ul').appendChild(makeLi(reply));
     });
     showPagingListFunc();
    }, function(err) {
      alert(err);
    }//실패했을 때 실행
  );
}
/**
 * 링크 클릭시 댓글목록 새로출력
 * 함수: showReplyListFunc
 */
function showReplyListFunc(e) {
  //기존에 있는 출력 리스트 지워주고
  page = e.target.dataset.page; // 페이지번호
  refreshList();
}

/**
 * 댓글갯수를 활용해서 페이지 리스트 생성
 * 함수: showPagingListFunc
 */


function showPagingListFunc() {
  svc.replyPagingCount(bno, //글번호
    makePagingList, // 성공했을때 실행할 콜백함수
    function(err) {
      alert(err);
    }  
  )
}
// 정상처리 실행할 콜백함수
function makePagingList(result) {
  pagination.innerHTML = ''; // 기존 페이지 리스트 지우기
  console.log(result);
  let totalCnt = result.totalCount;
  console.log('makePagingList totalcnt');
  console.log(totalCnt);
  //페이지 목록 만들기
  let startPage, endPage, realEnd; // 첫페이지 ~ 마지막페이지
  let prev, next; // 이전페이지, 이후페이지

  endPage = Math.ceil(page / 5) * 5;
  startPage = endPage - 4;
  realEnd = Math.ceil(totalCnt/5);

  endPage = endPage > realEnd ? realEnd : endPage;
  prev = startPage > 1;
  next = endPage < realEnd;
  //이전페이지
  let li = document.createElement('li');
  li.className = 'page-item';
  let a = document.createElement('a');
  a.className = 'page-link';
  a.innerHTML = 'Previous';
  li.appendChild(a);
  if(prev) {
    a.setAttribute('href','#');
    a.setAttribute('data-page',startPage-1);
  } else {
    li.classList.add('disabled');
  }
  pagination.appendChild(li);

  //list 생성 페이지 범위에 들어갈...
  for (let p = startPage; p <= endPage; p++) {
    let li = document.createElement('li');
    li.className = 'page-item';
    let a = document.createElement('a');
    a.className = 'page-link';
    a.setAttribute('href','#');
    a.setAttribute('data-page',p);
    a.innerHTML = p;
    li.appendChild(a);
    if(p==page) {
      li.classList.add('active');
      li.setAttribute('area-current','page');
    }

    pagination.appendChild(li);
  }
  //이후페이지
  li = document.createElement('li');
  li.className = 'page-item';
  a = document.createElement('a');
  a.className = 'page-link';
  a.innerHTML = 'Next';
  li.appendChild(a);
  if(next) {
    a.setAttribute('href','#');
    a.setAttribute('data-page',endPage+1);
  } else {
    li.classList.add('disabled');
  }
  pagination.appendChild(li);
  
  //이벤트 등록
  document.querySelectorAll('ul.pagination a').forEach(aTag => {
  aTag.addEventListener('click',showReplyListFunc);
  })
}