package edu.cqu.srtp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cqu.srtp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import edu.cqu.srtp.common.MainService;
import edu.cqu.srtp.common.Task;
import edu.cqu.srtp.controller.BookDetailActivity;
import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.drm.DrmStore.Action;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class UpdateListAdapter extends BaseAdapter{

	private List<BookItem> updatedBooks;
	private Activity activity;
	private ImageLoaderConfiguration config;
	private ImageLoader imgLoader;
	private Random random;
	
	public UpdateListAdapter(Activity activity) {
		this.activity = activity;
		this.updatedBooks = new ArrayList<BookItem>();
		Task task = new Task() {
			
			@Override
			public void run() {
				updatedBooks = DataProvider.getUpdatedBook(1);
			}
			
			@Override
			public void refresh() {
				UpdateListAdapter.this.notifyDataSetChanged();
			}
		};
		
		MainService.addTask(task);
		
		random = new Random();
		
		config=ImageLoaderConfiguration.createDefault(activity);
		imgLoader=ImageLoader.getInstance();
		imgLoader.init(config);
	}
	
	@Override
	public int getCount() {
		return updatedBooks.size();
	}

	@Override
	public Object getItem(int position) {
		return updatedBooks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	class ViewHolder{
		public ImageView pic;
		public TextView name;
		public TextView auther;
		public RatingBar score;
		public TextView update;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = activity.getLayoutInflater().inflate(R.layout.update_cell, null);
			convertView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(activity, BookDetailActivity.class);
					intent.putExtra("book", updatedBooks.get(0));
					activity.startActivity(intent);
				}
			});
			viewHolder = new ViewHolder();
			viewHolder.auther = (TextView) convertView.findViewById(R.id.book_author);
			viewHolder.name = (TextView) convertView.findViewById(R.id.book_name);
			viewHolder.pic = (ImageView) convertView.findViewById(R.id.book_view);
			viewHolder.score = (RatingBar) convertView.findViewById(R.id.book_score);
			viewHolder.update = (TextView) convertView.findViewById(R.id.book_update_infor);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		BookItem book = updatedBooks.get(position);
		viewHolder.auther.setText(book.getAuthor());
		viewHolder.name.setText(book.getName());
		imgLoader.displayImage(DataProvider.IMAGE_BASE_URL + book.getPicUrl(), viewHolder.pic);
		viewHolder.score.setRating(book.getLeve());
		viewHolder.update.setText("更新至"+random.nextInt(40)+"话");
		
		
		return convertView;
	}

}
