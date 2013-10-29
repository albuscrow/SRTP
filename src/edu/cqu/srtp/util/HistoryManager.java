package edu.cqu.srtp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;


import edu.cqu.srtp.domains.BookItem;

public class HistoryManager {
	private static String path = "/history";
	private static List<BookItem> books;
	
	public static List<BookItem> getBooks() {
		return books;
	}



	public static void setBooks(List<BookItem> books) {
		HistoryManager.books = books;
	}



	public static void init(Context context){
		new File(context.getFilesDir().getPath()).mkdir();
		path = context.getFilesDir().getPath()+path;
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
				books = new ArrayList<BookItem>();
				save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else{
			ObjectInputStream oi = null;
			try {
				oi = new ObjectInputStream(new FileInputStream(file));
				books = (List<BookItem>) oi.readObject();
				oi.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void save(){
		File file = new File(path);
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(books);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void add(BookItem book) {
		books.add(book);
		save();
	}
}
