<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<script>
  const bno = '${board.boardNo}'; // 원본글번호
  const writer = '${logid}'; // 로그인 정보
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<link rel="stylesheet" href="//cdn.datatables.net/2.1.5/css/dataTables.dataTables.min.css">
<script src="js/jquery-3.7.1.js"></script>
<script src="js/dataTables.js"></script>
<script src="js/moment.min.js"></script>

<style>
  div.reply div {
    margin: auto;
  }
  div.reply ul {
    list-style-type: none;
  }
  div.reply span {
    display: inline-block;
  }
</style>
<h3>글 상세 페이지.</h3>
<table class="table table-dark table-hover">
  <tbody>
    <tr>
      <th>글 번호</th><td colspan="2">${board.boardNo }</td><th>조회수</th><td>${board.viewCnt }</td>
     </tr>
    <tr>
      <th>글 제목</th><td colspan="4">${board.title }</td>
    </tr>
    <tr>
      <th>글 내용</th><td colspan="2">${board.content }</td>
      <td colspan="2" rowspan="3">
      <c:if test="${!empty board.image}">
        <img width="150px" src="images/${board.image }">
      </c:if>
      </td>
     </tr>
    <tr>
      <th>작성자</th><td colspan="2">${board.writer }</td>
    </tr>
    <tr>
      <th>게시일</th><td colspan="2"><fmt:formatDate value="${board.creationDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
    </tr>
  </tbody>
</table>

<form action="removeBoard.do" name="actForm">
  <input type="hidden" name="keyword" value="${kw }">
  <input type="hidden" name="searchCondition" value="${sc}">
  <input type="hidden" name="page" value="${paging.page}">
  <input type="hidden" name="bno" value="${board.boardNo}">
</form>

<div style="text-align: right;">
  <button type="button" class="btn btn-primary ${board.writer ne logid ? 'disabled' : ''}" onclick="form_submit('modifyFormBoard.do')">수정하기</button>
  <button type="button" class="btn btn-secondary" onclick="form_submit('boardList.do')">목록으로</button>
  <button type="button" class="btn btn-danger" onclick="form_submit('removeBoard.do')">삭제</button>
  <c:if test="${!empty message }">
    <span style="color: red;">${message}</span>
  </c:if>
</div>
<!-- 댓글관련 -->
<div class="container reply">
  <!-- 댓글등록 -->
  <div class="header">
    <input type="text" id="reply" class="col-sm-9">
    <button id="addReply" class="btn btn-primary">댓글등록</button>
    <button id="delReply" class="btn btn-danger">댓글삭제</button>
  </div>
  <!-- 댓글목록 -->
  <table id="example" class="display" style="width:100%">
    <thead>
        <tr>
            <th>댓글번호</th>
            <th>댓글내용</th>
            <th>작성자</th>
            <th>작성일시</th>
        </tr>
    </thead>
    <tfoot>
        <tr>
            <th>Name</th>
            <th>Position</th>
            <th>Office</th>
            <th>Extn.</th>
        </tr>
    </tfoot>
  </table>
  <!-- 댓글페이징 -->
  
</div>

<script>
  
  function form_submit(uri) {
	  document.forms.actForm.action = uri;
	  document.forms.actForm.submit();
  }
</script>

<script src="js/boardTable.js"></script>
