package com.cqu.srtp.adapter;

import java.util.List;

import com.cqu.srtp.entity.BookItem;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RecommendAdapter extends BaseAdapter {

	List<BookItem> books;
	
	
	public RecommendAdapter(List<BookItem> books) {
		super();
		this.books = books;
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		if(position<books.size() && position>=0)
			return books.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

}
