<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<h3>등록화면</h3>
<form action="addBoard.do" method="post" enctype="multipart/form-data"> <!-- file upload -> enctype="multipart/form-data -->
  <input type="hidden" name="writer" value="${logid}">
  <table class="table">
    <tr>
      <th>제목</th><td><input class="form-control" name="title" type="text"></td>  
    </tr>
    <tr>
      <th>내용</th><td><textarea class="form-control" name="content"></textarea></td>  
    </tr>
    <tr>
      <th>작성자</th><td>${logid}</td>  
    </tr>
    <tr>
      <th>이미지</th><td><input type="file" name="srcImg"></td>  
    </tr>
    <tr>
      <td colspan="2"><input class="btn btn-primary" type="submit"><input class="btn btn-warning" type="reset"></td>  
    </tr>
  </table>
</form>

<jsp:include page="../include/footer.jsp"></jsp:include>