// console.log("reply module");

var replyService = (function(){
	function add(reply, callback, error) {
	// console.log("add method");
	console.log(reply);
	
	$.ajax({
		type: "post",
		url: appRoot + "/replies/new", //context root로 변경
		data: JSON.stringify(reply),   // form data를 json
		contentType : "application/json; charset=utf-8",
		success: function(result, status, xhr) {
		if(callback) {
		callback(result);
			}
		},
		error: function(xhr, status, er) {
		if(error) {
		error(er);
			}
		}
	});
	}
	
	function getList(param, callback, error) {
		var bno = param.bno;
		var page = param.page || 1;
		
		// javascript boolean false : 0, "", null, undefined 
		
		$.getJSON(appRoot + "/replies/pages/" + bno + "/" + page, function(data) {
			if(callback) {
				callback(data);
			}
		}).fail(function(xhr, status, er){
			if(error) {
				error(er);
			}
		});
	}
	
	function remove(rno, callback, error) {
		$.ajax({
			type: "delete",
			url: appRoot + "/replies/" + rno,
			success: function(deleteResult, status, xhr) {
				if(callback) {
					callback(deleteResult);
					}
			},
			error: function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		});
	}
	
	function read(rno, callback, error) {
		$.get(appRoot + "/replies/" + rno, function(data) {
			if(callback) {
				callback(data);
			}
		}).fail(function() {
			if(error) {
				error();
			}
		});
	}
	
	function update(reply, callback, error) {
		$.ajax({
			type: "put",
			url: appRoot + "/replies/" + reply.rno,
			data: JSON.stringify(reply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if(error) {
					error(er);
				}
			}
		});
	}
	
	return {
//	name:"AAA",
	add:add,
	getList:getList,
	remove:remove,
	update:update,
	read:read
	};
})();