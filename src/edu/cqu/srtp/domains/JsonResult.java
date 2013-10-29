package edu.cqu.srtp.domains;

import java.util.List;

public class JsonResult {
	private Integer pageSum;  //总页数（从第1页开始计算，无数据则为0）
	public Integer getPageSum() {
		return pageSum;
	}
	public void setPageSum(Integer pageSum) {
		this.pageSum = pageSum;
	}
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public List<BookItem> getBooks() {
		return books;
	}
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}
	private Integer pageId;  //当前数据的页号
	private List<BookItem> books;
}
