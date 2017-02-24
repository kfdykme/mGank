package com.kfdykme.gank;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import com.kfdykme.gank.bean.*;
import com.kfdykme.gank.view.*;
import android.view.View.*;
import android.content.*;
import android.support.annotation.*;

public class likedActivity extends Activity
{
	private LinearLayout verticalLinaerLayout;
	
	private Button bButton;
	
	private List<Liked> likes;

	public static String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.liked);


		initiData();
     
		initiView();
	}
	
	

	private void initiView()
	{
		
		verticalLinaerLayout = (LinearLayout) findViewById(R.id.likedVerticalLinearLayout);
		bButton = (Button) findViewById(R.id.likedButton); 
		bButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					finish();
					// TODO: Implement this method
				}
			});
		for (int i = 0; i < likes.size() ; i++)
		{

			DataText dt = new DataText(this);
			dt.getDateTextView().setText("");
			dt.getDescTextView().setText(likes.get(i).getDesc());
			final String url = likes.get(i).getUrl();

			final int j = i;
			dt.getDescTextView().setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View view)
					{
						urltoMain(likes.get(j).getUrl());
					}

				});
				dt.getDescTextView().setOnLongClickListener(new OnLongClickListener(){

					@Override
					public boolean onLongClick(View p1)
					{
						List<Liked> l = Liked.listAll(Liked.class);
						for(Liked i : l){
							if(i.getDesc().equals(((TextView)p1).getText().toString())){
								i.delete();
							}
						}
						
						
						// TODO: Implement this method
						return false;
					}
				});
			dt.getWhoTextView().setText("");
			verticalLinaerLayout.addView(dt);
		}	
		// TODO: Implement this method
	}

	private void initiData()
	{
		
		likes = Liked.listAll(Liked.class);
		
			
		// TODO: Implement this method
	}
	

	private void urltoMain(String url)
	{
		
		finish();
		// TODO: Implement this method
	}
}
