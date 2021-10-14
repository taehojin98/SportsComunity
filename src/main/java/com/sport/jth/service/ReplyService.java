package com.sport.jth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.jth.model.Reply;
import com.sport.jth.model.User;
import com.sport.jth.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private BoardService boardService;
	
	//댓글등록
	@Transactional
	public void write(User user, int boardId, Reply reply) {
	
		reply.setUser(user);
		reply.setBoard(boardService.getOne(boardId));
		
		replyRepository.save(reply);
	}
	
	//댓글삭제
	@Transactional
	public void delete(int id) {
		replyRepository.deleteById(id);
	}
}
