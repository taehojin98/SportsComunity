var isChecked = false;
var isVerified = false;
var tempCode = "";
var tempEmail = "";
var tempUsername = "";
var pwValidation = false;

let index = {	
	
    init:function() {
        $("#btn-join").on("click", ()=>{
            this.save();
        });
		$("#btn-login").on("click", ()=>{
            this.login();
        });
		$("#btn-check").on("click", ()=>{
            this.check();
			isChecked=true;
        });
		$("#username").on("click", ()=>{
            isChecked=false;
        });
		$("#username").on("blur", ()=>{
            isChecked=false;
        });
		$(".re-password").on("keyup", ()=>{
            this.pcheck();
        });
		$(".password").on("keyup", ()=>{
            this.pcheck();
        });
		$("#btn-enter").on("click", ()=>{
            this.enter();
        });
		$("#btn-enter2").on("click", ()=>{
            this.enter2();
        });
		$("#btn-update").on("click", ()=>{
            this.update();
        });
		$("#btn-delete").on("click", ()=>{
            this.delete();
        });
		$("#category").on("click", ()=>{
            this.category();
        });
		$("#userDetail").on("click", ()=>{
            this.getinfo();
        });
		$("#btn-mylists").on("click", ()=>{
            this.getlists();
        });
		$("#btn-send-mail").on("click", ()=>{
			isVerified = false;
            this.sendmail();
        });
		$("#btn-check-mail").on("click", ()=>{
            this.checkmail();
        });
		$("#btn-email-enter").on("click", ()=>{
			isVerified = false;
            this.sendmail();
        });
		$("#btn-email-enter2").on("click", ()=>{
            this.findpwmail();
        });
		$("#btn-code-enter").on("click", ()=>{
            this.checkcode();
        });
		$("#btn-code-enter2").on("click", ()=>{
            this.checkcode2();
        });
		$("#btn-username-enter").on("click", ()=>{
            this.checkusername();
        });
		$("#password").on("keyup", ()=>{
            this.validationpw();
        });
    },

    save:function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
			repassword: $("#re-password").val(),
			email: $("#email").val()
        };
		console.log(pwValidation);
		if((data.username === "" && (data.password === "" || data.password !== "") && data.repassword === ""  && data.email === "") ||  
		(data.username === "" && data.password === "" && (data.repassword === "" || data.repassword !== "")  && data.email === "")) {
			alert('아이디와 비밀번호와 이메일을 입력해주세요.');
			return;
		} else if((data.username === "" && (data.password === "" || data.password !== "") && data.repassword === ""  && data.email !== "") ||  
		(data.username === "" && data.password === "" && (data.repassword === "" || data.repassword !== "")  && data.email !== "")) {
			alert('아이디와 비밀번호를 입력해주세요.');
			return;		
		} else if(data.username === "" && data.email === "" && (data.password !== "" && data.repassword !== "" && data.password === data.repassword)) {
			alert('아이디와 이메일을 입력해주세요.')
			return;
		} else if((data.username !== "" && (data.password === "" || data.password !== "") && data.repassword === ""  && data.email === "") ||  
		(data.username !== "" && data.password === "" && (data.repassword === "" || data.repassword !== "")  && data.email === "")) {
			alert('이메일과 비밀번호를 입력해주세요.')
			return;
		} else if(data.username !== ""  && data.email !== "" && data.password === "" || data.repassword === "") {
			alert('비밀번호를 입력해주세요.');
			return;
		} else if(data.username === ""  && data.email !== "" && (data.password !== "" && data.repassword !== "" && data.password === data.repassword)) {
			alert('아이디를 입력해주세요.');
			return;
		} else if(data.username !== ""  && data.email === "" && (data.password !== "" && data.repassword !== "" && data.password === data.repassword)) {
			alert('이메일울 입력해주세요.');
			return;
		} else if(data.username !== ""  && data.email !== "" && (data.password !== "" && data.repassword !== "" && data.password === data.repassword) && isChecked === false) {
			alert('아이디 중복확인를 해주세요.');
			return;
		} else if(data.username !== ""  && data.email !== "" && (data.password !== "" && data.repassword !== "" && data.password === data.repassword) && isChecked === true && isVerified === false) {
			alert('이메일 인증을 해주세요.');
			return;
		} else if(pwValidation === false) {
			alert('비밀번호를 확인해주세요.');
			return;
		}
		
        $.ajax({
            type:"POST",
            url:"/auth/joinProc",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			console.log(pwValidation);
			if(resp.status === 200 && resp.data === 'true' && isChecked === true && isVerified === true && pwValidation === true) {
				isVerified = false;
				alert('회원가입이 완료되었습니다.');
				location.href="/";
			} else if(resp.status === 204 && resp.data === 'false') {
				alert('비밀번호를 확인해주세요.');
			} else if(resp.status === 204 && resp.data === 'true') {
				alert('아이디와 비밀번호를 확인해주세요.');
			} else if(resp.status === 409 && resp.data === 'false') {
				alert('아이디 중복확인을 해주세요.');
			} else {
				alert('비밀번호를 확인해주세요.');
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	check:function() {
        let username = $("#username").val();
		var check = /^[a-z0-9]{5,15}$/.test(username);

		if(username === "") {
			alert('아이디를 입력해주세요.');
			return;
		}
		
		if(!check) {
			alert('아이디 형식에 맞게 입력해주세요.');
			isCheck = false;
			return;
		}

        $.ajax({
            type:"GET",
            url:`/auth/joinCheck/${username}`,
            data:JSON.stringify(username),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			
            if(resp.status === 200) {
				isChecked = true;
                $("#msg1").show();
                $("#msg2").hide();
			} else if(resp.status === 400) {
				isChecked = false;
				alert('아이디를 입력해주세요.');
			} else {
				isChecked = false;
                $("#msg2").show();
                $("#msg1").hide();
            }
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	pcheck:function() {
		let data = {
			password: $("#password").val(),
			repassword: $("#re-password").val()
		};
		
		if(data.password != data.repassword) {
			$("#check-password").html('비밀번호가 일치하지 않습니다.');
		} else {
			$("#check-password").html('');
		}
	},
	
	enter:function() {
        let data = {
			id: $("#id").val(),
			password: $("#password").val()
		};
		
		if(data.password === "") {
			alert('비밀번호를 입력해주세요.');
			return;
		}

        $.ajax({
            type:"POST",
            url:'/enter',
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
            if(resp.status === 200) {
				location.href="/user/updateForm";
			} else {
				alert('비밀번호를 다시 입력해주세요.');
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	enter2:function() {
        let data = {
			id: $("#id").val(),
			password: $("#password").val()
		};
		
		if(data.password === "") {
			alert('비밀번호를 입력해주세요.');
			return;
		}

        $.ajax({
            type:"POST",
            url:'/enter',
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
            if(resp.status === 200) {
				location.href="/user/deleteForm";
			} else {
				alert('비밀번호를 다시 입력해주세요.');
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	update:function() {
        let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			repassword: $("#re-password").val()
		};
		var check = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/.test(data.password);
		
		if(data.password === "" || data.repassword === "") {
			alert('비밀번호를 입력해주세요.');
			return;
		} else if(data.password !== data.repassword) {
			alert('비밀번호를 확인해주세요.');
			return;
		} else if(!check) {
			alert('비밀번호 양식을 맞춰주세요.');
			return;
		}

        $.ajax({
            type:"PUT",
            url:'/user/update',
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
            if(resp.status === 200) {
				alert('비밀번호 변경이 완료되었습니다.');
				location.href="/";
			} else {
				alert('비밀번호를 확인해주세요.');
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	delete:function() {
        let id = $("#id").val();

        $.ajax({
            type:"DELETE",
            url:`/user/delete/${id}`,
            data:JSON.stringify(id),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
            if(resp.status === 200) {
				alert('탈퇴가 완료되었습니다.');
				location.href="/logout";
			} else {
				alert('비밀번호를 확인해주세요.');
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	category:function() {
       	let category = $("#category").text();
        $.ajax({
            type:"GET",
            url:`/auth/list/${category}`,
            data:JSON.stringify(category),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			location.href = `/auth/list/${category}`;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	getinfo:function() {
       	let id = $("#userId").val();

		console.log(id);
        $.ajax({
            type:"GET",
            url:`/detailForm/${id}`,
            data:JSON.stringify(id),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			location.href = `/detailForm/${id}`;
        }).fail(function(error) {
			location.href = `/detailForm/${id}`;
        });
    },

	getlists:function() {
       	let id = $("#userId").val();

        $.ajax({
            type:"GET",
            url:`/user/mylistForm/${id}`,
            data:JSON.stringify(id),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			location.href = `/user/mylistForm/${id}`;
        }).fail(function(error) {
			location.href = `/user/mylistForm/${id}`;
        });
    },

	login:function() {
       	let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};

		if(data.username === "" && data.password === "") {
			alert('아이디와 비밀번호를 입력해주세요.');
		} else if(data.username !== "" && data.password === "") {
			alert('비밀번호를 입력해주세요.');
		} else if(data.username === "" && data.password !== "") {
			alert('아이디를 입력해주세요.');
		}
    },

	checkmail:function() {
       	let code = $("#email-code").val();
		
		if(code === "") {
			alert('인증번호를 입력해주세요.');
			return;
		} else if(code === tempCode) {
			$("#emailmsg1").show();
            $("#emailmsg2").hide();
			isVerified = true;
			return;
		} else if (code !== tempCode) {
			$("#emailmsg1").hide();
            $("#emailmsg2").show();
			isVerified = false;
			return;
		}
    },

	sendmail:function() {
       	let email = $("#email").val();
		
		if(email === "") {
			alert('이메일을 입력해주세요.');
			return;
		}	
		
        $.ajax({
            type:"POST",
            url:"/auth/send/email",
            data:JSON.stringify(email),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			if(resp.status === 409) {
				alert('이메일 하나로 가입할 수 있는 아이디는 3개입니다.');
			}
	
			if(resp.status === 200) {
				tempEmail = email;
				tempCode = resp.data;
				alert('인증번호가 발송되었습니다.');
			} else {
				alert('이메일 양식을 다시 입력해주세요.');
			}
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	checkcode:function() {
       	let code = $("#code").val();
		let email = $("#email").val();
		
		if(code === "") {
			alert('인증번호를 입력해주세요.');
			return;
		} else if(email !== tempEmail) {
			alert('인증되지 않은 이메일입니다.');
			return;
		} else if(code !== tempCode) {
			alert('인증번호를 확인해주세요.');
			return;
		}
		
		$.ajax({
            type:"GET",
            url:`/auth/findid/${email}`,
            data:JSON.stringify(email),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			location.href = `/auth/findid/${email}`;
        }).fail(function(error) {
            location.href = `/auth/findid/${email}`;
        });
    },

	checkusername:function() {
       	let username = $("#username").val();
		
		if(username === "") {
			alert('아이디를 입력해주세요.');
			return;
		}
		
		$.ajax({
            type:"GET",
            url:`/auth/joinCheck/${username}`,
            data:JSON.stringify(username),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			if(resp.status !== 200) {
				location.href = "/auth/findpwForm2";
			} else {
				alert('해당 아이디가 존재하지 않습니다.');
				return;
			}
        }).fail(function(error) {
			alert(JSON.stringify(error));
        });
    },

	findpwmail:function() {
       	let email = $("#email").val();
		
		if(email === "") {
			alert('이메일을 입력해주세요.');
			return;
		}
		
		$.ajax({
            type:"POST",
            url:"/auth/findpw/mail",
            data:JSON.stringify(email),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			if(resp.status === 400) {
				alert('가입시에 기입한 아이디와 이메일이 일치하지 않습니다.');
			} else {
				tempEmail = email;
				tempCode = resp.data;
				alert('인증번호가 발송되었습니다.');
			}
        }).fail(function(error) {
			alert(JSON.stringify(error));
        });
    },

	checkcode2:function() {
       	let code = $("#code").val();
		let email = $("#email").val();
		
		if(code === "") {
			alert('인증번호를 입력해주세요.');
			return;
		} else if(email !== tempEmail) {
			alert('인증되지 않은 이메일입니다.');
			return;
		} else if(code !== tempCode) {
			alert('인증번호를 확인해주세요.');
			return;
		}
		
		$.ajax({
            type:"PUT",
            url:"/auth/findpw/temppw",
            data:JSON.stringify(email),
            contentType:"application/json; charset=utf-8",
            dataType:"json"
        }).done(function(resp) {
			alert('해당 이메일 주소로 임시 비밀번호를 전송하였습니다.');
			location.href = "/";
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    },

	validationpw:function() {
       	let password = $("#password").val();
		var check = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/.test(password);
		console.log(check);
		
		if(check) {
			$("#validation").hide();
			pwValidation = true;
		} else {
			$("#validation").show();
			pwValidation = false;
		}
	}
	
}

index.init();