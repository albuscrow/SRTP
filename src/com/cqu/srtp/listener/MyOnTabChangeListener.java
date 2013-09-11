package com.cqu.srtp.listener;

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

import com.cqu.srtp.adapter.ClassifyAdapter;
import com.cqu.srtp.adapter.HistoryAdapter;
import com.cqu.srtp.common.MainService;
import com.cqu.srtp.common.Task;
import com.cqu.srtp.controller.MainActivity;
import com.cqu.srtp.entity.BookItem;
import com.cqu.srtp.entity.ClassifyItem;
import com.cqu.srtp.util.TitleManeger;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost.OnTabChangeListener;

public class MyOnTabChangeListener implements OnTabChangeListener {

	private static String TAG="MyOnTabChangeListener";
	public static final String file="history.cfg";
	private MainActivity activity;
	private boolean flag = true;
	ListView classifyListView;
	ListView historyListView;
	public MyOnTabChangeListener(MainActivity mainActivity) {
		this.activity = mainActivity;
		classifyListView=mainActivity.classifyListView;
		historyListView=mainActivity.historyListView;
	}
	@Override
	public void onTabChanged(String tabId) {
		TitleManeger.changeTitle(tabId);
		if(tabId.equals(MainActivity.TAB_TAGS[2]))
			updateClassify();
		if(tabId.equals(MainActivity.TAB_TAGS[1])){
//			clean();
//			updateHistory();
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
				Log.v(TAG, "updateClassify is Running");
				for (int i = 0; i < 30; i++) {
					ClassifyItem item=new ClassifyItem();
					item.setName("����"+i);
					item.setPicUrl("http://b.hiphotos.baidu.com/album/w" +
							"%3D2048/sign=19d7d4fa4034970a4773172fa1f2d0c8/50da81cb39dbb6fde362f9f30824ab18972b372d.jpg");
					items.add(item);
				}
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

	public void updateHistory(){
//		clean();
//		Set<BookItem> items=read();
//		List<BookItem> list=new ArrayList<BookItem>(items);
//		HistoryAdapter adapter=new HistoryAdapter(activity, list,historyListView);
//		activity.historyListView.setAdapter(adapter);
//		activity.historyListView.invalidate();
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public  Set<BookItem> read(){
		Set<BookItem> items=null;
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(activity.openFileInput(file));
			if(in==null){
				ObjectOutputStream out=new ObjectOutputStream(activity.openFileOutput(file, Context.MODE_PRIVATE));
				out.writeObject(new HashSet<BookItem>());
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {

			if(in!=null)
				items=(Set<BookItem>)in.readObject();

		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if(items==null){
			return new HashSet<BookItem>();
		}else{
			return items;
		}
	}

	public void clean(){
		Set<BookItem> items=new HashSet<BookItem>();
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(activity.openFileOutput(file, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(out!=null){
				out.writeObject(items);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
