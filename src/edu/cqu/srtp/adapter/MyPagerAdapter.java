package edu.cqu.srtp.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter {
	
	private List<View> mviews;
	
	public MyPagerAdapter(List<View> mviews) {
		super();
		this.mviews = mviews;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(mviews.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(mviews.get(position),0);
		return mviews.get(position);
	}

	@Override
	public int getCount() {
		return mviews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	
	
	

}
