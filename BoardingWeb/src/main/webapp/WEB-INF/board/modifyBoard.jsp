<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<h3>글 수정 페이지.</h3>
<form action="modifyBoard.do" name="actForm" method="post" enctype="multipart/form-data">
	<input type ="hidden" type="text" name = "bno" value="${boardInfo.boardNo }">
	<table class="table table-dark table-hover">
	  <tbody>
	    <tr>
	      <td>글 제목</td><td><input class="form-control" type="text" name="title" value="${boardInfo.title}"></td>
	     </tr>
	    <tr>
	      <td>글 내용</td><td><input class="form-control" type="text" name="content" value="${boardInfo.content }"></td>
	    </tr>
	    <tr>
	      <td>이미지</td><td><input type="file" name="srcImg">${boardInfo.image }</td>
	    </tr>
	  </tbody>
	</table>
	<input type ="hidden" name = "writer" value="${boardInfo.writer }">
	<input type ="hidden" name = "viewCnt" value="${boardInfo.viewCnt }">
	<input type ="hidden" name = "date" value="${boardInfo.creationDate }">
	<input type ="hidden" name = "page" value="${page}">
	<input type ="hidden" name = "keyword" value="${kw}">
	<input type ="hidden" name = "searchCondition" value="${sc}">
	<div style="text-align: right;">
	  <button type="submit" class="btn btn-primary ${boardInfo.writer ne logid ? 'disabled' : ''}" onclick="form_submit('modifyBoard.do')">저장</button>
	  <button type="button" class="btn btn-secondary" onclick="form_submit('boardList.do')">취소</button>
	</div>
</form>
<script>
  function form_submit(uri) {
    document.forms.actForm.action = uri;
    document.forms.actForm.submit();
  }
</script>

