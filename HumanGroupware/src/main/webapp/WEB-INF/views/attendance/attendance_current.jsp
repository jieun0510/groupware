<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>근태 현황</title>
<link type="text/css" rel="stylesheet" href="/resources/css/attendance_current.css">
</head>
<body>
<!-- 임시 링크용 -->
<div class="option" style="border:0.1px solid black; width:150px;text-align:center;">
	<a>[임시 링크용 div]</a>
	<div>
		<a href="/employee/organization">조직도</a><br>
		<a href="/employee/inquiry">직원조회</a><br>
		<a href="/employee/registration">부서변경</a><br>
		<a href="/attendance/current">근태현황</a><br>
		<a href="/attendance/management">근태관리</a><br>
		<a href="/attendance/byEmployee">사원별 근태현황</a>
		<input type=button value="출근">
		<input type=button value="퇴근">
	</div>
</div>
<!-- 임시 링크용 -->
<div class="Mysession_container">
		<div id="Show-img_box"></div>
		<div id="MY_box">
			<% if(session.getAttribute("emp_name") != null && session.getAttribute("emp_id")!="") {%>
				이름: ${ emp_name} 
				<div id=emp_depart>부서: </div>
				<div id="My_box1">
				<a href='/employee/mypage'>마이페이지</a>
				<a href='/employee/logout'>로그아웃</a>
				</div>
			<% } else {%>
				로그인 후 이용해주세요
				<div class="My_box2">
				<a href='/employee/login'>로그인</a>
				<a href="/employee/signin">회원가입</a><br>
				</div>
			<% } %>
		</div>
	</div>	
<div>
	<div class="inquiry_main">
		<a>근태현황</a>
	</div>
</div>
	
	<div>
		<div>
			<div>
				월별 확인
				 <label>1</label><input type="checkbox" id=checkboxCurrent>
				 <label>2</label><input type="checkbox" id=checkboxCurrent>
				 <label>3</label><input type="checkbox" id=checkboxCurrent>
				 <label>4</label><input type="checkbox">
				 <label>5</label><input type="checkbox">
				 <label>6</label><input type="checkbox">
				 <label>7</label><input type="checkbox">
				 <label>8</label><input type="checkbox">
				 <label>9</label><input type="checkbox">
				 <label>10</label><input type="checkbox">
				 <label>11</label><input type="checkbox">
				 <label>12</label><input type="checkbox">
			</div>
			<div>
				근태 현황
			</div>
		</div>
		<div>
			현황 테이블
		</div>
		<div>
			달력
		</div>
		<div>
			<div>
				이번달 출근 일수 <input type=text style="width:50px;">
			</div>
			<div>
				이번달 지각 일수 <input type=text style="width:50px;">
			</div>
			<div>
				이번달 야근 일수 <input type=text style="width:50px;">
			</div>
			<div>
				이번달 외근 일수 <input type=text style="width:50px;">
			</div>
		</div>
	</div>
</div>
	
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(document).ready(function(){
	
})

</script>
</html>