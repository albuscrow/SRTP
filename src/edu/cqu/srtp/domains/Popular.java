package edu.cqu.srtp.domains;

import java.io.Serializable;
import java.util.List;

/**
 * 热门连载
 * @author Jason
 *
 */
public class Popular implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 热门连载批次
	 */
	private long id;
	/**
	 * 热门连载书籍
	 */
	private List<BookItem> books;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<BookItem> getBooks() {
		return books;
	}
	public void setBooks(List<BookItem> books) {
		this.books = books;
	}
}
