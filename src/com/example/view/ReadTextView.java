package com.example.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class ReadTextView extends View{
	
	private Paint paint = new Paint();
	
	private int textColor = 0xffbbbbbb;
	
	private float textSize;
	
	private int lineSpace = 30;
	
	private List<Line> tempLineArray;
	
	private String mText;
	
	private float lastLineCount;
	
	private int paddingTop,paddingBottom,paddingLeft,paddingRight;
	
	private int screenHeight,screenWidth;
	
	private Page page;
	
	private Context context;
	
	private boolean isHasJiefen;
	
	private int jieHeight;
	
	public ReadTextView(Context context) {
		super(context);
	}
	
	public ReadTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ReadTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public ReadTextView(Context context,float textSize,int textColor,Page page,String content) {
		super(context);
		this.textSize = textSize;
		this.textColor = textColor;
		this.tempLineArray = page.getPage();
		lastLineCount = page.getLastLineCount();
		this.page = page;
		this.context = context;
		mText = content;
		screenHeight = DeviceInfo.getScreentHeight(context);
		screenWidth = DeviceInfo.getScreentWidth(context);
		jieHeight = DeviceInfo.dip2px(context,80);
		init();
	}

	private void init(){
		paint.setTextSize(textSize);
		paint.setColor(textColor);
		paint.setAntiAlias(true);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		drawText(tempLineArray, mText, canvas);
	}
	
	public void drawText(List<Line> tempLineArray, String mTextStr,

			Canvas canvas) {

				if (tempLineArray == null || canvas == null || mTextStr == null

				|| mTextStr.equals("") == true) {

					return;

				}

				
				for (int lineNum = 0; lineNum < tempLineArray.size(); lineNum++) {

					Line linePar = tempLineArray.get(lineNum);

					int start = linePar.getStart();

					int end = linePar.getEnd();

					float width = linePar.getWordSpaceOffset();

					float lineCount = linePar.getLineCount()-lastLineCount;
					

					if (start > end || end > mTextStr.length() - 1) {

						continue;

					}

					float lineWidth = 0;

					for (int strNum = start; strNum <= end; strNum++) {

						char ch = mTextStr.charAt(strNum);

						String str = String.valueOf(ch);

						if (str == null || str.equals("") == true) {

							continue;

						}

						if (ch == '\n') {

							str = "";

						}

						if (strNum > end) {

							break;

						}

						if (strNum >= start && strNum <= end && lineCount >= 1) {

							canvas.drawText(str, lineWidth+100, lineCount

							* textSize+(lineCount-1)*lineSpace+100+(page.isHasJieFen()?jieHeight:0), paint);

							lineWidth += BaikeConstant.getWidthofString(str, paint);

							lineWidth = lineWidth - width;

						}

					}

				}

			}

	
}
