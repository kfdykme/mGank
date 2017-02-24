package com.kfdykme.gank;


import android.os.*;
import android.view.*;
import android.support.v4.app.*;
import com.orm.*;
import android.app.*;
import android.content.*;
import com.kfdykme.gank.view.*;
import java.util.*;

public class LoadingActivity extends Activity
{
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loading);
		
		SugarContext.init(this);
		

		SystemClock.sleep(3000);
		
		
		
		
		final Intent i = new Intent(LoadingActivity.this,MainActivity.class);
		startActivity(i);
	}

	
	
	
}
