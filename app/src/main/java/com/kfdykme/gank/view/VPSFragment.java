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

public class VPSFragment extends Fragment 
{
	private String mTitle;

	private String typeString ;

	private String num = "15";

	private String maxPage = "1";

	private String maxSearchPage ="1";
	
	public static final String BUNDLE_TITLE = "title";

	private int startN = 0;


	private List<result> results ;

	private LinearLayout linearLayout;

	private View view = null;

	private kWebView kWebView;

	private TextView toBackTextView;

	private ScrollView scrollView;

	private Button udaButton;
	
	private gankPresenter presenter;



	private boolean isFirstLoaded(){
		
		if(getLinearLayout().getChildCount() < Integer.valueOf(num))
			return true;
		else 
			return false;
		
	}


	public void addPage()
	{
		setMaxPage((Integer.valueOf(getMaxPage()) + 1) + "");
	}

	@Override
	public void onStart()
	{
		super.onStart();

		getApi();
		
	}

	@Override
	public void onResume()
	{
		// TODO: Implement this method
		super.onResume();

		getApi();
		
		
	}

	@Override
	public void onAttach(Context context)
	{
		// TODO: Implement this method
		super.onAttach(context);

		getApi();
	}

	



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Bundle bundle = getArguments();

		if (bundle != null)
		{
			mTitle = bundle.getString(BUNDLE_TITLE);
			typeString = mTitle;
		}


		
		
		if (view == null || linearLayout == null)
		{
			
			view = inflater.inflate(R.layout.vpsfragmentlayout, container, false);
			linearLayout = (LinearLayout)view.findViewById(R.id.vpsfragmentlayoutLinearLayout);

			kWebView = (kWebView)view.findViewById(R.id.vpsfragmentlayoutcom_kfdykme_gank_view_kWebView);
			scrollView = (ScrollView)view.findViewById(R.id.vpsfragmentlayoutScrollView);
			udaButton = (Button)view.findViewById(R.id.vpsfragmentlayoutuDaButton);
			
			udaButton.setVisibility(View.VISIBLE);
			udaButton.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View p1)
					{
						upData();
					}
				});
			udaButton.setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View p1)
					{
						openLikedWindow();
						
						return false;
					}


				});
				
				

			presenter  = new gankPresenter(this);
			

		} 

		return view;

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onActivityCreated(savedInstanceState);

		getApi();
		
	}
	
	

	private void upData()
	{
		if(!isFirstLoaded())
			addPage();
			
		getApi();
		// TODO: Implement this method
	}

	private void getApi()
	{
		if( presenter != null
		&&presenter.getGankmodel() != null)
			results = presenter.getGankmodel().getResults(this);
	}
	
	public void updatView(){
		getActivity().runOnUiThread(new Runnable(){

				@Override
				public void run()
				{
					addViews2Lin();
				}
				
			
		});
		
	}
	
	
	private void addViews2Lin(){
		
		if(getResults() != null 
		   && (startN != (getResults().size() ))){

	   		Log.i("addVIew","addViews2Lin" + getTypeString() );
	   
			if (isFirstLoaded()){
				getLinearLayout().removeAllViews();
				
				//udaButton.setVisibility(View.VISIBLE);
			}


			for (; startN < getResults().size() ; startN++)
			{

				DataText dt = new DataText(getContext());
				dt.getDateTextView().setText(getResults().get(startN).getPublishedAt());
				dt.getDescTextView().setText(getResults().get(startN).getDesc());
				final kWebView kw = getKWebView();
				final result loadResult = getResults().get(startN);
				dt.getDescTextView().setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View view)
						{
							if (kw.getVisibility() == View.GONE)
							{
								kw.setVisibility(View.VISIBLE);
							}
							kw.setLoadResult(loadResult);
							kw.getWv().loadUrl(kw.getLoadResult().getUrl());
						}
					});
				dt.getWhoTextView().setText(getResults().get(startN).getWho());

				getLinearLayout().addView(dt);

			}

			if((startN % Integer.valueOf(num)) == 0 ){

				TextView nTv = new TextView(getContext());
				nTv.setText("");
				nTv.setWidth(LayoutParams.MATCH_PARENT);
				nTv.setHeight(30);
				nTv.setBackgroundColor(Color.parseColor("#ff6600"));
				getLinearLayout().addView(nTv);
				
				String toast;
				
				if(isFirstLoaded()){
					toast = "Load "+getTypeString()+" Finished.";
				}	else{
					toast = "Load Page"+getMaxPage()+"'s Finished.";
				}
				
				Toast.makeText(getContext(),toast,Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
	private void openLikedWindow(){
		LikedDialog lD =new LikedDialog(getContext(),this);
		lD.creatDialog().show();
		
	}


	
	

	public static VPSFragment newInstance(String title)
	{
		Bundle bundle = new Bundle();

		bundle.putString(BUNDLE_TITLE, title);


		VPSFragment fragment = new VPSFragment();

		fragment.setArguments(bundle);
		fragment.setTypeString(title);
		//fragment.results = fragment.presenter.getGankmodel().getResults(fragment);
		
		
		return fragment;
	}










	public void setTypeString(String typeString)
	{
		this.typeString = typeString;
	}

	public String getTypeString()
	{
		return typeString;
	}

	public void setNum(String num)
	{
		this.num = num;
	}

	public String getNum()
	{
		return num;
	}

	public void setMaxPage(String maxPage)
	{
		this.maxPage = maxPage;
	}

	public String getMaxPage()
	{
		return maxPage;
	}


	public void setToBackTextView(TextView toBackTextView)
	{
		this.toBackTextView = toBackTextView;
	}

	public TextView getToBackTextView()
	{
		return toBackTextView;
	}

	public void setUdaButton(Button udaButton)
	{
		this.udaButton = udaButton;
	}

	public Button getUdaButton()
	{
		return udaButton;
	}


	public void setKWebView(kWebView kWebView)
	{
		this.kWebView = kWebView;
	}

	public kWebView getKWebView()
	{
		return kWebView;
	}

	public void setView(View view)
	{
		this.view = view;
	}

	public View getView()
	{
		return view;
	}

	public void setLinearLayout(LinearLayout linearLayout)
	{
		this.linearLayout = linearLayout;
	}

	public LinearLayout getLinearLayout()
	{
		return linearLayout;
	}

	public void setResults(List<result> results)
	{
		this.results = results;
	}

	public List<result> getResults()
	{
		return results;
	}


	public void setMaxSearchPage(String maxSearchPage)
	{
		this.maxSearchPage = maxSearchPage;
	}

	public String getMaxSearchPage()
	{
		return maxSearchPage;
	}


	public void setMTitle(String mTitle)
	{
		this.mTitle = mTitle;
	}

	public String getMTitle()
	{
		return mTitle;
	}

	public void setScrollView(ScrollView scrollView)
	{
		this.scrollView = scrollView;
	}

	public ScrollView getScrollView()
	{
		return scrollView;
	}


}
