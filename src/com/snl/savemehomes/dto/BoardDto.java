package com.snl.savemehomes.dto;

public class BoardDto {
	private int idx;
	private String boardWriter;
	private String boardTitle;
	private String boardDate;
	private String boardContent;
	
	public BoardDto() {
		super();
	}

	public BoardDto(String boardWriter, String boardTitle, String boardDate, String boardContent) {
		super();
		this.boardWriter = boardWriter;
		this.boardTitle = boardTitle;
		this.boardDate = boardDate;
		this.boardContent = boardContent;
	}

	public int getIdx() {
		return idx;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	
	public String toJSONString() {
		return String.format("{\"idx\" : %d, \"boardTitle\" : \"%s\", \"boardWriter\" : \"%s\", \"boardDate\" : \"%s\"}", idx, boardTitle, boardWriter, boardDate);
	}

	@Override
	public String toString() {
		return "BoardDto [idx=" + idx + ", boardWriter=" + boardWriter + ", boardTitle=" + boardTitle + ", boardDate="
				+ boardDate + ", boardContent=" + boardContent + "]";
	}
	
}
