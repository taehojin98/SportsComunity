package com.sport.jth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sport.jth.model.Board;
import com.sport.jth.model.User;
import com.sport.jth.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	//모든 게시글 가져오기
	@Transactional(readOnly = true)
	public List<Board> list() {
		return boardRepository.findAll();
	}
	
	//카테고리별 모든 게시글 가져오기
	@Transactional(readOnly = true)
	public Page<Board> sortListByCategory(Pageable pageable, String category) {
		return boardRepository.findAllGroupByCategory(pageable, category);
	}

	//검색 결과로 모든 게시글 가져오기
	@Transactional(readOnly = true)
	public Page<Board> searchAll(Pageable pageable, String search) {
		return boardRepository.findByTitleContainingOrContentContaining(pageable, search, search);
	}

	//상세보기 시 조회수 1 증가
	@Transactional
	public void increaseCount(int id) {
		Board boardEntity = boardRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
		});
		
		boardEntity.setCount(boardEntity.getCount()+1);
	}

	//게시글 아이디 값에 해당하는 글 가져오기
	@Transactional(readOnly = true)
	public Board getOne(int id) {
		Board boardEntity = boardRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
		});
		return boardEntity;
	}

	//유저 아이디값에 해당하는 게시글 모두 가져오기
	@Transactional(readOnly = true)
	public Page<Board> getMyList(Pageable pageable, int id) {		
		return boardRepository.findByUserId(pageable, id);
	}

	//게시글 작성하기
	@Transactional
	public void write(Board board, User user) {
		Board boardEntity = new Board();
		boardEntity.setUser(user);
		boardEntity.setTitle(board.getTitle());
		boardEntity.setCategory(board.getCategory());
		boardEntity.setContent(board.getContent());
		boardRepository.save(boardEntity);
	}

	//게시글 수정하기
	@Transactional
	public void update(int id, Board board) {
		Board boardEntity = boardRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
		});
		
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
	}

	//게시글 삭제하기
	@Transactional
	public void delete(int id) {
		boardRepository.deleteById(id);
	}

	//카테고리별 조회수 높은 순으로 5개 가져오기
	@Transactional(readOnly = true)
	public List<Board> findByBoardsWithCountDescAndGroupByCategory(String category) {
		return boardRepository.findByBoardsWithCountDescAndGroupByCategory(category);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> sortList(Pageable pageable) {
		Page<Board> pageList = boardRepository.findAll(pageable);
		return pageList;
	}
	
	//다음글 찾기
	@Transactional(readOnly = true)
	public Board nextBoard(int id, String category) {
		Board nextBoard = boardRepository.findNextBoard(id, category);
		return nextBoard;
	}
	
	//이전글 찾기
	@Transactional(readOnly = true)
	public Board prevBoard(int id, String category) {
		Board prevBoard = boardRepository.findPrevBoard(id, category);
		return prevBoard;
	}
	
	//카테고리별 게시글 수 찾기
	@Transactional(readOnly = true)
	public Page<Integer> findCountByCategory(Pageable pageable, String category) {
		return boardRepository.findCountBycategory(pageable, category);
	}
}
