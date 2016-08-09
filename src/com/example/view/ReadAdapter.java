package com.example.view;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ReadAdapter extends PagerAdapter{
	
	private List<View> data;
	
	public ReadAdapter(List<View> data){
		this.data = data;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;//官方提示这样写  
	}
	
	 @Override  
     public void destroyItem(ViewGroup container, int position, Object object)   {     
         container.removeView(data.get(position));//删除页卡  
     }  

	
	 @Override  
     public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡         
          container.addView(data.get(position), 0);//添加页卡  
          return data.get(position);  
     }  

}
