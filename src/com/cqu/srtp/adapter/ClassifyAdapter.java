package com.cqu.srtp.adapter;

import java.util.List;

import com.cqu.srtp.R;
import com.cqu.srtp.controller.MainActivity;
import com.cqu.srtp.entity.ClassifyItem;
import com.cqu.srtp.listener.OnClassifyClickListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassifyAdapter extends BaseAdapter {

	private List<ClassifyItem> items;
	private LayoutInflater inflater;
	private ImageLoaderConfiguration config;
	private ImageLoader imgLoader;
	private MainActivity ac;
	public ClassifyAdapter(MainActivity activity,List<ClassifyItem> items){
		ac=activity;
		this.items=items;
		inflater = (activity).getLayoutInflater();
		config=ImageLoaderConfiguration.createDefault(activity);
		imgLoader=ImageLoader.getInstance();
		imgLoader.init(config);
	}
	
	private class ViewHolder{
		public ImageView item1Picture;
		public TextView item1Title;
		public ImageView item2Picture;
		public TextView item2Title;
		public ImageView item3Picture;
		public TextView item3Title;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		if (items.size() != 0 && position<items.size() && position>=0) {
			return items.get(position);
		}else {
			return null;
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder viewHolder = null;
		if (v == null) {
			v = inflater.inflate(R.layout.catgory_item_list, null);
			viewHolder = new ViewHolder();
			viewHolder.item1Picture = (ImageView) v.findViewById(R.id.item_view1);
			viewHolder.item1Title = (TextView) v.findViewById(R.id.item_tiltle1);
			viewHolder.item2Picture = (ImageView) v.findViewById(R.id.item_view2);
			viewHolder.item2Title = (TextView) v.findViewById(R.id.item_tiltle2);
			viewHolder.item3Picture = (ImageView) v.findViewById(R.id.item_view3);
			viewHolder.item3Title = (TextView) v.findViewById(R.id.item_tiltle3);
			v.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder) v.getTag();
		}
		ClassifyItem item1=(ClassifyItem) getItem((position)*3);
		ClassifyItem item2=(ClassifyItem) getItem((position)*3+1);
		ClassifyItem item3=(ClassifyItem) getItem((position)*3+2);
		if(item1!=null)
		{
			imgLoader.displayImage(item1.getPicUrl(), viewHolder.item1Picture);
			viewHolder.item1Title.setText(item1.getName());
			viewHolder.item1Picture.setOnClickListener(new OnClassifyClickListener(item1.getName(), ac));
		}
		if(item2!=null)
		{
			imgLoader.displayImage(item2.getPicUrl(), viewHolder.item2Picture);
			viewHolder.item2Title.setText(item2.getName());
			viewHolder.item2Picture.setOnClickListener(new OnClassifyClickListener(item2.getName(), ac));
		}
		if(item3!=null)
		{
			imgLoader.displayImage(item3.getPicUrl(), viewHolder.item3Picture);
			viewHolder.item3Title.setText(item3.getName());
			viewHolder.item3Picture.setOnClickListener(new OnClassifyClickListener(item3.getName(), ac));
		}
		return v;
	}

}
