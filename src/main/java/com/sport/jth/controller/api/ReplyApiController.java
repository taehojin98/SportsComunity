package com.sport.jth.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.jth.config.auth.PrincipalDetails;
import com.sport.jth.model.Board;
import com.sport.jth.model.Reply;
import com.sport.jth.repository.ReplyRepository;
import com.sport.jth.repository.ResponseDto;
import com.sport.jth.service.ReplyService;

@RestController
public class ReplyApiController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글삭제
	@DeleteMapping("/board/{boardId}/delete/reply/{replyId}")
	public ResponseDto<String> delete(@PathVariable int replyId) {
		System.out.println(replyId);
		replyService.delete(replyId);
		return new ResponseDto<>(HttpStatus.OK.value(), "delete");
	}
	
	//댓글등록
	@PostMapping("/board/{boardId}/write/reply")
	public ResponseDto<String> write(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetails principal) {
		
		if(reply.getContent() == "" || reply.getContent() == null) {
			return new ResponseDto<String>(HttpStatus.BAD_REQUEST.value(), "fail");
		} else {
			replyService.write(principal.getUser(), boardId, reply);
			return new ResponseDto<String>(HttpStatus.OK.value(), "write");			
		}		
	}
	
}
