package com.snl.savemehomes.service;

import java.util.List;

import com.snl.savemehomes.dao.BoardDao;
import com.snl.savemehomes.dao.BoardDaoImpl;
import com.snl.savemehomes.dto.BoardDto;
import com.snl.savemehomes.dto.UserDto;
import com.snl.savemehomes.exception.NoPermissionsException;

public class BoardServiceImpl implements BoardService {
	
	static BoardService boardService;
	private final BoardDao boardDao = BoardDaoImpl.getInstance();
	private BoardServiceImpl() {}

	public static BoardService getInstance() {
		if(boardService == null)
			boardService = new BoardServiceImpl();
		return boardService;
	}

	@Override
	public boolean writeBoard(BoardDto boardDto){
		return boardDao.createBoard(boardDto);
	}

	@Override
	public List<BoardDto> readBoardList(int pageNum) {
		return boardDao.readBoard(pageNum);
	}

	@Override
	public boolean modifyBoard(BoardDto boardDto) throws NoPermissionsException {
		//수정 권한 확인
		if(!boardDao.readWriterByIdx(boardDto.getIdx()).getUserId().equals(boardDto.getBoardWriter())) {
			throw new NoPermissionsException("게시글 수정 권한이 없습니다");
		}
		
		return boardDao.updateBoard(boardDto);
	}

	@Override
	public boolean deleteBoard(int idx, UserDto userDto) throws NoPermissionsException{
		
		if(!boardDao.readWriterByIdx(idx).getUserId().equals(userDto.getUserId())) {
			throw new NoPermissionsException("게시글  삭제 권한이 없습니다");
		}
		return boardDao.deleteBoard(idx);
	}

	@Override
	public BoardDto readBoardByIdx(int idx) {
		return boardDao.readBoardByIdx(idx);
	}

	@Override
	public int readBoardPageCount() {
		return boardDao.readBoardPageCount();
	}

}
