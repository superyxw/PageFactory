package com.example.view;

import com.example.pagefactory.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PageView extends RelativeLayout{
	
	private Context context;
	
	private int textColor = 0xffbbbbbb;
	
	private float textSize;
	
	private String mText;
	
	private Page page;
	
	private ReadTextView readTextView;
	
	private View tjV;
	
	private View jfV;
	
	private View psV;
	
	private View dsV;
	
	private View linkV;
	
	private TextView tjTv;
	
	private ImageView tjIv;
	
	private TextView psTv;
	
	private TextView linkTv;
	
	private int marginHeight;
	
	private int paddingTop = 100;
	
	private int paddingBottom = 100;
	
	private int screenHeight;
	
	private String linkStr = "测试链接";
	
	private int linkHeight;
	
	private int dsHeight;
	
	private int tjHeight;
	
	private String norender;
	
	public String getNorender() {
		return norender;
	}

	public void setNorender(String norender) {
		this.norender = norender;
	}

	public PageView(Context context) {
		super(context);
		
		
	}

	public PageView(Context context,float textSize,int textColor,Page page,String content)
	{
		super(context);
		
		this.context = context;
		
		this.textSize = textSize;
		
		this.textColor = textColor;
		
		this.mText = content;
		
		this.page = page;
		
		this.marginHeight = DeviceInfo.dip2px(context,10);
		
		screenHeight = DeviceInfo.getScreentHeight(context);
		
		linkHeight = DeviceInfo.dip2px(context,15);
		
		dsHeight = DeviceInfo.dip2px(context,60);
		
		tjHeight = DeviceInfo.dip2px(context,110);
		
		initView();
	}
	
	private void initView() {
		
		if(page.getPage()!=null){
			readTextView = new ReadTextView(context, textSize, textColor, page, mText);
			
			addView(readTextView);
		}
		
		//解封
		jfV = inflate(context, R.layout.jiefeng, null);
		RelativeLayout.LayoutParams rlp1=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);  
		rlp1.addRule(RelativeLayout.CENTER_HORIZONTAL);//addRule参数
		rlp1.topMargin = paddingTop;
		jfV.setLayoutParams(rlp1);
		if(page.isHasJieFen()){
			addView(jfV);
		}
		
		linkV = inflate(context,R.layout.link,null);
		RelativeLayout.LayoutParams rlp2=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);  
		
		dsV = inflate(context,R.layout.shang, null);
		RelativeLayout.LayoutParams rlp3=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);  
		rlp3.addRule(RelativeLayout.CENTER_HORIZONTAL);//addRule参数
		
		tjV = inflate(context,R.layout.tuijian, null);
		RelativeLayout.LayoutParams rlp4=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT); 
		
		if(page.getPageHeight()>0){
			
			if(page.getPageHeight()<10000){
				if(!StringUtil.isEmpty(linkStr)){
					if(screenHeight-paddingBottom-paddingTop-page.getPageHeight()-marginHeight-linkHeight>0){
						rlp2.setMargins(100, (int) (page.getPageHeight()+paddingTop+marginHeight), 100, 0);
						linkV.setLayoutParams(rlp2);
						addView(linkV);
					}else{
						norender = "link";
						return;
					}
					
					if(screenHeight-paddingBottom-paddingTop-page.getPageHeight()-marginHeight*2-linkHeight-dsHeight>0){
						rlp3.setMargins(100, (int) (page.getPageHeight()+paddingTop+marginHeight*2+linkHeight), 100, 0);
						dsV.setLayoutParams(rlp3);
						addView(dsV);
					}else{
						norender = "ds";
						return;
					}
					
					if(screenHeight-paddingBottom-paddingTop-page.getPageHeight()-marginHeight*3-linkHeight-dsHeight-tjHeight>0){
						rlp4.setMargins(100, (int) (page.getPageHeight()+paddingTop+marginHeight*3+linkHeight+dsHeight), 100, 0);
						tjV.setLayoutParams(rlp4);
						addView(tjV);
					}else{
						norender = "tj";
						return;
					}
				}
			}else{
				if(page.getNorender().equals("link")){
					rlp2.setMargins(100, paddingTop, 100, 0);
					linkV.setLayoutParams(rlp2);
					addView(linkV);
					
					rlp3.setMargins(100, (int) (paddingTop+marginHeight+linkHeight), 100, 0);
					dsV.setLayoutParams(rlp3);
					addView(dsV);
					
					rlp4.setMargins(100, (int) (paddingTop+marginHeight*2+linkHeight+dsHeight), 100, 0);
					tjV.setLayoutParams(rlp4);
					addView(tjV);
				}else if(page.getNorender().equals("ds")){
					
					rlp3.setMargins(100, (int) (paddingTop), 100, 0);
					dsV.setLayoutParams(rlp3);
					addView(dsV);
					
					rlp4.setMargins(100, (int) (paddingTop+marginHeight+dsHeight), 100, 0);
					tjV.setLayoutParams(rlp4);
					addView(tjV);
					
				}else if(page.getNorender().equals("tj")){
					rlp4.setMargins(100, (int) (paddingTop), 100, 0);
					tjV.setLayoutParams(rlp4);
					addView(tjV);
				}
			}
			
		}
		
	}
	
}
