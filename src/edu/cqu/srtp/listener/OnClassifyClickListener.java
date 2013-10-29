package edu.cqu.srtp.listener;

import edu.cqu.srtp.controller.SearchedBooksActivity;
import edu.cqu.srtp.controller.MainActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClassifyClickListener implements OnClickListener{

	private Long classifyId;
	private MainActivity ac;
	public OnClassifyClickListener(Long long1,MainActivity ac){
		this.classifyId=long1;
		this.ac=ac;
	}
	@Override
	public void onClick(View v) {
		Intent intent=new Intent(ac,SearchedBooksActivity.class);
		intent.putExtra("classifyId", classifyId);
		intent.putExtra("type", "c");
		ac.startActivity(intent);
	}

}
