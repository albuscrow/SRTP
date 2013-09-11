package com.cqu.strp.view;

import java.util.ArrayList;
import java.util.List;

import com.cqu.srtp.R;
import com.cqu.srtp.entity.BookItem;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MyListview {

	private Activity activity;
	private ScrollView root;
	public ScrollView getRoot() {
		return root;
	}

	public void setRoot(ScrollView root) {
		this.root = root;
	}

	private List<BookItem> books;

	public MyListview(Activity activity) {
		this.activity = activity;
		this.init();
		return;
	}

	private void rePaint(){

		LinearLayout left = (LinearLayout) root.findViewById(R.id.leftListview);
		LinearLayout right = (LinearLayout) root.findViewById(R.id.rightListview);

		for (int i = 0; i < books.size(); i++) {
			LinearLayout cell = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.recommend_cell, null);
			((TextView)cell.findViewById(R.id.book_name)).setText("test");
			((TextView)cell.findViewById(R.id.book_update)).setText("19hua");
			((ImageView)cell.findViewById(R.id.picture)).setImageResource(R.drawable.ic_launcher);
			if(i%2 == 0){
				left.addView(cell);
			}else{
				right.addView(cell);
			}
			LayoutParams layoutParams = (LayoutParams) cell.getLayoutParams();
			layoutParams.setMargins(0, 20, 0, 0);
		}

		return;
	};

	public void addBooks(List<BookItem> books){
		this.books = books;
		rePaint();
		return;
	}

	public void refresh(){
		//get from internet
		return;
	};

	private void init(){
		this.root = (ScrollView) activity.getLayoutInflater().inflate(R.layout.recommend, null);
		root.setOnTouchListener(new OnTouchListener() {

			boolean flag1 = true;
			boolean flag2 = true;


			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					int scrollY = v.getScrollY();
					int height = v.getHeight();
					int scrollViewMeasuredHeight = root.getChildAt(0).getMeasuredHeight();
					
					if (flag1 && flag2) {
						if (scrollY == 0) {
							Toast.makeText(activity, "begin", Toast.LENGTH_SHORT).show();
							flag1 = false;
							flag2 = false;
							new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									flag1 = true;
								}
							}).start();
						}
						
						if ((scrollY + height) == scrollViewMeasuredHeight) {
							Toast.makeText(activity, "end", Toast.LENGTH_SHORT).show();
							flag1 = false;
							flag2 = false;
							new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									flag1 = true;
								}
							}).start();
						}
					}else if(flag1 && scrollY != 0 && (scrollY + height) != scrollViewMeasuredHeight){
						flag2 = true;
					}
					
					break;

				default:
					break;
				}
				return false;
			}
		});

		//init books
		books = new ArrayList<BookItem>();
		for (int i = 0; i < 21; i++) {
			books.add(new BookItem());
		}

		rePaint();
		return;
	}
}
