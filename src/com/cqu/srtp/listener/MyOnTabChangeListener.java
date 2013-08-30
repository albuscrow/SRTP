package com.cqu.srtp.listener;

import com.cqu.srtp.controller.MainActivity;
import com.cqu.srtp.util.TitleManeger;

import android.widget.TabHost.OnTabChangeListener;

public class MyOnTabChangeListener implements OnTabChangeListener {

	private MainActivity activity;
	private boolean flag = true;

	public MyOnTabChangeListener(MainActivity mainActivity) {
		this.activity = mainActivity;
	}
	@Override
	public void onTabChanged(String tabId) {
		TitleManeger.changeTitle(tabId);
		if (flag) {
			flag = false;
		}else {
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
			activity.moveNervier(temp);
			activity.setPosition(temp);
		}
//		if (tabId.equals(MainActivity.TAB_TAGS[0])) {
//			MySensorManager.arriveViewPager();
//		}else if (tabId.equals(MainActivity.TAB_TAGS[1])) {
//			MySensorManager.leaveViewPager();
//		}else if (tabId.equals(MainActivity.TAB_TAGS[2])) {
//			MySensorManager.leaveViewPager();
//		}else if (tabId.equals(MainActivity.TAB_TAGS[3])) {
//			MySensorManager.leaveViewPager();
//		}
	}
}
