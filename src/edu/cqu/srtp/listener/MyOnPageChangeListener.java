package edu.cqu.srtp.listener;



import com.cqu.srtp.R;

import edu.cqu.srtp.controller.MainActivity;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class MyOnPageChangeListener implements OnPageChangeListener {

	private MainActivity activity;
	private View slideView;
	private int space;

	public MyOnPageChangeListener(MainActivity activity) {
		this.activity = activity;
		slideView = activity.findViewById(R.id.slide_bar);
		final View basebar = activity.findViewById(R.id.base_bar);
		basebar.post(new Runnable() {
			@Override
			public void run() {
				space = basebar.getWidth()/3;
				slideView.setX((int) (basebar.getX()+basebar.getWidth()/6-slideView.getWidth()/2));
				slideView.setTag(0);
			}
		});
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		switch (arg0) {
		case 0:
			slide((Integer) slideView.getTag(),0);
			break;
		case 1:
			slide((Integer) slideView.getTag(),space);
			break;
		case 2:
			slide((Integer) slideView.getTag(),2*space);
			break;
		default:
			break;
		}
	}

	private void slide(int begain,int end) {
		Animation animation = new TranslateAnimation(begain, end, 0, 0);
		animation.setFillAfter(true);
		animation.setDuration(200);
		slideView.setTag(end);
		slideView.startAnimation(animation);
	}
	
}
