package com.kfdykme.gank.model;
import com.kfdykme.gank.http.gankAPI;
import java.util.*;
import com.kfdykme.gank.bean.*;
import com.kfdykme.gank.view.*;
import okhttp3.*;
import java.io.*;
import com.google.gson.*;
import android.util.*;
import android.webkit.*;
import android.widget.*;
import com.kfdykme.gank.*;
import android.content.*;

public class gankModel implements gankAPI
{

	private static String mBaseUrl = "http://gank.io/api/";
	
	private List<result> results;
	
	private List<Liked> likeds;
	
	private gankPresenter gankpresenter;
	
	public gankModel(){
		this.gankpresenter = new gankPresenter(null);
	}
	
	
	public gankModel( gankPresenter gankpresenter) {
		this.gankpresenter = gankpresenter;
	}

	@Override
	public List<result> getSearchResults(VPSFragment fragmen, String key, String category)
	{
		String baseUrl = "http://gank.io/api/search/query/";
		OkHttpClient okhttoClient = new OkHttpClient();


		Request.Builder bullder = new Request.Builder();

		//gank.io/api/search/query/listview/category/Android/count/10/page/1
		
		String url = baseUrl + key +"/category/ "+fragmen.getTypeString() +"/count/"+fragmen.getNum()+"/page/1";
				
		// TODO: Implement this method
		return null;
	}

	
	
	
	@Override
	public List<result> getResults(VPSFragment fragment)
	{
		
		//1 . get a okHttpClient entity
		OkHttpClient okHttpClient = new OkHttpClient();

		//2 . 
		Request.Builder builder = new Request.Builder();
		String url = mBaseUrl+"data/"+ fragment.getTypeString()+"/"+fragment.getNum() + "/" + fragment.getMaxPage();
		final Request request = builder.get().url(url).build();
		
		
		//3.
		Call call = okHttpClient.newCall(request);

		//4 . 
		call.enqueue(new Callback(){

				@Override
				public void onFailure(Call call, IOException p2)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException
				{		

					String res = response.body().string();

					Gson gson = new Gson();

					dataEntity db = gson.fromJson(res,dataEntity.class);
					if (results == null)
					{
						results = db.getResults();
					}
					else if (!results.get(0).getDesc().equals(db.getResults().get(0).getDesc()))
					{
						results.addAll(db.getResults());
					} else {

						results = db.getResults();
					}
					gankpresenter.updateView();
					
					
					
					

				}


			});
		// TODO: Implement this method
		return results;
	}
	
	public List<Liked> getLikeds(){
		likeds = Liked.listAll(Liked.class);
		
		return likeds;
	} 
	
	public void addLiked(WebView webview,Context context){
		Liked like = new Liked(
					webview.getTitle(),
					webview.getUrl());
					
		dealLiked(like,context);
		
		}
		

	public void addLiked(result loadResult,Context context){
		Liked like = new Liked(
			loadResult.getDesc(),
			loadResult.getUrl());

		dealLiked(like,context);

	}

	private void dealLiked(Liked like,Context context)
	{
		List<Liked> list = Liked.listAll(Liked.class);	



		if (list.size() > 0)
			for (Liked l : list)
			{
				if (l.getDesc().equals(like.getDesc())
					&& l.getUrl().equals(like.getUrl()))
				{

					Toast.makeText(context,"This page has liked", Toast.LENGTH_SHORT).show();	

					break;
				} 

				if (l.getId() == Liked.last(Liked.class).getId())
				{
					like.save();

					Toast.makeText(context, "added:\n " + like.getDesc() + "\n " + like.getUrl(), Toast.LENGTH_SHORT).show();
					Log.i("sugarDatabase", "added " + like.getDesc() + "/ " + like.getUrl());

				}

			}

		else
		{
			like.save();

			Toast.makeText(context, "added:\n " + like.getDesc() + "\n " + like.getUrl(), Toast.LENGTH_SHORT).show();
			Log.i("sugarDatabase", "added " + like.getDesc() + "/ " + like.getUrl());

		}
	}
	
	
}
