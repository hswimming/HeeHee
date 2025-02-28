<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.servletContext.contextPath}" />

<body>
	<script>
		$(function() {
			$("#accountModal").hide();
			
			$("#btn-account").on("click", show);
			$("#accountModalClose").on("click", hide);
			$("#btn_aModal_cancel").on("click", hide);
			function show() {
				$("#accountModal").show();
				$("body").css("overflow", "hidden"); 
			}
			function hide() {
				$("#accountModal").hide();
				$("body").css("overflow", "scroll"); 
			}
		});
	</script>

	<div id="accountModal" class="profileModal">
		<div class="profileModalBody">
			<img
				src="https://sh-heehee-bucket.s3.ap-northeast-2.amazonaws.com/images/header/icon_login_close.png"
				alt="로그인 창 닫기 아이콘" class ="profileModalClose" id="accountModalClose">
			<form class="modal_form" action="${path}/mypage/profile/updateAcc" method="post">

				<p class="modal_info">현재 계좌:</p>
				<p>${profile.bank}</p>
				<p>${profile.accountNum}</p>

				<div class="input">
					<div class="Modal_bank">
						<select name="bankSeq" required>
							<option value="">은행</option>
							<c:forEach var="banklist" items="${bankList}">
								<option value="${banklist.bankSeq}">${banklist.bank}</option>
							</c:forEach>
						</select>
					</div>

					<div class="Modal_bank">
						<input type="text" name="accountNum" placeholder="계좌번호를 입력하세요" required="required">
					</div>
				</div>

				<div class="btn_modal">
					<button type="submit" class="btn_submit">저장</button>
				</div>
			</form>
		</div>
	</div>
</body>