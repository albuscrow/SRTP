package com.cqu.srtp.listener;

import com.cqu.srtp.controller.BooksActivity;
import com.cqu.srtp.controller.MainActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClassifyClickListener implements OnClickListener{

	private String classify;
	private MainActivity ac;
	public OnClassifyClickListener(String classify,MainActivity ac){
		this.classify=classify;
		this.ac=ac;
	}
	@Override
	public void onClick(View v) {
		Intent intent=new Intent(ac,BooksActivity.class);
		intent.putExtra(BooksActivity.INTENT_NAME, classify);
		ac.startActivity(intent);
	}

}
