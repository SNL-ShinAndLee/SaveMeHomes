package com.snl.savemehomes.dao;

import java.util.List;

import com.snl.savemehomes.dto.BoardDto;
import com.snl.savemehomes.dto.UserDto;

public interface BoardDao {
	public boolean createBoard(BoardDto boardDto);
	public List<BoardDto> readBoard(int pageNum);
	public boolean updateBoard(BoardDto boardDto);
	public boolean deleteBoard(int idx);
	public BoardDto readBoardByIdx(int idx);
	public int readBoardPageCount();
	public UserDto readWriterByIdx(int idx);
}
