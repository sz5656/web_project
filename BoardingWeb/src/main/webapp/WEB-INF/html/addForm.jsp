<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<meta charset="UTF-8">
<title>회원등록 페이지.</title>
</head>
<body>
  <%
     // 자바영역.
     String message = (String) request.getAttribute("message");
  %>
  <%if (message != null) {  %>
  <p style="color:red"><%=message %></p>
  <%} %>
  <!-- 주석구문. -->
  <form action="addMember.do" method="get">
  <table class = 'table'>
    <tr><th>회원아이디: <input class = "form-control" type="text" name="id"></th></tr>
    <tr><th>회원이름: <input class = "form-control" type="text" name="name"></th></tr>
    <tr><th>비밀번호: <input class = "form-control" type="password" name="pass"></th></tr>
    <tr><th>이메일: <input class = "form-control" type="email" name="email"></th></tr>
    <tr><td colspan="2" align="center">
      <input class = "col-sm-2 btn btn-primary" type="submit">
      <input class = "col-sm-2 btn btn-secondary" type="reset">
      </td></tr>
   </table>
  </form>
