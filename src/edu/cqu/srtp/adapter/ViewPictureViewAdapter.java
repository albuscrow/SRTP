package edu.cqu.srtp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.cqu.srtp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;
import edu.cqu.srtp.domains.PageUrl;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ViewPictureViewAdapter extends PagerAdapter{

	private BookItem bookItem;
	private Activity activity;
	private ImageLoaderConfiguration ilc;
	private ImageLoader il;

	public ViewPictureViewAdapter(BookItem bookItem, Activity activity) {
		this.bookItem = bookItem;
		this.activity = activity;
		init();
	}


	@Override
	public int getCount() {
		return bookItem.getPagesUrls().size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView imageView = new ImageView(activity);
		il.displayImage(DataProvider.IMAGE_BASE_URL + bookItem.getPagesUrls().get(position).getUrl(), imageView);
		imageView.setScaleType(ScaleType.FIT_XY);
		ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
		layoutParams.width = 100;
		layoutParams.height = 100;
		imageView.setLayoutParams(layoutParams);
		container.addView(imageView);
		return imageView;
	}

	private void init() {
		ilc = ImageLoaderConfiguration.createDefault(activity);
		il = ImageLoader.getInstance();
		il.init(ilc);
//		bookItem.setPagesUrls(new ArrayList<PageUrl>());
//		for (int i = 0; i < 10; i++) {
//			bookItem.getPagesUrls().add(new PageUrl());
//		}
	}

}
