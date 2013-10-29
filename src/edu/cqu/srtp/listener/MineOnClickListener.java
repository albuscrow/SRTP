package edu.cqu.srtp.listener;

import com.cqu.srtp.R;
import com.umeng.fb.FeedbackAgent;

import edu.cqu.srtp.controller.MainActivity;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MineOnClickListener implements OnClickListener {

	private MainActivity activity;

	public MineOnClickListener(MainActivity mainActivity) {
		this.activity = mainActivity;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_favourite:
			break;
		case R.id.colloction:
			break;
		case R.id.shop_car:
			break;
		case R.id.clear_cach:
			Toast.makeText(activity, "清理完毕", Toast.LENGTH_SHORT).show();
			break;
		case R.id.connect_us:
			FeedbackAgent agent = new FeedbackAgent(activity);
			agent.startFeedbackActivity();
			break;
		case R.id.update:
			Toast.makeText(activity, "没有更新", Toast.LENGTH_SHORT).show();
			break;

		default:
			break;
		}
	}

}
