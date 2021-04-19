package com.snl.savemehomes.dto;

public class StoreClassC {
	private int idx;
	private String classCodeB;
	private String classCodeC;
	private String classNameC;
	
	public StoreClassC(String classCodeB, String classCodeC, String classNameC) {
		super();
		this.classCodeB = classCodeB;
		this.classCodeC = classCodeC;
		this.classNameC = classNameC;
	}

	public int getIdx() {
		return idx;
	}

	public String getClassCodeB() {
		return classCodeB;
	}

	public String getClassCodeC() {
		return classCodeC;
	}

	public String getClassNameC() {
		return classNameC;
	}

	public void setClassCodeB(String classCodeB) {
		this.classCodeB = classCodeB;
	}

	public void setClassCodeC(String classCodeC) {
		this.classCodeC = classCodeC;
	}

	public void setClassNameC(String classNameC) {
		this.classNameC = classNameC;
	}
	
}
