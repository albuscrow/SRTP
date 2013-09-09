package com.cqu.srtp.entity;

import java.io.Serializable;

public class BookItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String author;
	private String picUrl;
	private int leve;
	private String updateInfor;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getLeve() {
		return leve;
	}
	public void setLeve(int leve) {
		this.leve = leve;
	}
	public String getUpdateInfor() {
		return updateInfor;
	}
	public void setUpdateInfor(String updateInfor) {
		this.updateInfor = updateInfor;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(obj instanceof BookItem){
			if(obj==this)
				return true;
			else{
				BookItem temp=(BookItem) obj;
				if((this.author!=null && this.author.equals(temp.author) || this.author==temp.author)&&
					(this.name!=null &&this.name.equals(temp.name) || this.name==temp.name)&& 
					(this.picUrl!=null &&this.picUrl.equals(temp.picUrl) || this.picUrl==temp.picUrl) &&
					(this.updateInfor!=null &&this.updateInfor.equals(temp.updateInfor) || this.updateInfor==temp.updateInfor)&&
					this.leve==temp.leve ){
					return true;
				}
					
			}
		}
		return false;
	}
	
}
