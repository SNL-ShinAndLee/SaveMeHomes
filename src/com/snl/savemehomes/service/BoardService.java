package com.snl.savemehomes.service;

import java.util.List;


import com.snl.savemehomes.dto.BoardDto;
import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.exception.NoPermissionsException;

public interface BoardService {
	public boolean writeBoard(BoardDto boardDto);
	public List<BoardDto> readBoardList(int pageNum);
	public boolean modifyBoard(BoardDto boardDto) throws NoPermissionsException;
	public boolean deleteBoard(int idx, UserDto userDto) throws NoPermissionsException;
	public BoardDto readBoardByIdx(int idx);
	public int readBoardPageCount(); 
//	public UserDto readWriterById(int idx); 
}
