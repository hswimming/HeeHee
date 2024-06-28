$(function() {
	$(".content").hide();
	$("#img_preview").on("click", change);
	$(".header").on("click", show);
	$("#myQna .header").each(function() {
		var $ansStatus = $(this).find(".ansStatus");
		var $qnaAns = $(this).next(".content").find(".qnaAns");

		if ($qnaAns.text().trim() !== "") {
			$ansStatus.text("답변완료");

		} else {
			$ansStatus.text("답변대기");
			$ansStatus.css("background-color", "#ABC3FF");

		}
	});
	$("#cancel").on("click", reset);
});

function change() {
	$("#input_file").click();
}
function show() {
	if ($(this).next().css("display") != "none") {
		$(".content").hide();
		$(".header").removeClass("select");
	} else {
		$(".content").hide();
		$(this).next().show();
		$(".header").removeClass("select");
		$(this).addClass("select");
	}
}
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#preview').attr('src', e.target.result);
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		$('#preview').attr('src', "");
	}
}
function reset() {
	$("#qna input, textarea").val("");
	$("input:radio[name='SEQ_QNA_OPTION']").removeAttr("checked");
}

function showQnaOptionContent(optionContent) {
	$('#qnaOptionContent').text(optionContent);
}