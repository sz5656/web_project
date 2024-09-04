<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<h3>글 상세 페이지.</h3>
<table class="table table-dark table-hover">
  <tbody>
    <tr>
      <td>글 제목</td><td>${board.title }</td>
     </tr>
    <tr>
      <td>글 생성자</td><td>${board.writer }</td>
    </tr>
    <tr>
      <td>글 내용</td><td>${board.content }</td>
    </tr>
    <tr>
      <td>조회수</td><td>${board.viewCnt }</td>
    </tr>
    <tr>
      <td>게시일</td><td><fmt:formatDate value="${board.creationDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
    </tr>
  </tbody>
</table>
<div style="text-align: right;">
  <button type="button" class="btn btn-primary" onclick="location.href='modifyFormBoard.do?bno=${board.boardNo}'">수정하기</button>
  <button type="button" class="btn btn-secondary" onclick="location.href='boardList.do?page=${paging.page}'">목록으로</button>
</div>


<jsp:include page="../include/footer.jsp"></jsp:include>