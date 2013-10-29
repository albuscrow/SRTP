package edu.cqu.srtp.util;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.TextView;

import com.cqu.srtp.R;

import edu.cqu.srtp.controller.MainActivity;

public class TitleManeger {
	private static String homeTitle;
	private static String topicTitle;
	private static String topicTitleOld;
	private static String searchTitle;
	private static String mytaobaoTitle;
	private static String mytaobaoTitleOld;
	final static int HOME_MODE = 0;
	final static int TOPIC_MODE = 1;
	final static int SEARCH_MODE = 2;
	final static int TAOBAO_MODE = 3;
	private static int mode = HOME_MODE;
	private static TextView titleTextView;
	private static String searchTitleOld;
	
	public static void init(Activity activity) {
		TitleManeger.titleTextView = (TextView) activity.findViewById(R.id.header_title_text_view);
		Resources resources = activity.getResources();
		homeTitle = resources.getString(R.string.home);
		topicTitle = resources.getString(R.string.histoy);
		searchTitle = resources.getString(R.string.search);
		mytaobaoTitle = resources.getString(R.string.personal);
		TitleManeger.titleTextView.setText(homeTitle);
	}

	private static void storeTitle(String title) {
		switch (mode) {
		case HOME_MODE:
			homeTitle = title;
			break;
		case TOPIC_MODE:
			topicTitle = title;
			break;
		case SEARCH_MODE:
			searchTitle = title;
			break;
		case TAOBAO_MODE:
			mytaobaoTitle = title;
			break;

		default:
			break;
		}
	}

	public static void changeTitle(String tabId) {
		storeTitle(titleTextView.getText().toString());
		if (tabId.equals(MainActivity.TAB_TAGS[0])) {
			mode = HOME_MODE;
			titleTextView.setText(homeTitle);
		}else if (tabId.equals(MainActivity.TAB_TAGS[1])) {
			mode = TOPIC_MODE;
			titleTextView.setText(topicTitle);
		}else if (tabId.equals(MainActivity.TAB_TAGS[2])) {
			mode = SEARCH_MODE;
			titleTextView.setText(searchTitle);
		}else if (tabId.equals(MainActivity.TAB_TAGS[3])) {
			mode = TAOBAO_MODE;
			titleTextView.setText(mytaobaoTitle);
		}
	}
	
	static public void setTitleHome(String title){
		titleTextView.setText(title);
		homeTitle = title;
	}
	
	static public void setTitleTopic(String title){
		titleTextView.setText(title);
		topicTitleOld = topicTitle;
		topicTitle = title;
	}

	public static void restoreTitleTopic() {
		topicTitle = topicTitleOld;
		titleTextView.setText(topicTitle);
	}
	
	static public void setTitleSearch(String title){
		titleTextView.setText(title);
		searchTitleOld = searchTitle;
		searchTitle = title;
	}

	public static void restoreTitleSearch() {
		searchTitle = searchTitleOld;
		titleTextView.setText(searchTitle);
	}

	public static void setTitleMine(String title) {
		titleTextView.setText(title);
		mytaobaoTitleOld = mytaobaoTitle;
		mytaobaoTitle = title;
	}
	
	public static void restoreTitleMine() {
		mytaobaoTitle = mytaobaoTitleOld;
		titleTextView.setText(mytaobaoTitle);
	}
}
