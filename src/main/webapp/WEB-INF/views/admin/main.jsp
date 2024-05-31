<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 메인</title>
<link rel="stylesheet" href="/heehee/resources/css/admin/main.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div id="bodyContainer">
	
	<%@ include file="../common/admin/sideMenu.jsp" %>

	<div id="mainMenuContainer">
		<div id="totalOrder">
		<p class="mainTitle">전체 주문통계</p>
			<div id="totalOrderCol">
			<div id="allTable">
			<table>
				<thead>
					<tr>
						<th>회원번호</th>
						<th>회원명</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
					<tr>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		<div id="recentOrder">
			<div id="recentOrderTop"><p class="mainTitle">최근 주문내역</p><button class="productDetailBtn">상세정보 바로가기</button></div>
			<div id="allTable">
			<table>
				<thead>
					<tr>
						<th>회원번호</th>
						<th>회원명</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
					<tr>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
		<div id="recentQuestion">
			<div id="recentQuestionTop"><p class="mainTitle">최근 문의내역</p><button class="questionsBtn">1:1문의 바로가기</button></div>
			<div id="allTable">
			<table>
				<thead>
					<tr>
						<th>회원번호</th>
						<th>회원명</th>
						<th>아이디</th>
						<th>이메일</th>
						<th>전화번호</th>
						<th>주소</th>
						<th>가입일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
						<td>ajax</td>
					</tr>
				</tbody>
			</table>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>