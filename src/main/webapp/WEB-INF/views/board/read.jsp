<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<script>
var appRoot = '${root}';
var bno = '${board.bno}';
</script>
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
<script type="text/javascript" src="${root }/resources/js/reply.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	// console.log(replyService.name);
// 	replyService.add({reply:"js test..", replyer: "user03", bno:240}, function(result){
// 		console.log("result : " + result);
// 	}, function(er) {
// 		console.log(er);
// 	});
// 	replyService.getList({bno:230, page:1}, function(data) {
// 		console.log(data);
// 	}, function(er) {
// 		console.log("error");
// 	});
// 	replyService.remove(13, function(count) {
// 		console.log(count);
// 	}, function(er) {
// 		console.log(er);
// 	});
// 	replyService.update({rno:96, reply:"js update test"}, function(data) {
// 		console.log(data);
// 	}, function(er) {
// 		console.log(er);
// 	});
// 	replyService.read(96, function(data) {
// 		console.log(data);
// 	}, function() {
// 		console.log("error");
// 	});
});
</script>

<script type="text/javascript">
$(document).ready(function() {
	/* 댓글 목록  가져오는 함수*/
	function showList() {
		replyService.getList({bno: bno, page: ${cri.pageNo}}, function(list) {
			var replyUL = $("#reply-ul");
			// 비동기 요청때마다 중복으로 등록되는 list를 중복되지 않게 함.
			// replyUL.empty(); -> add success 함수에서 $("#reply-ul").empty();와 같음.
			replyUL.empty();
			for(var i = 0; i <list.length; i++) {
				var replyLI = '<li class="media" data-rno="' + list[i].rno + '"><div class="media-body"><h5>'
				+ list[i].replyer + '<small>' + list[i].replyDate + '</small></h5>'
				+ list[i].reply + '<hr/></div></li>';
			
				replyUL.append(replyLI);
				
			}
		});
	}
	// 댓글 쓰기 버튼 클릭 이벤트처리
	$("#replyBtn").click(function() {
		$("#replyModal").modal("show");
	});
	// 새 댓글 등록 버튼 클릭 이벤트처리
	$("#reply-submitBtn").click(function() {
		// input에서 value 저장
		var reply = $("#reply-input").val();
		var replyer = $("#replyer-input").val();	
		// ajax 요청 데이터만들기
		var data = {bno: bno, reply: reply, replyer: replyer};		
		replyService.add(data, function() {
			showList();
			alert("댓글 등록에 성공했습니다");
		}, function() {
			alert("댓글 등록에 실패했습니다");
		});
		// 모달창 닫기
		$("#replyModal").modal("hide");
		// 모달 input value 초기화
		$("#replyModal input").val("");
		});
	// ul 클릭이벤트 처리
		$("#reply-ul").on("click", "li", function() {
			// 댓글 하나 읽기
			var rno = $(this).attr("data-rno");
			replyService.read(rno, function(data) {
				$("#rno-input2").val(rno);
				$("#reply-input2").val(data.reply);
				$("#replyer-input2").val(data.replyer);
				$("#modyfy-replyModal").modal("show");
			});
		});
	// 수정버튼 이벤트
	$("#reply-modifyBtn").click(function() {
		var rno = $("#rno-input2").val();
		var reply = $("#reply-input2").val();
		var data = {rno: rno, reply:reply};
		
		replyService.update(data, function() {
			alert("댓글을 수정했습니다.");
			$("#modyfy-replyModal").modal("hide");
			showList();
		}); 
	});
	// 삭제버튼 이벤트
	$("#reply-removeBtn").click(function() {
		var rno = $("#rno-input2").val();
		replyService.remove(rno, function() {
			alert("댓글을 삭제했습니다.");
			$("#modyfy-replyModal").modal("hide");
			showList();
			
		});
		location.reload();
	});
	// 댓글 목록 가져오기 실행
	showList();
	
	
	
});
</script>
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
		
		<!-- 댓글 목록 -->
		<div class="container-md mt-3">
				<div class="row">
			<div class="col-1 col-sm-2"></div>
			<div class="col-10 col-sm-8">
				<div class="card">
					<div class="card-header d-flex justify-content-between align-items-center">
					<span>
						댓글 목록 	<c:if test="${board.replyCnt gt 0 }">
								<span>[${board.replyCnt }]</span></c:if></span>
						<button class="btn btn-primary" id="replyBtn"> 댓글쓰기</button>
					</div>
					
					<div class="card-body">
						<ul class="list-unstyled" id="reply-ul">
						<!-- 댓글 하나 -->
<!-- 							<li class="media" data-rno="24"> -->
<!-- 								<div class="media-body"> -->
<!-- 									<h5>user00 <small>2101/01/29</small></h5> -->
<!-- 									댓글 본문......... -->
<!-- 									<hr/> -->
<!-- 								</div> -->
<!-- 							</li> -->
			
						</ul>
					
					</div>
					
				</div>
			</div>
			<div class="col-1 col-sm-2"></div>
			</div>
		</div>
		<!-- 수정 댓글 modal form -->
		<div class="modal fade" id="modyfy-replyModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							수정 / 삭제
						</h5>
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>				
					<div class="modal-body">
						<div class="form-group">
							<label for="reply-input2" class="col-form-label">
							 댓글
							</label>
							<input type="text" class="form-control" id="reply-input2">
							</div>
							<div class="form-group">
							<label for="replyer-input2" class="col-form-label">
							 작성자
							</label>
							<input type="text" class="form-control" id="replyer-input2" readonly="readonly">
							</div>
							<input type="hidden" id="rno-input2">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal"> 닫기</button>
						<button type="button" class="btn btn-primary" id="reply-modifyBtn">수정</button>
						<button type="button" class="btn btn-danger" id="reply-removeBtn">삭제</button>
					</div>
					
				</div>
			</div>
		</div>
		
			<!-- 새 댓글 modal form -->
		<div class="modal fade" id="replyModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							새 댓글
						</h5>
						<button type="button" class="close" data-dismiss="modal">
							<span>&times;</span>
						</button>
					</div>				
					<div class="modal-body">
						<div class="form-group">
							<label for="reply-input" class="col-form-label">
							 댓글
							</label>
							<input type="text" class="form-control" id="reply-input">
							</div>
							<div class="form-group">
							<label for="replyer-input" class="col-form-label">
							 작성자
							</label>
							<input type="text" class="form-control" id="replyer-input">
							</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-dismiss="modal"> 닫기</button>
						<button type="button" class="btn btn-primary" id="reply-submitBtn">등록</button>
					</div>
					
				</div>
			</div>
		</div>
</body>
</html>