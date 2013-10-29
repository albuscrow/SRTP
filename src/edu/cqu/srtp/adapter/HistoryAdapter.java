package edu.cqu.srtp.adapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.cqu.srtp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import edu.cqu.srtp.controller.BookDetailActivity;
import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;
import edu.cqu.srtp.util.HistoryManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

public class HistoryAdapter extends BaseAdapter {
	
	Activity  activity;
    List<BookItem> items;
    LayoutInflater inflater;
    ImageLoaderConfiguration imgconfig;
    ImageLoader imgLoader;
    ListView list;
    public HistoryAdapter(Activity activity){
    	this.items = HistoryManager.getBooks();
    	this.activity=activity;
    	inflater=activity.getLayoutInflater();
    	imgconfig=ImageLoaderConfiguration.createDefault(activity);
    	imgLoader=ImageLoader.getInstance();
    	imgLoader.init(imgconfig);
    	HistoryManager.init(activity);
    	items = HistoryManager.getBooks();
    }
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		if(position>=0 && position<items.size())
			return items.get(position);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder{
		public ImageView pic;
		public TextView name;
		public TextView author;
		public TextView updateInfor;
		public RatingBar level;
		public Button deleteButton;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		ViewHolder vh=null;
		if(v==null){
			vh=new ViewHolder();
			v=inflater.inflate(R.layout.history_item, null);
			vh.pic=(ImageView) v.findViewById(R.id.book_view);
			vh.name=(TextView) v.findViewById(R.id.book_name);
			vh.author=(TextView) v.findViewById(R.id.book_author);
			vh.updateInfor=(TextView) v.findViewById(R.id.book_update_infor);
			vh.level=(RatingBar) v.findViewById(R.id.ratingBar1);
			vh.deleteButton=(Button) v.findViewById(R.id.deleteButton);
			v.setTag(vh);
		}else
		{
			vh=(ViewHolder) v.getTag();
		}
		final BookItem item=(BookItem) getItem(position);
		if(item!=null){
			imgLoader.displayImage(DataProvider.IMAGE_BASE_URL+item.getPicUrl(), vh.pic);
			vh.name.setText(item.getName());
			vh.author.setText(item.getAuthor());
			vh.updateInfor.setText(item.getUpdateInfor());
			vh.level.setMax(10);
			vh.level.setRating(item.getLeve());
			vh.level.setStepSize(0.5f);
			vh.level.setNumStars(5);
			vh.deleteButton.setTag(item);
			vh.deleteButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					for(BookItem book:items){
						if(book.getId().equals(item.getId())){
							items.remove(book);
							HistoryManager.save();
							break;
						}
					}
					HistoryAdapter.this.notifyDataSetChanged();
				}
			});
		}
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, BookDetailActivity.class);
				intent.putExtra("book", items.get(0));
				activity.startActivity(intent);
			}
		});
		return v;
	}

	
	public void addAll(List<BookItem> part){
		items.addAll(part);
	}

}
