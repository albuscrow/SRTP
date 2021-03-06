package edu.cqu.srtp.listener;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import edu.cqu.srtp.adapter.SearchedBookAdapter;
import edu.cqu.srtp.common.MainService;
import edu.cqu.srtp.common.Task;
import edu.cqu.srtp.domains.BookItem;

public class MyRefreshListener implements OnRefreshListener<ListView>{
	SearchedBookAdapter adapter;
	public MyRefreshListener(SearchedBookAdapter adapter){
		this.adapter=adapter;
	}
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		update(refreshView);
	}
    private void update(final PullToRefreshBase<ListView> refreshView){
    	Task t=new Task() {
			List<BookItem> items=new ArrayList<BookItem>();
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
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
				adapter.addAll(items);
				refreshView.invalidate();
			}
		};
		MainService.addTask(t);
    }
}
