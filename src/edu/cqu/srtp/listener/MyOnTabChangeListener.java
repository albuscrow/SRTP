package edu.cqu.srtp.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import edu.cqu.srtp.adapter.ClassifyAdapter;
import edu.cqu.srtp.adapter.HistoryAdapter;
import edu.cqu.srtp.common.MainService;
import edu.cqu.srtp.common.Task;
import edu.cqu.srtp.controller.MainActivity;
import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;
import edu.cqu.srtp.domains.ClassifyItem;
import edu.cqu.srtp.util.TitleManeger;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost.OnTabChangeListener;

public class MyOnTabChangeListener implements OnTabChangeListener {

	private static String TAG="MyOnTabChangeListener";
	private MainActivity activity;
	private boolean flag = true;
	ListView classifyListView;
	public MyOnTabChangeListener(MainActivity mainActivity) {
		this.activity = mainActivity;
		classifyListView=mainActivity.classifyListView;
	}
	@Override
	public void onTabChanged(String tabId) {
		TitleManeger.changeTitle(tabId);
		if(tabId.equals(MainActivity.TAB_TAGS[2])){
			updateClassify();
		}

		int temp = 0;
		if (tabId.equals(MainActivity.TAB_TAGS[0])) {
			temp = 0;
		}else if (tabId.equals(MainActivity.TAB_TAGS[1])) {
			temp = 1;
		}else if (tabId.equals(MainActivity.TAB_TAGS[2])) {
			temp = 2;
		}else if (tabId.equals(MainActivity.TAB_TAGS[3])) {
			temp = 3;
		}
		activity.setPosition(temp);
		activity.moveNervier(temp);
	}
	public void updateClassify(){
		activity.searchLoading.setVisibility(View.VISIBLE);
		Task t=new Task() {
			List<ClassifyItem> items=new ArrayList<ClassifyItem>();
			@Override
			public void run() {
				items = DataProvider.getClassifies();
			}

			@Override
			public void refresh() {
				activity.searchLoading.setVisibility(View.GONE);
				Log.v(TAG, "updateClassify is refreshing");
				ClassifyAdapter adapter=new ClassifyAdapter(activity, items);
				classifyListView.setAdapter(adapter);
				classifyListView.invalidate();
			}
		};
		MainService.addTask(t);
	}
	
}
