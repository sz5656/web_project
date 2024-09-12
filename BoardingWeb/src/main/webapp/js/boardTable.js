/**
 * boardTabls.js
 */

 const table = new DataTable('#example', {
   ajax: 'replyTable.do?bno='+bno,
   columnDefs: [
        {
            render: (data, type, row) => '<button onclick="deleteRow(event,'+row.replyNo+')">삭제</button>',
            targets: 4
        },
        //{ visible: false, targets: [3] }
    ],
    columns: [
        { data: 'replyNo' },
        { data: 'reply' },
        { data: 'replyer' },
        { data: 'replyDate' }
    ],
    lengthMenu: [
      [5,10,20,-1],
      [5,10,20,'All']
    ],
    order: {
      idx: 0,
      dir: 'desc'
      }
  });
  
  function deleteRow(e,row) { // button의 상위상위.첫번째자식요소.html
    console.log(e);
    //.firstChild.innerHTML
    if(e.target.parentElement.parentElement.classList.contains('selected')) {
      e.stopPropagation(); // 상위요소 이벤트 차단
    }
    let dno = row;
    if(!dno) {
      alert('삭제할 댓글을 선택');
      return;
    }
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
        fetch('removeReply.do?rno='+dno)
        .then(resolve => resolve.json())
        .then(result => {
          if(result.retCode=='OK') {
              // alert('삭제성공');
            table.row('.selected').remove().draw(false);
          } else {
            alert('삭제처리 중 예외발생');
          }
        })
        .catch(err => console.log(err))
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
  
  let delNum = '';
  
  table.on('click', 'tbody tr', (e) => {
    let classList = e.currentTarget.classList;
    if (classList.contains('selected')) {
        classList.remove('selected');
        delNum = '';
    }
    else {
        table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
        classList.add('selected');
        delNum = e.currentTarget.firstChild.innerHTML;
    }
  });
  //삭제
  document.querySelector('#delReply').addEventListener('click', delReply);
  function delReply() {
    if(!delNum) {
      alert('삭제할 댓글을 선택');
      return;
    }
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
        fetch('removeReply.do?rno='+delNum)
        .then(resolve => resolve.json())
        .then(result => {
          if(result.retCode=='OK') {
              // alert('삭제성공');
            console.log('del result:'+result);
            table.row('.selected').remove().draw(false);
          } else {
            alert('삭제처리 중 예외발생');
          }
        })
        .catch(err => console.log(err))
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
  
  //등록
  document.querySelector('#addReply').addEventListener('click', addReply);
  function addReply() {
    let reply = document.querySelector('#reply').value;
    if (!writer || !reply) {
      alert('필수 입력값 입력');
      return;
    }
    console.log('bno:'+bno+' reply:'+reply+' replyer:'+writer);
    fetch('addReply.do', {
      method: 'post',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      body: 'bno='+bno+'&reply='+reply+'&replyer='+writer
    })
    .then(resolve => resolve.json())
    .then(result => {
      console.log(result.retVal);
      table.row
          .add({
            replyNo: result.retVal.replyNo,
            reply: result.retVal.reply,
            replyer: result.retVal.replyer,
            replyDate: result.retVal.replyDate
            })
          .draw(false);
          document.querySelector('#reply').value = '';
    })
    .catch(err => console.log(err));
  }
 
   
  // Automatically add a first row of data
    //addReply에 출력
  //replyNo: 111, reply: test, replyer:user02, replyDate:2023.11.11