package com.kfdykme.gank;
import com.kfdykme.gank.model.*;
import com.kfdykme.gank.view.*;
import com.kfdykme.gank.bean.*;

public class gankPresenter 
{
	private gankModel gankmodel;
	
	private VPSFragment view;
	
	public gankPresenter(VPSFragment view)
	{
		this.view = view;
		gankmodel =  new gankModel(this);
	}
	
	public void updateView(){
		view.updatView();
	}
	
	
	
	
	
	
	

	public void setGankmodel(gankModel gankmodel)
	{
		this.gankmodel = gankmodel;
	}

	public gankModel getGankmodel()
	{
		return gankmodel;
	}

	public void setView(VPSFragment view)
	{
		this.view = view;
	}

	public VPSFragment getView()
	{
		return view;
	}
}
