package com.snl.savemehomes.dto;

public class StoreClassADto {
	private int idx;
	private String classCodeA;
	private String classNameA;
	
	public StoreClassADto(String classCodeA, String classNameA) {
		super();
		this.classCodeA = classCodeA;
		this.classNameA = classNameA;
	}

	public int getIdx() {
		return idx;
	}

	public String getClassCodeA() {
		return classCodeA;
	}

	public String getClassNameA() {
		return classNameA;
	}

	public void setClassCodeA(String classCodeA) {
		this.classCodeA = classCodeA;
	}

	public void setClassNameA(String classNameA) {
		this.classNameA = classNameA;
	}
	
	
}
