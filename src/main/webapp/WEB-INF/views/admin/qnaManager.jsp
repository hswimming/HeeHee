<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>희희낙찰 관리자 페이지 - 고객지원</title>
<link rel="stylesheet" href="/heehee/resources/css/admin/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div id="bodyContainer">
<%@ include file="../common/admin/sideMenu.jsp" %>
	<div id="mainMenuContainer">
	<p class="mainTitle">1:1 상담문의</p>
		<p class="searchTitle">문의사항 검색</p>
		<div id="searchContainer">
		<div id="btmContainer">
		<div class="containerHeadBlock"><p class="searchContext">검색어</p></div>
		<select>
			<option>등록번호</option>
			<option>카테고리</option>
			<option>세부 카테고리</option>
			<option>판매자ID</option>
			<option>상태</option>
		</select>
		<input type="text" placeholder="입력란 (제목 혹은 내용 입력)">
		<button class="commonSmallBtn">검색</button>
		<button class="commonSmallBtn">초기화</button>
		</div>
		</div>
		<div id="btmContainer">
		<p class="detailTitle">문의 내용</p>
		<button class="commonSmallBtn">수정</button>
		<button class="commonSmallBtn">삭제</button>
		</div>
		<div id="allTable">
			<table>
				<thead>
					<tr>
						<th>checkbox</th>
						<th>번호</th>
						<th>유형</th>
						<th>제목</th>
						<th>작성자ID</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>checkbox</td>
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
</body>
</html>