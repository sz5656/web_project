/**
 * replyBoard.js
 * replayService에 정의된 메소드를 통해서 기능 실행
 */

/**
 * 이벤트(댓글등록)
 */
document.querySelector('#addReply').addEventListener('click',addReplyFunc);

//댓글출력
svc.replyList(bno,function(result) {
   console.log(result);
   result.forEach(reply => {
    document.querySelector('div.content ul').appendChild(makeLi(reply));
   });
  }, function(err) {
    alert(err);
  }//실패했을 때 실행
);
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
            e.target.parentElement.parentElement.remove();
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
      document.querySelector('div.content ul').appendChild(makeLi(result.retVal));
    } else {
      alert('추가중 예외발생');
    }
    document.querySelector('#reply').value = '';
  },
  function(err) {
    console.log(err);
  });
}