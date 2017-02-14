package com.hen.corejava.test;

/**
 * @author liaohenry
 * @version 2017年1月19日 上午10:28:32
 */
public enum Gender {
	Male("M", "男性"), Female("F", "女性");

	private String val;
	private String desc;

	private Gender(String val, String desc) {
		this.val = val;
		this.desc = desc;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
