package com.hen.entity;

import com.hen.common.BaseEntity;

/** 
* @author  liaohenry
* @version 2016年7月28日 上午11:21:13
*/
public class ItemEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

    private String groupcode;

    private String code;

    private String name;

	@Override
	public String toString() {
		return "ItemEntity [id=" + id + ", groupcode=" + groupcode + ", code=" + code + ", name=" + name + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
