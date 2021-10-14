let index2 = {
	init:function() {
        $("#btn-write").on("click", ()=>{
            this.write();
        });
		$("#contentTable").on("click", ()=>{
            this.blur();
        });
		$("#btn-update").on("click", ()=>{
            this.update();
        });
		$("#btn-delete").on("click", ()=>{
            this.delete();
        });
		$("#btn-cmt-write").on("click", ()=>{
            this.cmtWrite();
        });
    },

    write:function() {
        let data = {
            title: $("#title").val(),
			content: $("#content").val(),
			category: $("select[name=category]").val()
        };

        $.ajax({
            type:"POST",
            url:"/board/write",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			console.log(resp);
			var temp = data.category.toLowerCase();
			console.log(temp);
			if(resp.status === 400) {
				alert('카테고리를 선택해주세요.');
			} else if(resp.status === 204) {
				alert('제목을 입력해주세요.');
			} else if(resp.status === 503) {
				alert('제목을 입력하고 카테고리를 선택해주세요.');
			} else {
				alert('글이 정상적으로 등록되었습니다.');
				location.href = `/auth/list/${temp}`;
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	blur:function() {
		let principal = $("#principal").val();
		
		if(principal === "" || principal === null) {
			alert('로그인 후 이용해주세요.');
			location.href = "/auth/loginForm";
		}
	},
	
	update:function() {
        let data = {
			id: $("#boardId").val(),
            title: $("#title").val(),
			content: $("#content").val(),
			category: $("select[name=category]").val()
        };
		
        $.ajax({
            type:"PUT",
            url:`/board/update/${data.id}`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			var temp = data.category.toLowerCase();
			if(resp.status === 400) {
				alert('제목을 입력해주세요.');
			} else {
				alert('글이 정상적으로 수정되었습니다.');
				location.href = `/auth/list/${temp}`;
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	delete:function() {
        let data = {
			id: $("#boardId").val(),
			category: $("#boardCategory").val()
        };

        $.ajax({
            type:"DELETE",
            url:`/board/delete/${data.id}`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			var temp = data.category.toLowerCase();
			if(resp.status === 200) {
				alert('글이 정상적으로 삭제되었습니다.');
				location.href = `/auth/list/${temp}`;
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	cmtDelete:function(boardId, replyId) {
		console.log(boardId);
		console.log(replyId);
		
        $.ajax({
            type:"DELETE",
            url:`/board/${boardId}/delete/reply/${replyId}`,
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			location.href = `/auth/list/detail/${boardId}`;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	cmtWrite:function() {
        let data = {
			boardId: $("#boardId").val(),
			content: $("#contentTable").val(),
			category: $("#boardCategory").val(),
			principal: $("#principal").val()
        };
		console.log(data.category);
		if(data.principal === null || data.principal === "") {
				alert('로그인 후 이용해주세요.');
				location.href = "/auth/loginForm";
		} 
		
        $.ajax({
            type:"POST",
            url:`/board/${data.boardId}/write/reply`,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			if(resp.status === 400) {
				alert('내용을 입력해주세요.');
			} else {
				location.href = `/auth/list/detail/${data.category}/${data.boardId}`;
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

index2.init();