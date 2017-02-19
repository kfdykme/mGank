package com.kfdykme.gank.view;
import android.widget.*;
import android.content.*;
import android.util.*;
import android.view.*;
import com.kfdykme.gank.*;

public class DataText extends LinearLayout
{
	private TextView descTextView;
	private TextView whoTextView;
	private TextView dateTextView;
	
	public DataText(Context context){
		this(context,null);
	}
	
	public DataText(Context context, AttributeSet attrs){
		super(context,attrs);
		LayoutInflater.from(context).inflate(R.layout.dataitemlayout, this);
		
		descTextView = (TextView)findViewById(R.id.dataitemlayoutDescTextView);
		dateTextView = (TextView)findViewById(R.id.dataitemlayoutDateTextView);
		whoTextView = (TextView)findViewById(R.id.dataitemlayoutWhoTextView);
		
	}
	
	

	public void setDescTextView(TextView descTextView)
	{
		this.descTextView = descTextView;
	}

	public TextView getDescTextView()
	{
		return descTextView;
	}

	public void setWhoTextView(TextView whoTextView)
	{
		this.whoTextView = whoTextView;
	}

	public TextView getWhoTextView()
	{
		return whoTextView;
	}

	public void setDateTextView(TextView dateTextView)
	{
		this.dateTextView = dateTextView;
	}

	public TextView getDateTextView()
	{
		return dateTextView;
	}}
