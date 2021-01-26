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
			<div class="col-1 col-sm-2"></div>
			<div class="col-10 col-sm-8">
				<h1>read</h1>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>번호</td>
							<td>${board.bno }</td>
							<td>작성일</td>
							<td><fmt:formatDate value="${board.regdate}"
									pattern="yyyy-MM-dd HH:mm" /></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td>${board.writer }</td>
							<td>수정일</td>
							<td><fmt:formatDate value="${board.updateDate}"
									pattern="yyyy-MM-dd HH:mm" /></td>
						</tr>
						<tr>
							<td>제목</td>
							<td colspan="3"><c:out value="${board.title }"/></td>
						</tr>
						<tr>
							<td colspan="4">내용</td>
						</tr>
						<tr>
							<td colspan="4"><textarea cols="30" rows="9"
									class="form-control" name="content"><c:out value="${board.content }"/> </textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="d-flex bd-highlight mb-3">
					<div class="mr-auto bd-highlight">
						<a
							href="${root }/board/list.do?pageNo=${cri.pageNo}&amount=${cri.amount}&type=${cri.type}&keyword=${cri.keyword}">
						<button type="submit" class="btn btn-primary">목록</button></a>
					</div>
					<div class="pr-1 bd-highlight">
										<c:url value="/board/modify" var="modifyLink">
						<c:param name="bno" value="${board.bno }"></c:param>
						<c:param name="pageNo" value="${cri.pageNo }"></c:param>
						<c:param name="amount" value="${cri.amount }"></c:param>
						<c:param name="type" value="${cri.type }"></c:param>
						<c:param name="keyword" value="${cri.keyword }"></c:param>

					</c:url>
						<a
							href="${modifyLink}"><button
								type="submit" class="btn btn-primary">수정</button></a>
					</div>

				</div>
				<div class="col-1 col-sm-2"></div>
			</div>
		</div>
</body>
</html>