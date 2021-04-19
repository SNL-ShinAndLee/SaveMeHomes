package com.snl.savemehomes.dto;

public class StoreClassB {
	private int idx;
	private String classCodeA;
	private String classCodeB;
	private String classNameB;
	
	public StoreClassB(String classCodeA, String classCodeB, String classNameB) {
		super();
		this.classCodeA = classCodeA;
		this.classCodeB = classCodeB;
		this.classNameB = classNameB;
	}

	public int getIdx() {
		return idx;
	}

	public String getClassCodeA() {
		return classCodeA;
	}

	public String getClassCodeB() {
		return classCodeB;
	}

	public String getClassNameB() {
		return classNameB;
	}

	public void setClassCodeA(String classCodeA) {
		this.classCodeA = classCodeA;
	}

	public void setClassCodeB(String classCodeB) {
		this.classCodeB = classCodeB;
	}

	public void setClassNameB(String classNameB) {
		this.classNameB = classNameB;
	}

}
