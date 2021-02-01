<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btn-1").click(function() {
			$.ajax({
				method : "post",
				url : "/replies/new",
				contentType : "application/json",
				data : '{"bno":230, "reply":"새글작성!!!!", "replyer":"newbie00"}',
				complete : function(jqXHR, status) {
					console.log("complete");
					console.log(status);
				}
			});
		});
		$("#btn-2").click(function() {
			$.ajax({
				method : "post",
				url : "/replies/new",
				contentType : "application/json",
				data : '{"reply":"새글작성!!!!", "replyer":"newbie00"}',
				complete : function(jqXHR, status) {
					console.log("complete");
					console.log(status);
				}
			});
		});
		$("#btn-3").click(function() {
			$.ajax({
				method : "post",
				url : "/replies/new",
				contentType : "application/json",
				data : '{"bno":230, "reply":"새글작성!!!!", "replyer":"newbie00"}',
				success: function(data, status, xhr) {
					console.log("댓글등록성공");
					// console.log(jqXHR.responseText); == data parameter
					console.log(data);
				},
				error: function() {
					console.log("댓글등록실패");
				}
			});
		});
		$("#btn-4").click(function() {
			$.ajax({
				method : "get",
				url : "/replies/pages/230/1",
				success: function(data) {
					console.log("댓글조회성공");
					console.log(data);
				}
			});
		});
		$("#btn-5").click(function() {
			$.ajax({
				method : "put",
				url : "/replies/101",
				contentType : "application/json",
				data : '{"reply":"수정수정수정"}',
				success: function() {
					console.log("댓글수정성공");
				},
				error: function() {
					console.log("댓글수정실패");
				}
			});
		});
		$("#btn-6").click(function() {
			$.ajax({
				method : "delete",
				url : "/replies/2",
				success: function() {
					console.log("댓글삭제성공");
				},
				error: function() {
					console.log("댓글삭제실패");
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>ajaxEx3</h1>
	<div>
		<button id="btn-1">댓글등록 성공</button>
	</div>

	<div>
		<button id="btn-2">댓글등록 실패</button>
	</div>

	<div>
		<button id="btn-3">댓글등록 성공 or 실패</button>
	</div>

	<div>
		<button id="btn-4">댓글목록</button>
	</div>
	<div>
		<button id="btn-5">댓글수정</button>
	</div>
	<div>
		<button id="btn-6">댓글삭제</button>
	</div>
</body>
</html>