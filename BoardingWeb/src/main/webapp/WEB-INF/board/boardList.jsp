<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="../include/header.jsp"></jsp:include>
<h3>게시글 목록</h3>
<div class="center">
  <form action="boardList.do">
    <div class="row">
      <!-- 검색조건(title, writer 검색) -->
      <div class="col-sm-4">
      <select name="searchCondition" class="form-control">
        <option value="">선택하세요</option>
        <option value="T" ${search.searchCondition eq 'T' ? 'selected' : '' }>제목</option>
        <option value="W" ${search.searchCondition eq 'W' ? 'selected' : '' }>작성자</option>
        <option value="TW" ${search.searchCondition eq 'TW' ? 'selected' : '' }>제목 & 작성자</option>
      </select>
	    </div>
	    <!-- 키워드 -->
	    <div class="col-sm-5">
	      <input type="text" name="keyword" class="form-control" value="${search.keyword }"> 
	    </div>
	    <!-- 버튼 -->
	    <div class="col-sm-2">
	      <input type="submit" value="조회" class="btn btn-primary">
	    </div>
    </div><!-- end of div rows -->
  </form>
</div><!-- end of div center -->

<c:choose>
  <c:when test="${!empty message}">
    <p>no data</p>
  </c:when>
  <c:otherwise>
    <table class="table">
		  <thead>
		    <tr>
		      <th>글번호</th><th>제목</th><th>작성자</th><th>작성일시</th>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="board" items="${list }">
		    <tr>
		      <td><a href="getBoard.do?bno=${board.boardNo}&page=${paging.page}&searchCondition=${search.searchCondition}&keyword=${search.keyword}"><c:out value="${board.boardNo }"></c:out></a></td><td>${board.title }</td>
		      <td>${board.writer }</td><td><fmt:formatDate value="${board.creationDate }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
		<!-- paging -->
		<nav aria-label="...">
		  <ul class="pagination">
		    <li class="page-item ${paging.prev ? '' : 'disabled' }">
		      <a class="page-link" href="boardList.do?page=${paging.startPage-1 }&searchCondition=${search.searchCondition}&keyword=${search.keyword}">Previous</a>
		    </li>
		    <c:forEach var="pg" begin="${paging.startPage }" end="${paging.endPage }">
		      <c:choose>
		       <c:when test="${paging.page ==pg}">
		          <li class="page-item active" aria-current="page">
		            <a class="page-link" href="boardList.do?page=${pg }&searchCondition=${search.searchCondition}&keyword=${search.keyword}">${pg }</a>
		          </li>
		       </c:when>
		       <c:otherwise>
		          <li class="page-item">
		            <a class="page-link" href="boardList.do?page=${pg }&searchCondition=${search.searchCondition}&keyword=${search.keyword}">${pg }</a>
		          </li>
		       </c:otherwise>
		      </c:choose>
		    </c:forEach>
		    <li class="page-item ${paging.next ? '' : 'disabled' }">
		      <a class="page-link" href="boardList.do?page=${paging.endPage+1 }&searchCondition=${search.searchCondition}&keyword=${search.keyword}">Next</a>
		    </li>
		  </ul>
		</nav>
  </c:otherwise>
</c:choose>


<p>${paging }</p>
<p>${'문자열,숫자, boolean null'}</p>
<p>${3+5 >10 ? '참':'거짓'}</p>
<%
  String name = "hong";
%>


<c:set var="name" value="honggildong"></c:set>
<c:out value="${name}"></c:out>
<c:set var="age" value="20"></c:set>
<c:out value="${age >= 20 ? '성인' : '미성년'}"></c:out>

<c:if test="${name ne 'honggildong' }">
  <p>틀렸습니다</p>
</c:if>

<jsp:include page="../include/footer.jsp"></jsp:include>