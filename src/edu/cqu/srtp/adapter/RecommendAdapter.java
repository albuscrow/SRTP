package edu.cqu.srtp.adapter;

import java.util.List;


import com.cqu.srtp.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import edu.cqu.srtp.domains.BookItem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendAdapter extends BaseAdapter {

	List<BookItem> books;
	Activity activity;
	ImageLoader imLoader;
	private LayoutInflater inflater;
	public RecommendAdapter(List<BookItem> books,Activity activity) {
		super();
		this.books = books;
		this.activity=activity;
		inflater=activity.getLayoutInflater();
		ImageLoaderConfiguration Ilc=ImageLoaderConfiguration.createDefault(activity);
		imLoader=ImageLoader.getInstance();
		imLoader.init(Ilc);
	}
	public RecommendAdapter(List<BookItem> books) {
		super();
		this.books = books;
	}

	@Override
	public int getCount() {
		return books.size();
	}

	@Override
	public Object getItem(int position) {
		if(position<books.size() && position>=0)
			return books.get(position);
		else
			return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder{
		public ImageView book_view_recommend;
		public TextView book_name_recommend;
		public TextView book_update_recommend;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		ViewHolder vh=null;
		if(v==null){
			vh=new ViewHolder();
//			v=inflater.inflate(R.layout.recommend_item_list, null);
//			vh.book_name_recommend=(TextView) v.findViewById(R.id.book_name_recommend);
//			vh.book_update_recommend=(TextView) v.findViewById(R.id.book_update_recommend);
//			vh.book_view_recommend=(ImageView) v.findViewById(R.id.book_view_recommend);
			v.setTag(vh);
		}else{
			vh=(ViewHolder) v.getTag();
		}
		BookItem book=books.get(position);
		if(book!=null){
			vh.book_name_recommend.setText(book.getName());
			vh.book_update_recommend.setText(book.getUpdateInfor());
			imLoader.displayImage(book.getPicUrl(), vh.book_view_recommend);
		}
		return v;
	}
}
