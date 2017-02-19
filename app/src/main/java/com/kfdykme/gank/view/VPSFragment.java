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

public class VPSFragment extends Fragment 
{
	private String mTitle;
	
	public static final String BUNDLE_TITLE = "title";
	
	private static String mBaseUrl = "http://gank.io/api/";
	
	
	private List<result> results ;

	private LinearLayout linearLayout;
	
	private View view = null;

	private WebView webView;
	
	private TextView toBackTextView;

	private String typeString ;

	private String num = "15";

	private String maxPage = "0";

	private ScrollView scrollView;
	
	private ImageView imageView = null;
	
	private Button udaButton;

	public void setImageView(ImageView imageView)
	{
		this.imageView = imageView;
	}

	public ImageView getImageView()
	{
		return imageView;
	}

	
	public void addPage(){
		 setMaxPage( (Integer.valueOf(getMaxPage()) + 1 ) + "");
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
			//TextView loadingTv = (TextView)view.findViewById(R.id.vp 
			webView = (WebView)view.findViewById(R.id.vpsfragmentlayoutWebView);
			toBackTextView = (TextView)view.findViewById(R.id.vpsfragmentlayoutToBackTextView);
			scrollView = (ScrollView)view.findViewById(R.id.vpsfragmentlayoutScrollView);
			udaButton =(Button)view.findViewById(R.id.vpsfragmentlayoutuDaButton);
			udaButton.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						upData();
						// TODO: Implement this method
					}
				});
			
			
			toBackTextView.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View view)
					{

						if(view.getVisibility() == View.VISIBLE){
							webView.setVisibility(View.GONE);
							view.setVisibility(View.GONE);
							webView.loadUrl("about:blank");
						}
						// TODO: Implement this method
					}
				});
				

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
				//	Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException
				{		
				
					String res = response.body().string();

					Gson gson = new Gson();

					dataEntity db = gson.fromJson(res,dataEntity.class);

					if ( results == null)
						results=db.getResults();
					else if(results.size() != db.getResults().size())
						results.addAll(db.getResults());
					
					if(getActivity() != null){
						getActivity().runOnUiThread(new Runnable(){

								@Override
								public void run()
								{
									Toast.makeText(getContext(),"run",Toast.LENGTH_SHORT).show();
									displayFragment();
									// TODO: Implement this method
								}
							});
					}
 	
//					Log.i("getApi",Lr.get(0).getType());
					
				}

				

			});

		// TODO: Implement this method
	}
	public void displayFragment()
	{
		
		if (getLinearLayout() != null && getResults() != null){
			udaButton.setVisibility(View.VISIBLE);
			getLinearLayout().removeAllViews();
			addViews2Lin();
			
		} 
		
	}
	
	public void upData(){
		Toast.makeText(getContext(),"Updata",Toast.LENGTH_LONG).show();
		addPage();
		doGetApi();
		upDataFragment();
	}

	private void upDataFragment()
	{
		addViews2Lin();
		// TODO: Implement this method
	}
	
	private void addViews2Lin(){

		TextView nTv = new TextView(getContext());
		nTv.setText("");
		nTv.setWidth(getLinearLayout().getWidth());
		nTv.setHeight(30);
		nTv.setBackgroundColor(Color.parseColor("#ff6600"));
		getLinearLayout().addView(nTv);
		
		
		for (int i = 0; i < getResults().size() ; i++){
			
			DataText dt = new DataText(getContext());
			dt.getDateTextView().setText(getResults().get(i).getPublishedAt());
			dt.getDescTextView().setText(getResults().get(i).getDesc());
			final String url = getResults().get(i).getUrl();
			final WebView wv = getWebView();
			final TextView btv = getToBackTextView();
			dt.getDescTextView().setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View view)
					{
						if(wv.getVisibility() == View.GONE){
							wv.setVisibility(View.VISIBLE);
							btv.setVisibility(View.VISIBLE);
							
						}
						wv.loadUrl(url);

					}


				});
			dt.getWhoTextView().setText(getResults().get(i).getWho());
			
			getLinearLayout().addView(dt);
		
			}
			

	}
		
		
		
		public static VPSFragment newInstance(String title){
			Bundle bundle = new Bundle();
			
			bundle.putString(BUNDLE_TITLE,title);
		
			
			VPSFragment fragment = new VPSFragment();
			
			fragment.setArguments(bundle);
			fragment.setTypeString(title);
			if(fragment.getResults() == null);
			fragment.doGetApi();
			fragment.displayFragment();
			
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

	public void setWebView(WebView webView)
	{
		this.webView = webView;
	}

	public WebView getWebView()
	{
		return webView;
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
