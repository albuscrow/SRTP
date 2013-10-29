package edu.cqu.srtp.controller;

import java.util.ArrayList;
import java.util.List;

import com.cqu.srtp.R;
import com.cqu.srtp.R.layout;
import com.cqu.srtp.R.menu;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;
import edu.cqu.srtp.domains.Paragraph;
import edu.cqu.srtp.util.HistoryManager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BookDetailActivity extends Activity {
	
	BookItem bookItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
		HistoryManager.init(this);
		initData();
		initUI();
	}
	
	private void initData() {
		bookItem = (BookItem) getIntent().getExtras().get("book");
	}

	private void initUI() {
		TableLayout chapters = (TableLayout) findViewById(R.id.chapters);
		for (int i = 0; i < bookItem.getParagraphs().size(); i += 4) {
			TableRow tableRow = new TableRow(this);
			for (int j = 0; j < 4 && i+j < bookItem.getParagraphs().size(); j++) {
				Button button = new Button(this);
				final int index = i+j;
				
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(BookDetailActivity.this, ViewPictureActivity.class);
						intent.putExtra("bookItem", bookItem);
						intent.putExtra("pageNum", bookItem.getParagraphs().get(bookItem.getParagraphs().size()-index-1).getBeginIndex());
						HistoryManager.add(bookItem);
						BookDetailActivity.this.startActivity(intent);
					}
				});
				button.setText(String.valueOf(bookItem.getParagraphs().size()-index));
				tableRow.addView(button);
			}
			chapters.addView(tableRow);
		}
		
		ImageLoaderConfiguration ilc = ImageLoaderConfiguration.createDefault(this);
		ImageLoader il = ImageLoader.getInstance();
		il.init(ilc);
		
		il.displayImage(DataProvider.IMAGE_BASE_URL+bookItem.getPicUrl(), (ImageView) findViewById(R.id.book_picture));
		
		((TextView)findViewById(R.id.book_name)).setText(bookItem.getName());
		((TextView)findViewById(R.id.book_author)).setText("作者："+bookItem.getAuthor());
		((TextView)findViewById(R.id.book_score)).setText("评分："+bookItem.getLeve());
		((TextView)findViewById(R.id.desc)).setText("    这是一本很好看的漫画，大家都很喜欢看，非常好看！");
		((Button)findViewById(R.id.continue_read)).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						Intent intent = new Intent(BookDetailActivity.this, ViewPictureActivity.class);
						intent.putExtra("bookItem", bookItem);
						intent.putExtra("pageNum", 0);
						HistoryManager.add(bookItem);
						BookDetailActivity.this.startActivity(intent);
					}
				});
	}
	

}
