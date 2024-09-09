<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>스크립트 연습</h3>
<div id="show">
  <!-- <p>이름:~~</p>
  <p>연락처:~~</p> -->
  <!-- 
  <ul>
    <li>이름:김, 연락처 1234-1234</li>
    <li>이름:김, 연락처 1234-1234</li>
    <li>이름:김, 연락처 1234-1234</li>
  </ul>
   -->

</div>

<table>
  <tr>
    <th>id</th><td><input type="text" id="id"></td>
  </tr>
  <tr>
    <th>name</th><td><input type="text" id="name"></td>
  </tr>
  <tr>
    <th>point</th><td><input type="text" id="point"></td>
  </tr>
  <tr>
    <td colspan="2"><button id="addBtn">추가</button></td>
  </tr>
</table>

<table id="target">
  <thead>
    <tr>
      <th>아이디</th><th>이름</th><th>포인트</th>
    </tr>
  </thead>
  <tbody id="list">
    <tr>
      
    </tr>
  </tbody>
</table>

<div>
  salary: <input id="salary">
  gender: <select id="gender">
            <option value='M'>남성</option>
            <option value='Female'>여성</option>
          </select>
          <button id="searchBtn">검색</button>
</div>

<h3>목록</h3>
<table class="table">
  <tr>
    <td>댓글내용: <input type="text" id="reply"></td>
    <td><button id="addReply">댓글등록</button></td>
  </tr>
</table>


<table class="table">
  <thead>
	  <tr>
	    <th><input type="checkbox"></th>
	    <th>댓글번호</th>
	    <th>댓글내용</th>
	    <th>작성자</th>
	    <th>작성일시</th>
	    <th><input type="button" value="선택삭제" id="delChecked"></input></th>
	  </tr>  
  </thead>
  <tbody class="list">
	  <tr>
	    
	  </tr>  
  </tbody>
</table>

<script src="js/reply.js"></script>
<!--
<script src="js/ajax.js"></script>
<script src="js/xmlhttprequest.js"></script>
 
<script src="js/basic.js"></script>
<script src="js/basic2.js"></script>
<script src="js/basic3.js"></script>
<script src="js/data.js"></script>
<script src="js/basic4.js"></script>
 -->