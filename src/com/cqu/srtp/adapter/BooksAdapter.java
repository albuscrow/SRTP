package com.cqu.srtp.adapter;

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
import com.cqu.srtp.entity.BookItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class BooksAdapter extends BaseAdapter {
	public static final String file="history.cfg";//与MyOnTabChangListener中相同
	
    List<BookItem> items;
    LayoutInflater inflater;
    ImageLoaderConfiguration imgconfig;
    ImageLoader imgLoader;
    public BooksAdapter(Activity activity,List<BookItem> items){
    	this.items=items;
    	inflater=activity.getLayoutInflater();
    	imgconfig=ImageLoaderConfiguration.createDefault(activity);
    	imgLoader=ImageLoader.getInstance();
    	imgLoader.init(imgconfig);
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
			imgLoader.displayImage(item.getPicUrl(), vh.pic);
			vh.name.setText(item.getName());
			vh.author.setText(item.getAuthor());
			vh.updateInfor.setText(item.getUpdateInfor());
			vh.level.setMax(10);
			vh.level.setRating(item.getLeve());
			vh.level.setStepSize(0.5f);
			vh.level.setNumStars(5);
		}
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addBookHistory(item,v.getContext()); //点击后添加至历史记录
			}
		});
		return v;
	}
	
	public void addBookHistory(BookItem item,Context context){
		Set<BookItem> items=read(context);
		boolean flag=true;
		for(BookItem book :items){
			if(book.equals(item)){
				flag=false;
				break;
			}
		}
		if(flag){
			items.add(item);
		}
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(context.openFileOutput(file, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.writeObject(items);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public void addBookHistory(List<BookItem> item,Context context){
		Set<BookItem> items=read(context);
		for(BookItem book:item){
			boolean flag=true;
			for(BookItem bookold:items){
				if(bookold.equals(book)){
					flag=false;
					break;
				}
			}
			if(flag){
				items.add(book);
			}
		}
		ObjectOutputStream out=null;
		try {
			out=new ObjectOutputStream(context.openFileOutput(file, Context.MODE_PRIVATE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			out.writeObject(items);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	@SuppressWarnings({ "unchecked", "unused" })
	public  Set<BookItem> read(Context context){
		Set<BookItem> items=null;
		ObjectInputStream in=null;
		try {
			in=new ObjectInputStream(context.openFileInput(file));
			if(in==null){
				ObjectOutputStream out=new ObjectOutputStream(context.openFileOutput(file, Context.MODE_PRIVATE));
				out.writeObject(new HashSet<BookItem>());
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(in!=null)
				items=(Set<BookItem>)in.readObject();
		} catch (OptionalDataException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(items==null){
			return new HashSet<BookItem>();
		}else{
			return items;
		}
	}
	public void addAll(List<BookItem> part){
		items.addAll(part);
	}
}
