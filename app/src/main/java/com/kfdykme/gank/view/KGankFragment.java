package com.kfdykme.gank.view;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.*;
import android.webkit.*;
import android.widget.*;
import com.google.gson.*;
import com.kfdykme.gank.*;
import com.kfdykme.gank.bean.*;
import java.io.*;
import java.util.*;
import okhttp3.*;
import android.util.*;
import android.graphics.*;
import android.widget.TableRow.*;
import java.net.*;
import android.net.*;
import android.content.*;

import java.util.zip.*;
import android.app.AlertDialog;
import android.app.Dialog;

public class KGankFragment extends Fragment implements KGankMainContract.View
{


	
	private String mTitle;

	private String mType ;

	private int mPage =  0;

	private int mSearchPage = 0;

	private List<result> mResults ;
	
	
	private ListView mListView;
	
	private SimpleAdapter mSimpleAdapter;
	
	private View view = null;

	private TextView toBackTextView;
	
	private TextView mTextViewLoading;
	
	private Button udaButton;
	
	private ProgressBar mProgressBarLoading;
	
	private KWebView mKWebView;
	

	private KGankMainPresenter mPresenter;
	
	

	public static final String BUNDLE_TITLE = "title";

	



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Bundle bundle = getArguments();

		if (bundle != null)
		{
			mTitle = bundle.getString(BUNDLE_TITLE);
			mType = mTitle;
		}

		if (view == null)
		{
			
			view = inflater.inflate(R.layout.kgankfragmen, container, false);
			
			mKWebView = (KWebView)view.findViewById(R.id.kgankfragmen_KWebView);
			udaButton = (Button)view.findViewById(R.id.vpsfragmentlayoutuDaButton);
			mListView = (ListView)view.findViewById(R.id.kgankfragmen_ListView);
			mProgressBarLoading = (ProgressBar)view.findViewById(R.id.kgankfragmenProgressBar);
			mTextViewLoading = (TextView)view.findViewById(R.id.kgankfragmenLoadingTextView);
			
			
		} 
		
		return view;

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		

		mPresenter = new KGankMainPresenter(this);

		mPresenter.loadArticle(getMType(), ++mPage);		
	}

	
	

	@Override
	public void addViews(final List<result> list_result)
	{

		
		getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					
					initViews(isInited());
					
					
				}
			});
		// TODO: Implement this method
	}

	

	@Override
	public void initViews(Boolean isInited)
	{
		if(isInited){
			mTextViewLoading.setVisibility(View.GONE);
			mProgressBarLoading.setVisibility(View.GONE);
		}
	}

	@Override
	public Boolean isInited()
	{
	
		return (mProgressBarLoading.getVisibility() == View.VISIBLE);
	}
	

	@Override
	public void setPresenter(KGankMainPresenter presenter)
	{
		this.mPresenter = presenter;
	}
	
	
	

	public static KGankFragment newInstance(String title)
	{
		Bundle bundle = new Bundle();

		bundle.putString(BUNDLE_TITLE, title);


		KGankFragment fragment = new KGankFragment();

		fragment.setArguments(bundle);
		
		return fragment;
	}



	public void setMType(String mType)
	{
		this.mType = mType;
	}

	public String getMType()
	{
		return mType;
	}
	
	

	public void setMPage(int mPage)
	{
		this.mPage = mPage;
	}

	public int getMPage()
	{
		return mPage;
	}

	public void setMSearchPage(int mSearchPage)
	{
		this.mSearchPage = mSearchPage;
	}

	public int getMSearchPage()
	{
		return mSearchPage;
	}
	
	


	public void setTitle(String mTitle)
	{
		this.mTitle = mTitle;
	}

	public String getTitle()
	{
		return mTitle;
	}




}
