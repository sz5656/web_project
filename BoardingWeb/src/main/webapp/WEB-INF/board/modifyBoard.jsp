<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<h3>글 수정 페이지.</h3>
<input type ="hidden" name = "bno" value="${board.boardNo }">
<table class="table table-dark table-hover">
  <tbody>
    <tr>
      <td>글 제목</td><td><input class="form-control" type="text" name="title" value="${board.title}"></td>
     </tr>
    <tr>
      <td>글 내용</td><td><input class="form-control" type="text" name="content" value="${board.content }"></td>
    </tr>
  </tbody>
</table>
<input type ="hidden" name = "writer" value="${board.writer }">
<input type ="hidden" name = "viewCnt" value="${board.viewCnt }">
<input type ="hidden" name = "date" value="${board.creationDate }">
<div style="text-align: right;">
  <button type="submit" class='btn btn-primary' onclick="location.href = 'boardList.do'">저장</button>
  <button class='btn btn-secondary'>취소</button>
</div>


<jsp:include page="../include/footer.jsp"></jsp:include>