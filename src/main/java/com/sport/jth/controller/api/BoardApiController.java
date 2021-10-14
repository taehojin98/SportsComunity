package com.sport.jth.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.jth.config.auth.PrincipalDetails;
import com.sport.jth.model.Board;
import com.sport.jth.repository.ResponseDto;
import com.sport.jth.service.BoardService;

@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	//글쓰기
	@PostMapping("/board/write")
	public ResponseDto<String> write(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetails principal ) {
		System.out.println(principal.getUser());
		System.out.println(board.getCategory());
		if((board.getTitle() == "" || board.getTitle() == null) && board.getCategory().toString().equals("Category")) {
			return new ResponseDto<>(HttpStatus.SERVICE_UNAVAILABLE.value(), "fail");
		} else if(board.getTitle() == "" || board.getTitle() == null) {
			return new ResponseDto<>(HttpStatus.NO_CONTENT.value(), "fail");
		} else if(board.getCategory().toString().equals("Category")) {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "fail");
		} else {
			boardService.write(board, principal.getUser());
			return new ResponseDto<>(HttpStatus.OK.value(), "write");
		}
	}

	//글수정
	@PutMapping("/board/update/{id}")
	public ResponseDto<String> update(@PathVariable int id, @RequestBody Board board) {
		System.out.println("id : " + id + ", Board" + board);
		if(board.getTitle() == "" || board.getTitle() == null) {
			return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "fail");
		} else {
			boardService.update(id, board);
			return new ResponseDto<String>(HttpStatus.OK.value(), "update");
		}
	}

	//글삭제
	@DeleteMapping("board/delete/{id}")
	public ResponseDto<String> delete(@PathVariable int id) {
		boardService.delete(id);
		return new ResponseDto<>(HttpStatus.OK.value(), "delete");
	}
	
	@GetMapping("/auth/boardList")
	public Page<Board> tempList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> pageList = boardService.sortList(pageable);
		
		return pageList;
	}
}
