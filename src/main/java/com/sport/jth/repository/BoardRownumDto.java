package com.sport.jth.repository;

import com.sport.jth.model.Board;

public class BoardRownumDto {
	private Board board;
	private int rowNum;
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	
	public BoardRownumDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardRownumDto(Board board, int rowNum) {
		super();
		this.board = board;
		this.rowNum = rowNum;
	}
	@Override
	public String toString() {
		return "BoardRownumDto [board=" + board + ", rowNum=" + rowNum + "]";
	}
	
	
}
