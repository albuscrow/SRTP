package edu.cqu.srtp.controller;

import java.util.ArrayList;
import java.util.List;

import com.cqu.srtp.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import edu.cqu.srtp.adapter.SearchedBookAdapter;
import edu.cqu.srtp.common.MainService;
import edu.cqu.srtp.common.Task;
import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;
import edu.cqu.srtp.listener.MyRefreshListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class SearchedBooksActivity extends Activity {

	public static String INTENT_NAME="bookIntent";

	ListView bookList;

	private String keyString;

	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books);
		bookList = (ListView) this.findViewById(R.id.books_list);
		keyString =(String) getIntent().getExtras().get(INTENT_NAME);
		type = (String) getIntent().getExtras().getString("type");
		update();
	}

	private void update(){
		Task t=new Task() {
			List<BookItem> items=new ArrayList<BookItem>();
			@Override
			public void run() {
				if (type != null && type.equals("c")) {
					Long int1 = getIntent().getExtras().getLong("classifyId");
					items = DataProvider.getBookByClassify(int1, 1);
				}else{
					items = DataProvider.getBookByKeyWord(keyString, 1);
				}
			}

			@Override
			public void refresh() {
				SearchedBookAdapter adapter=new SearchedBookAdapter(SearchedBooksActivity.this, items);
				bookList.setAdapter(adapter);
			}
		};
		MainService.addTask(t);
	}
}
