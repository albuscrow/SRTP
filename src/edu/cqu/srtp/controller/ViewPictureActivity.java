package edu.cqu.srtp.controller;

import com.cqu.srtp.R;
import com.cqu.srtp.R.layout;
import com.cqu.srtp.R.menu;

import edu.cqu.srtp.adapter.ViewPictureViewAdapter;
import edu.cqu.srtp.domains.BookItem;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class ViewPictureActivity extends Activity {

	private BookItem bookItem;
	private Integer startIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_picture);
		initData();
		intiUI();
	}

	private void intiUI() {
		ViewPictureViewAdapter adapter = new ViewPictureViewAdapter(bookItem, this);
		ViewPager viewPager = (ViewPager) findViewById(R.id.view_picture_viewpager);
		viewPager.setAdapter(adapter);
		System.out.println("ViewPictureActivity.intiUI()");
		
	}

	private void initData() {
		bookItem = (BookItem) getIntent().getExtras().get("bookItem");
		startIndex = (Integer) getIntent().getExtras().get("pageNum");
	}

}
