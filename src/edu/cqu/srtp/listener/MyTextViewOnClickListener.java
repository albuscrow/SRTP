package edu.cqu.srtp.listener;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

public class MyTextViewOnClickListener implements OnClickListener {
	
	private int pageId;
	private ViewPager page;
	public MyTextViewOnClickListener(int pageId, ViewPager page) {
		super();
		this.pageId = pageId;
		this.page = page;
	}
	@Override
	public void onClick(View v) {
		page.setCurrentItem(pageId);
	}

}
