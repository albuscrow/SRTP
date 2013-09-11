package com.cqu.srtp.controller;

import java.util.ArrayList;
import java.util.List;

import com.cqu.srtp.R;
import com.cqu.srtp.adapter.BooksAdapter;
import com.cqu.srtp.common.MainService;
import com.cqu.srtp.common.Task;
import com.cqu.srtp.entity.BookItem;
import com.cqu.srtp.listener.MyRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BooksActivity extends Activity {
	
	public static String INTENT_NAME="bookIntent";

	PullToRefreshListView bookList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books);
		bookList = (PullToRefreshListView) this.findViewById(R.id.books_list);
		update();
	}
	
	private void update(){
		Task t=new Task() {
			List<BookItem> items=new ArrayList<BookItem>();
			@Override
			public void run() {
				for(int i=0;i<5;i++ ){
					BookItem item=new BookItem();
					item.setAuthor("author"+i);
					item.setName("name"+i);
					item.setLeve(i%11);
					item.setPicUrl("http://e.hiphotos.baidu.com/album/" +
							"w%3D2048/sign=97726d272fdda3cc0be4bf2035d13801/5d6034a85edf8db1175788dc0823dd54564e7409.jpg");
					item.setUpdateInfor("������"+i);
					items.add(item);
				}
			}
			
			@Override
			public void refresh() {
				BooksAdapter adapter=new BooksAdapter(BooksActivity.this, items);
				bookList.setOnRefreshListener(new MyRefreshListener(adapter));
				bookList.setAdapter(adapter);
			}
		};
		MainService.addTask(t);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.books, menu);
		return true;
	}

}
