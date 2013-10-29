package edu.cqu.srtp.domains;

import java.io.Serializable;

public class ClassifyItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String infor;
	private String picUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof ClassifyItem && ((ClassifyItem)obj).id==this.id){
			return true;
		}
		return false;
	}
	
	
}
