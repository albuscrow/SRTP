package com.cqu.srtp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.cqu.srtp.R;
import com.cqu.srtp.entity.BookItem;

import android.app.Activity;
import android.database.DataSetObserver;
import android.drm.DrmStore.Action;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

public class UpdateListAdapter extends BaseAdapter{

	private List<BookItem> updatedBooks;
	private Activity activity;
	
	public UpdateListAdapter(Activity activity) {
		this.activity = activity;
		this.updatedBooks = new ArrayList<BookItem>();
		for (int i = 0; i < 10; i++) {
			this.updatedBooks.add(new BookItem());
		}
	}
	
	@Override
	public int getCount() {
		return updatedBooks.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return updatedBooks.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return activity.getLayoutInflater().inflate(R.layout.recommend_cell, null);
	}

}
