package edu.cqu.srtp.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import edu.cqu.srtp.domains.BookItem;
import edu.cqu.srtp.domains.ClassifyItem;
import edu.cqu.srtp.domains.JsonClassifyies;
import edu.cqu.srtp.domains.JsonResult;
import edu.cqu.srtp.util.JsonUtils;
import edu.cqu.srtp.util.NetUtils;



public class DataProvider {

	private static final String TAG = "DataProvider";
	private static final String BASE_URL = "http://172.21.115.70:8080/demo/srtp/";
	public static final String IMAGE_BASE_URL = "http://172.21.115.70:8080/demo";
	private static final String RECOMMENF_URL = BASE_URL+"recommend.htm";
	private static final String POPULAR_URL = BASE_URL+"popular.htm";
	private static final String UPDATE_URL = BASE_URL+"latestUpdate.htm";
	private static final String CLASSIFIES_URL = BASE_URL+"classifies.htm";
	private static final String CLASSIFY_BOOKS_URL = BASE_URL+"classifyBooks.htm";
	private static final String FIND_URL = BASE_URL + "find.htm";

	private static final String PAGE_INDEX = "pageId";
	private static final String CLASSIFY_ID = "id";
	private static final String KEYWORD = "keyword";
	
	
	public static List<BookItem> getRecommendBook(Integer pageIndex){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PAGE_INDEX, pageIndex.toString());
		String json = NetUtils.getGetResult(params, RECOMMENF_URL);
		JsonResult result = (JsonResult) JsonUtils.parseJsonResult(new TypeToken<JsonResult>(){}.getType(), json);
		return result.getBooks();
	}
	
	public static List<BookItem> getPopularBook(Integer pageIndex){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PAGE_INDEX, pageIndex.toString());
		String json = NetUtils.getGetResult(params, POPULAR_URL);
		JsonResult result = (JsonResult) JsonUtils.parseJsonResult(new TypeToken<JsonResult>(){}.getType(), json);
		return result.getBooks();
	}
	
	public static List<BookItem> getUpdatedBook(Integer pageIndex){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PAGE_INDEX, pageIndex.toString());
		String json = NetUtils.getGetResult(params, UPDATE_URL);
		JsonResult result = (JsonResult) JsonUtils.parseJsonResult(new TypeToken<JsonResult>(){}.getType(), json);
		return result.getBooks();
	}
	
	public static List<ClassifyItem> getClassifies(){
		String json = NetUtils.getGetResult(null, CLASSIFIES_URL);
		JsonClassifyies result = (JsonClassifyies) JsonUtils.parseJsonResult(new TypeToken<JsonClassifyies>(){}.getType(), json);
		return result.getClassifies();
	}
	
	public static List<BookItem> getBookByClassify(Long classifyId, Integer pageIndex){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CLASSIFY_ID, classifyId.toString());
		params.put(PAGE_INDEX, pageIndex.toString());
		String json = NetUtils.getGetResult(params, CLASSIFY_BOOKS_URL);
		JsonResult result = (JsonResult) JsonUtils.parseJsonResult(new TypeToken<JsonResult>(){}.getType(), json);
		return result.getBooks();
	}
	
	public static List<BookItem> getBookByKeyWord(String keyWord, Integer pageIndex){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PAGE_INDEX, pageIndex.toString());
		params.put(KEYWORD, keyWord);
		String json = NetUtils.getGetResult(params, FIND_URL);
		JsonResult result = (JsonResult) JsonUtils.parseJsonResult(new TypeToken<JsonResult>(){}.getType(), json);
		return result.getBooks();
	}

}