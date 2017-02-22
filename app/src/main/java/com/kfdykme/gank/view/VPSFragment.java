package com.kfdykme.gank.view;
import android.os.*;
import android.support.v4.app.*;
import android.view.*;
import android.view.View.*;
import android.webkit.*;
import android.widget.*;
import com.google.gson.*;
import com.kfdykme.gank.*;
import com.kfdykme.gank.entity.*;
import java.io.*;
import java.util.*;
import okhttp3.*;
import android.util.*;
import android.graphics.*;
import android.widget.TableRow.*;
import java.net.*;
import android.net.*;
import android.content.*;

public class VPSFragment extends Fragment 
{
	private String mTitle;
	
	public static final String BUNDLE_TITLE = "title";
	
	private static String mBaseUrl = "http://gank.io/api/";
	
	
	private List<result> results ;

	private LinearLayout linearLayout;
	
	private View view = null;

	private LinearLayout webButtonLinearLayout;
	
	private kWebView kWebView;
	
	private TextView toBackTextView;

	private String typeString ;

	private String num = "15";

	private String maxPage = "1";

	private ScrollView scrollView;
	
	private ImageView imageView = null;
	
	private Button udaButton;

	private int currentI = 0;

	public void setKWebView(kWebView kWebView)
	{
		this.kWebView = kWebView;
	}

	public kWebView getKWebView()
	{
		return kWebView;
	}
	
	public void addPage(){
		 setMaxPage( (Integer.valueOf(getMaxPage()) + 1 ) + "");
	}

	@Override
	public void onStart()
	{
		// TODO: Implement this method
		super.onStart();
		if(udaButton.getVisibility() != View.VISIBLE)
			displayFragment();
	}

	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Bundle bundle = getArguments();

		if (bundle != null){
			mTitle = bundle.getString(BUNDLE_TITLE);
			typeString = mTitle;
		}
		
		
		if (view == null || linearLayout == null){
			view = inflater.inflate(R.layout.vpsfragmentlayout,container,false);
			linearLayout = (LinearLayout)view.findViewById(R.id.vpsfragmentlayoutLinearLayout);
			
			kWebView = (kWebView)view.findViewById(R.id.vpsfragmentlayoutcom_kfdykme_gank_view_kWebView);
//			new kWebView().initWebView(kWebView);
//			toBackTextView = (TextView)view.findViewById(R.id.vpsfragmentlayoutToBackTextView);
			scrollView = (ScrollView)view.findViewById(R.id.vpsfragmentlayoutScrollView);
			udaButton =(Button)view.findViewById(R.id.vpsfragmentlayoutuDaButton);
			udaButton.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						upData();
					}
				});
			
			
//			toBackTextView.setOnClickListener(new OnClickListener(){
//
//					@Override
//					public void onClick(View view)
//					{
//
//						if(view.getVisibility() == View.VISIBLE){
//							kWebView.setVisibility(View.GONE);
//							view.setVisibility(View.GONE);
//							kWebView.loadUrl("about:blank");
//						}
//						
//					}
//				});
//				
				
		} 
	
		
		return view;
		
	}

	

	
	private void doGetApi()
	{
		
		
		final String s = getTypeString();
		
		//1 . get a okHttpClient entity
		OkHttpClient okHttpClient = new OkHttpClient();

		//2 . 
		Request.Builder builder = new Request.Builder();
		String url = mBaseUrl+"data/"+ getTypeString()+"/"+getNum() + "/" + getMaxPage();
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


					if ( results == null){
						results=db.getResults();
						if(getActivity() != null)
							getActivity().runOnUiThread(new Runnable(){

									@Override
									public void run()
									{
										//if ( udaButton.getVisibility() == View.INVISIBLE)
										displayFragment();
										Toast.makeText(getContext(), typeString +"Loading finished.",Toast.LENGTH_SHORT).show();
									}
								});

						
					} else if(!results.get(0).getDesc().equals( db.getResults().get(0).getDesc())) {
						results.addAll(db.getResults());
						if(getActivity() != null)
							getActivity().runOnUiThread(new Runnable(){

									@Override
									public void run()
									{
										//if ( udaButton.getVisibility() == View.INVISIBLE)
										addViews2Lin();
										Toast.makeText(getContext(),"Loading finished.",Toast.LENGTH_SHORT).show();
									}
								});

					} else 
						results = db.getResults();
					
					
				}
 	
				
			});

		// TODO: Implement this method
	}
	public void displayFragment()
	{
		if (getLinearLayout() != null && getResults() != null){
			//Toast.makeText(getContext(),"display",Toast.LENGTH_SHORT).show();
			udaButton.setVisibility(View.VISIBLE);
			getLinearLayout().removeAllViews();
			addViews2Lin();
			
		} 
		
	}
	
	public void upData(){
		addPage();
		Toast.makeText(getContext(),"Loading "+getMaxPage()+ " datas.",Toast.LENGTH_SHORT).show();
		doGetApi();
		//getLinearLayout().removeAllViews();
		
	}

	
	private void addViews2Lin(){

		
		
		for (; currentI < getResults().size() ; currentI++){
			
			DataText dt = new DataText(getContext());
			dt.getDateTextView().setText(getResults().get(currentI).getPublishedAt());
			dt.getDescTextView().setText(getResults().get(currentI).getDesc());
			final String url = getResults().get(currentI).getUrl();
			final WebView wv = getKWebView().getWv();
			final kWebView kw = getKWebView();
			final TextView btv = getToBackTextView();
			dt.getDescTextView().setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View view)
					{
						if(kw.getVisibility() == View.GONE){
						//	wv.setVisibility(View.VISIBLE);
						//	btv.setVisibility(View.VISIBLE);
							kw.setVisibility(View.VISIBLE);
						}
						wv.loadUrl(url);

					}


				});
			dt.getWhoTextView().setText(getResults().get(currentI).getWho());
			
			getLinearLayout().addView(dt);
		
			}
			
		TextView nTv = new TextView(getContext());
		nTv.setText("");
		nTv.setWidth(getLinearLayout().getWidth());
		nTv.setHeight(30);
		nTv.setBackgroundColor(Color.parseColor("#ff6600"));
		getLinearLayout().addView(nTv);
		

	}
		
		
		
		public static VPSFragment newInstance(String title){
			Bundle bundle = new Bundle();
			
			bundle.putString(BUNDLE_TITLE,title);
		
			
			VPSFragment fragment = new VPSFragment();
			
			fragment.setArguments(bundle);
			fragment.setTypeString(title);
	
			if(fragment.getResults() == null)
				fragment.doGetApi();
			
			fragment.displayFragment();
			
			return fragment;
		}
		
		

		
		
		
		
		
	public void setImageView(ImageView imageView)
	{
		this.imageView = imageView;
	}

	public ImageView getImageView()
	{
		return imageView;
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
