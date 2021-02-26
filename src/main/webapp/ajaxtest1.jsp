<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.15.2/js/all.js" data-auto-replace-svg="nest"></script>
<link rel="stylesheet" type="text/css" href="root/resources/css/font.css">
<title>Insert title here</title>
<script>
$(document).ready(function() {
	$("#test-btn1").click(function() {
		$.ajax({
			url: '${root}' + "/aj/t1",
			success: function(r) {
				$("#result").html(r);
			}
		});
	});
	$("#test-btn2").click(function() {
		$.ajax({
			url: '${root}' + "/aj/t2",
			dataType: 'json',
			success: function(r) {
				$("#result").append(r.name);
				$("#result").append(r.email);
			}
		});
	});
	$("#test-btn3").click(function() {
		$.ajax({
			url: '${root}' + "/aj/t3",
			dataType: 'json',
			success: function(r) {
				console.log(r);
				for (var i = 0; i < r.length; i++) {
					console.log(r[i].email);
				}
			}
		});
	});
	$("#test-btn4").click(function() {
		$.ajax({
			url: '${root}' + "/aj/t3",
			method: 'post',
			data: {},
			dataType: 'json',
			success: function(r) {
				console.log(r);
				for (var i = 0; i < r.length; i++) {
					console.log(r[i].email);
				}
			}
		});
	});
});

</script>
</head>
<body>
<div class="container-sm">
   <div class="row">
      <div class="col-12 col-sm-6 offset-sm-3">
  		<h1>ajax</h1>
  		<button id='test-btn1' >test1</button>
  		<button id='test-btn2' >test2</button>
  		
  		<button id='test-btn3' >test3</button>
  		
  		<p id="result"></p>
      </div>
   </div>
</div>
</body>
</html>