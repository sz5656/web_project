<%@page import="com.yedam.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <h3>회원수정화면</h3>
  <form action="modifyMember.do">
    <input type ="hidden" name = "id" value="${memberInfo.memberId}">
	  <table class="table">
	    <tr>
	      <th>회원이름</th><td><input class="form-control" type="text" name="name" value="${memberInfo.memberName}"></td>
	    </tr>
	    <tr>
	      <th>비밀번호</th><td><input class="form-control" type="text" name="pass" value="${memberInfo.password}"></td>
	    </tr>
	    <tr>
	      <th>이메일</th><td><input class="form-control" type="text" name="email" value="${memberInfo.email}"></td>
	    </tr>
	    <tr>
	      <th>Authority</th><td>${memberInfo.authority}</td>
	    </tr>
	    <tr>
	      <th>가입일자</th><td>${memberInfo.creationDate}</td>
	    </tr>
	    <tr>
	      <td colspan="2" align="center">
	      <button type="submit" class='btn btn-primary' onclick="location.href = 'memberList.do'">저장</button>
	      <button class='btn btn-secondary'>취소</button>
	      <button type="button" class="btn btn-danger" onclick="location.href = 'removeBoard.do'">삭제</button>
	      </td>
	    </tr>
	  </table>
  </form>
