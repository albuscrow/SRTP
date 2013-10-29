package edu.cqu.srtp.domains;

import java.io.Serializable;
import java.util.List;



/**
 * 精彩推荐
 * @author Jason
 *
 */
public class Recommend implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 精彩推荐批次
	 */
	private long id;
	/**
	 * 精彩推荐书籍
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
