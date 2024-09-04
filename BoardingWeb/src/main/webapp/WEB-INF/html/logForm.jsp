<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<h3>로그인 화면</h3>
<form action="login.do" method="post">
	<table class="table">
	  <tr>
	    <th>아이디</th><td><input type="text" name="id"></td>
	  </tr>
	  <tr>
	    <th>비밀번호</th><td><input type="password" name="pw"></td>
	  </tr>
	  <tr>
	    <td><input type="submit" value="로그인"></td>
	  </tr>
  </table>
</form>

<jsp:include page="../include/footer.jsp"></jsp:include>