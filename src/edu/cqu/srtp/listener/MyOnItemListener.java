package edu.cqu.srtp.listener;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MyOnItemListener implements OnItemClickListener{

	private Activity activity;
	
	public MyOnItemListener() {
		super();
	}

	public MyOnItemListener(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		
	}

}
