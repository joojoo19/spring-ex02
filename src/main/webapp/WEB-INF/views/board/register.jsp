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
</head>
<body>
	<div class="container-md">
	<u:navbar/>
		<div class="row">
			<div class="col-1 col-sm-3"></div>

			<div class="col-10 col-sm-6">
						<h1>게시물 작성</h1>
			<form action="/board/register" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="input1">제목</label> <input
						type="text" class="form-control" name="title" id="input1">
				</div>
				<div class="form-group">
					<label for="textarea1">내용</label>
					<textarea class="form-control" name="content" id="textarea1"
						rows="6" ></textarea>
				</div>
								<div class="form-group">
					<label for="input2">작성자</label> <input
						type="text" class="form-control" name="writer" id="input2">
				</div>
				<div class="form-group">
					<label for="input3">파일첨부</label> <input
						type="file" class="form-control" name="file" id="input3" accept="image/*"> <!-- 여러개를 넣고 싶을때는 multiple -->
				</div>
				<button type="submit" class="btn btn-primary">등록</button>
			</form>
		</div>
		<div class="col-1 col-sm-3"></div>
	</div>
	</div>
</body>
</html>