package com.kfdykme.gank.bean;
import com.orm.*;

public class Liked extends SugarRecord
{

	
	private String desc;

	
	private String url;
	
	
	public Liked(){
		
	}

	
	public Liked(String desc, String url){
		setDesc(desc);
		
		setUrl(url);
		
	}

	
	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}
	

	

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getDesc()
	{
		return desc;
	}

}
