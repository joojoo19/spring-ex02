<%@ tag language="java" pageEncoding="UTF-8"%>
<script>
	$(document).ready(function() {
		var result = '${result}';
		var message = '${message}';
		// checkModal(result);
		 checkModal2(message);
		history.replaceState({}, null, null);
		
		function checkModal2(message) {
			if (message && history.state == null) {
				$("#myModal .modal-body p").html(message);
				
			}
			$("#myModal").modal("show");
		}
// 		function checkModal(result) {
// 			if(result == '' || history.state) {
// 				return;
// 			}
// 			if (parseInt(result) > 0) {
// 				$("#myModal .modal-body p").html("게시글" + result +"번이 등록되었습니다");
				
// 			}
// 			$("#myModal").modal("show");
// 		}
	});
</script>


<div class="modal" id="myModal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">알림</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>처리가 완료 되었습니다.</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
