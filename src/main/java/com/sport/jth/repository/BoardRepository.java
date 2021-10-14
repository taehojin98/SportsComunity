package com.sport.jth.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sport.jth.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	//내 게시글 찾기
	@Query(value = "SELECT * FROM board WHERE userId = ?1 ORDER BY id DESC", nativeQuery = true)
	Page<Board> findByUserId(Pageable pagealbe, int id);

	//카테고리별 모든 게시글 찾기
	@Query(value = "SELECT * FROM board WHERE category = ?1", nativeQuery = true)
	Page<Board> findAllGroupByCategory(Pageable pageable, String category);

	//카테고리별 게시글 하나 찾기
	@Query(value = "SELECT * FROM board WHERE category = ?1", nativeQuery = true)
	Board getBoardByCategory(String category);
 
	//검색했을 때 나오는 게시글 찾기
	Page<Board> findByTitleContainingOrContentContaining(Pageable pageable, String title, String content);
	
	//카테고리별로 조회수 높은 순으로 5개 게시글 가져오기
	@Query(value = "SELECT * FROM board WHERE category = ?1 ORDER BY count DESC LIMIT 0, 5", nativeQuery = true)
	List<Board> findByBoardsWithCountDescAndGroupByCategory(String category);
	
	//다음글 찾기
	@Query(value = "SELECT * FROM board WHERE id>?1 AND category=?2 LIMIT 1", nativeQuery = true)
	Board findNextBoard(int id, String category);
	
	//이전글 찾기
	@Query(value = "SELECT * FROM board WHERE id<?1 AND category=?2 ORDER BY id desc LIMIT 1", nativeQuery = true)
	Board findPrevBoard(int id, String category);
	
	//카테고리별 게시글 수 찾기
	@Query(value = "SELECT count(*) FROM board WHERE category=?1", nativeQuery = true)
	Page<Integer> findCountBycategory(Pageable pageable, String category);
}
