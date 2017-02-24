package com.kfdykme.gank;


import android.os.*;
import android.widget.*;
import android.view.View.*;
import android.view.*;
import okhttp3.*;
import org.json.*;
import com.kfdykme.gank.bean.*;
import java.util.*;
import com.google.gson.*;
import android.support.v4.app.*;
import android.support.v4.view.*;
import com.kfdykme.gank.view.*;
import android.webkit.*;
import com.orm.*;
import android.content.*;


public class MainActivity extends FragmentActivity 
{
	
	private ViewPager mViewPager;
	
	private ViewPagerIndicator mVPIndicator;
	
	private List<String> mTitles = Arrays.asList("Android","iOS","前端","福利");
	
	private FragmentPagerAdapter mAdapter;
	
	private List<VPSFragment> mContents = new ArrayList<VPSFragment>();
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
		
		SugarContext.init(this);
		
		
		initViews();

		initDatas();
		
		mVPIndicator.setVisibleTabCount(4);
		mVPIndicator.setTabItems(mTitles);
		mViewPager.setAdapter(mAdapter);
		mVPIndicator.setViewPager(mViewPager,0);
	
//
//		for(VPSFragment v: mContents){
//			v.getUdaButton().setOnLongClickListener(new OnLongClickListener(){
//
//					@Override
//					public boolean onLongClick(View p1)
//					{
//						Intent i = new Intent(MainActivity.this,likedActivity.class);
//						startActivity(i);
//						return false;
//					}
//
//
//				});
//			
//		} 
		
	}

	@Override
	protected void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		SugarContext.terminate();
	}
	
	
	
		

	private void initDatas()
	{
		for(String title:mTitles){
			VPSFragment fragment = VPSFragment.newInstance(title);
			fragment.setMTitle(title);
			mContents.add(fragment);	
			
		}
		

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){

			@Override
			public android.support.v4.app.Fragment getItem(int p1)
			{
				
				return mContents.get(p1);
			}

			
			@Override
			public int getCount()
			{
				// TODO: Implement this method
				return mContents.size();
			}

			
		}; 
		
		// TODO: Implement this method
	}


	
	private void initViews()
	{
		mViewPager = (ViewPager)findViewById(R.id.mViewPager);
		mVPIndicator = (ViewPagerIndicator)findViewById(R.id.mViewPagerIndicator);
	
		
		// TODO: Implement this method
	}

	

	public void setMViewPager(ViewPager mViewPager)
	{
		this.mViewPager = mViewPager;
	}

	public ViewPager getMViewPager()
	{
		return mViewPager;
	}
	
		
	
}
