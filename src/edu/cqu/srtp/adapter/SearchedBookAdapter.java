package edu.cqu.srtp.adapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cqu.srtp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import edu.cqu.srtp.controller.BookDetailActivity;
import edu.cqu.srtp.data.DataProvider;
import edu.cqu.srtp.domains.BookItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class SearchedBookAdapter extends BaseAdapter {
	public static final String file="history.cfg";//��MyOnTabChangListener����ͬ
	
    List<BookItem> items;
    LayoutInflater inflater;
    ImageLoaderConfiguration imgconfig;
    ImageLoader imgLoader;

	private Activity activity;
    public SearchedBookAdapter(Activity activity,List<BookItem> items){
    	this.items=items;
    	inflater=activity.getLayoutInflater();
    	imgconfig=ImageLoaderConfiguration.createDefault(activity);
    	imgLoader=ImageLoader.getInstance();
    	imgLoader.init(imgconfig);
    	this.activity = activity;
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
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		ViewHolder vh=null;
		if(v==null){
			vh=new ViewHolder();
			v=inflater.inflate(R.layout.book_item_list, null);
			vh.pic=(ImageView) v.findViewById(R.id.book_view);
			vh.name=(TextView) v.findViewById(R.id.book_name);
			vh.author=(TextView) v.findViewById(R.id.book_author);
			vh.updateInfor=(TextView) v.findViewById(R.id.book_update_infor);
			vh.level=(RatingBar) v.findViewById(R.id.ratingBar1);
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
			vh.level.setRating(item.getLeve());
		}
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(activity, BookDetailActivity.class);
				intent.putExtra("book",item);
				activity.startActivity(intent);
			}
		});
		return v;
	}
	public void addAll(List<BookItem> part){
		items.addAll(part);
	}
}
