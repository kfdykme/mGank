package com.kfdykme.gank.http;
import java.util.List;
import com.kfdykme.gank.bean.result;
import com.kfdykme.gank.view.VPSFragment;

public interface gankAPI
{
	//获取文章数据
	public List<result> getResults( VPSFragment fragment);
	
	//获取图片
	
	//获取随机图片
	
	//获取搜索数据
	//gank.io/api/search/query/listview/category/Android/count/10/page/1
	public List<result> getSearchResults(VPSFragment fragmen, String key, String category);
}
