package edu.cqu.srtp.domains;

import java.io.Serializable;
import java.util.List;


public class BookItem implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 书的Id唯一标识一本书
	 */
	private Long id;
	/**
	 * 书名
	 */
	private String name;
	/**
	 * 书的作者
	 */
	private String author;
	/**
	 * 书本封面的URL
	 */
	private String picUrl;
	/**
	 * 书本的推荐级别
	 */
	private int leve;
	/**
	 * 书本的分类
	 */
	private ClassifyItem classify;
	/**
	 * 书本每一个页面的url
	 */
	List<PageUrl> pagesUrls;
	/**
	 * 每一章节在pagesUrl中的下标
	 */
	List<Paragraph> paragraphs;
	/**
	 * 书本更新情况
	 */
	private String updateInfor;
	

	private Long date;
	
	public Long getDate() {
		return date;
	}
	public void setDate(Long date) {
		this.date = date;
	}
	public ClassifyItem getClassify() {
		return classify;
	}
	public void setClassify(ClassifyItem classify) {
		this.classify = classify;
	}
	public List<PageUrl> getPagesUrls() {
		return pagesUrls;
	}
	public void setPagesUrls(List<PageUrl> pagesUrls) {
		this.pagesUrls = pagesUrls;
	}
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
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
				if(this.id==temp.id){
					return true;
				}
			}
		}
		return false;
	}
}
