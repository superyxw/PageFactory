package com.example.view;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class ReadTextViewHelper {

	private int textColor = 0xffbbbbbb;
	
	private float textSize;
	
	private Context context = null;
	
	private Paint paint = new Paint();
	
	private int screenHeight = 1920,sceenWidth = 1080;
	
	private int paddingTop=100,paddingBottom=100,paddingLeft=100,paddingRight=100;
	
	private int jieHeight;
	
	private boolean isHasJIe = true;
	
	private float lineSpace = 30;
	
	private String content;
	
	private List<ReadTextView> pages = null;
	
	List<View> pageView = new ArrayList<View>();
	
	public ReadTextViewHelper(Context context,float textSize,String content){
		this.context = context;
		sceenWidth = DeviceInfo.getScreentWidth(context)-paddingLeft-paddingRight;
		screenHeight = DeviceInfo.getScreentHeight(context);
		this.content = content;
		this.textSize = textSize;
		jieHeight = DeviceInfo.dip2px(context,80);
		paint.setTextSize(textSize);
	}
	
	public ArrayList<Line> getLines(String mTextStr) {

		if (mTextStr == null || mTextStr.isEmpty() == true) {

			return null;

		}

		int tempStart = 0;

		int tempLineWidth = 0;

		float tempLineCount = 0;

		ArrayList<Line> tempLineArray = new ArrayList<Line>();

		// 对字符串进行一次循环

		for (int i = 0; i < mTextStr.length(); i++) {

			char ch = mTextStr.charAt(i);

			String str = String.valueOf(ch);

			float strWidth = 0;

			if (str != null && str.isEmpty() == false) {

				strWidth = BaikeConstant.getWidthofString(str, paint);

			}

			/*
			 * 
			 * 如果是换行符，将这一行的信息存入列表中
			 */

			if (ch == '\n' && tempStart != i) {

				tempLineCount++;

				addLine(tempStart, i, tempLineCount, 0, tempLineArray);
				
				tempLineCount = (float) (tempLineCount+0.5);

				if (i == (mTextStr.length() - 1)) {

					break;

				} else {

					tempStart = i + 1;

					tempLineWidth = 0;

				}

				continue;

			} else {

				tempLineWidth += Math.ceil(strWidth);

				if (tempLineWidth >= sceenWidth ) {

					tempLineCount++;

					/*
					 * 
					 * 对正常绘制时的“下一行第一个字符”进行判断，如果是“成对出现标点”的左侧半个，
					 * 
					 * 对上一行的字符间距进行拉伸，或者不处理
					 */

					if (BaikeConstant.isLeftPunctuation(ch) == true) {
//
//						Log.i(TAG, "i: " + i + "");
//
//						Log.i(TAG,
//
//						"the char is the left half of the punctuation");
//
//						Log.i(TAG, "str: " + str + " ");

						/*
						 * 
						 * if the char is the left half of the punctuation. Go
						 * 
						 * into the next line of the current character
						 */

						i--;

						float tempWordSpaceOffset = (float) (tempLineWidth

						- Math.ceil(strWidth) - sceenWidth)

						/ (float) (i - tempStart);

						addLine(tempStart, i, tempLineCount,

						tempWordSpaceOffset, tempLineArray);

					} else if (BaikeConstant.isRightPunctuation(ch) == true) {

						/*
						 * 
						 * 对正常绘制时的“下一行第一个字符”进行判断，如果是“成对出现标点”的右侧半个
						 */

//						Log.i(TAG,
//
//						"the char is the right half of the punctuation");
//
//						Log.i(TAG, "str: " + str + " ");

						if (i == (mTextStr.length() - 1)) {

							addLine(tempStart, i, tempLineCount, 0,

							tempLineArray);

							break;

						} else {

							char nextChar = mTextStr.charAt(i + 1);

							if ((BaikeConstant.isHalfPunctuation(nextChar) == true || BaikeConstant

							.isPunctuation(nextChar) == true)

									&& BaikeConstant

									.isLeftPunctuation(nextChar) == false) {

								/*
								 * 
								 * 对正常绘制时的“下一行第一个字符”进行判断，如果是“成对出现标点”的右侧半个
								 * 
								 * 
								 * 
								 * 并且，“再下一个字符”是“英文标点”、“中文标点”、“右侧标点”
								 * 
								 * 
								 * 
								 * 处理：讲这两个标点都放在上一行进行绘制
								 */

								String nextStr = String.valueOf(nextChar);

								float nextStrWidth = 0;

								if (nextStr != null

								&& nextStr.isEmpty() == false) {

									nextStrWidth = BaikeConstant

									.getWidthofString(nextStr, paint);

								}

								i++;

								float tempWordSpaceOffset = (float) (tempLineWidth

										+ Math.ceil(nextStrWidth) - sceenWidth)

										/ (float) (i - tempStart);

								addLine(tempStart, i, tempLineCount,

								tempWordSpaceOffset, tempLineArray);

							} else {

								/*
								 * 
								 * 对正常绘制时的“下一行第一个字符”进行判断，如果是“成对出现标点”的右侧半个
								 * 
								 * 
								 * 
								 * 并且，“再下一个字符”是“左侧标点”、非标点的字符
								 * 
								 * 
								 * 
								 * 处理：只将右侧标点放在上一行进行绘制
								 */

								float tempWordSpaceOffset = (float) (tempLineWidth - sceenWidth)

										/ (float) (i - tempStart);

								addLine(tempStart, i, tempLineCount,

								tempWordSpaceOffset, tempLineArray);

							}

						}

					} else {

						/*
						 * 
						 * 如果下一行的第一个字符是“单个出现的标点”和“非标点字符”
						 */

						/*
						 * 
						 * if the char is not the left And Right half of the
						 * 
						 * punctuation.
						 */

						if (BaikeConstant.isHalfPunctuation(ch) == true

						|| BaikeConstant.isPunctuation(ch) == true) {

							/*
							 * 
							 * 如果下一行的第一个字符是“单个出现的标点”
							 * 
							 * 放在上一行进行绘制
							 */

							/*
							 * 
							 * If the current character is a punctuation mark,
							 * 
							 * on the end of the Bank
							 */

							float tempWordSpaceOffset = (float) (tempLineWidth - sceenWidth)

									/ (float) (i - tempStart);

							addLine(tempStart, i, tempLineCount,

							tempWordSpaceOffset, tempLineArray);

						} else {

							/*
							 * 
							 * 如果下一行的第一个字符是“非标点”
							 */

							/*
							 * 
							 * If the current character is not a punctuation
							 */

							if (i >= 1) {

								char preChar = mTextStr.charAt(i - 1);

								if (BaikeConstant.isLeftPunctuation(preChar) == true) {

									/*
									 * 
									 * 如果下一行的第一个字符是“非标点”
									 * 
									 * 
									 * 
									 * 上一个字符(即结尾的字符)，是左侧标点
									 * 
									 * 
									 * 
									 * 处理：两个字符全都放在下一行进行绘制
									 */

									String preStr = String.valueOf(preChar);

									float preStrWidth = 0;

									if (preStr != null

									&& preStr.isEmpty() == false) {

										preStrWidth = BaikeConstant

										.getWidthofString(preStr,

										paint);

									}

//									Log.i(TAG,
//
//									"the char is the left half of the punctuation");
//
//									Log.i(TAG, "preChar: " + preChar + " ");

									i = i - 2;

									float tempWordSpaceOffset = (float) (tempLineWidth

											- Math.ceil(strWidth)

											- Math.ceil(preStrWidth) - sceenWidth)

											/ (float) (i - tempStart);

									addLine(tempStart, i, tempLineCount,

									tempWordSpaceOffset, tempLineArray);

								} else {

									/*
									 * 
									 * 如果下一行的第一个字符是“非标点”
									 * 
									 * 
									 * 
									 * 上一个字符(即结尾的字符)，是“非左侧标点”
									 * 
									 * 
									 * 
									 * 处理：下一行的第一个字符放在下一行(即，不处理)
									 */

									i--;

									float tempWordSpaceOffset = (float) (tempLineWidth

											- Math.ceil(strWidth) - sceenWidth)

											/ (float) (i - tempStart);

									addLine(tempStart, i, tempLineCount,

									tempWordSpaceOffset, tempLineArray);

								}

							}

						}

					}

					if (i == (mTextStr.length() - 1)) {

						break;

					} else {

						tempStart = i + 1;

						tempLineWidth = 0;

					}
					
					if(mTextStr.charAt(i+1)==  '\n'){
						tempLineCount = tempLineCount +0.5f;
					}

					continue;

				} else {

					if (i == (mTextStr.length() - 1)) {

						tempLineCount++;

						addLine(tempStart, i, tempLineCount, 0,

						tempLineArray);

						break;

					}

					continue;

				}

			}

		}

		return tempLineArray;


	}
	
	public void cutList(){
		List<Line> lines = getLines(content);
		List<Page> pages = new ArrayList<Page>();
		List<Line> page = new ArrayList<Line>();
		float lastLineCount = 0;
		for(int i=0;i<lines.size();i++){
			float tempCount = lines.get(i).getLineCount();
			float drawHeight = ((tempCount-lastLineCount)*textSize+(tempCount-lastLineCount-1)*lineSpace);
			if(drawHeight<=screenHeight-paddingTop-paddingBottom-(isHasJIe?jieHeight:0)&&i!=lines.size()-1){
				page.add(lines.get(i));
			}else{
				if(i==lines.size()-1){
					page.add(lines.get(i));
					Page tempPage = new Page();
					tempPage.setPage(page);
					tempPage.setHasJieFen(isHasJIe);
					tempPage.setPageHeight(drawHeight+(isHasJIe?jieHeight:0));
					tempPage.setLastLineCount(lastLineCount);
					pages.add(tempPage);
				}else{
					Page tempPage = new Page();
					tempPage.setPage(page);
					tempPage.setLastLineCount(lastLineCount);
					tempPage.setHasJieFen(isHasJIe);
					pages.add(tempPage);
					page = new ArrayList<Line>();
					page.add(lines.get(i));
					lastLineCount = lines.get(i-1).getLineCount();
				}
				isHasJIe = false;
			}
		}
		
		for(Page paget :pages){
			PageView p = new PageView(context, textSize, textColor, paget, content);
			pageView.add(p);
			if(!StringUtil.isEmpty(p.getNorender())){
				Page tempPage = new Page();
				tempPage.setPageHeight(10000);
				tempPage.setNorender(p.getNorender());
				PageView ap = new PageView(context, textSize, textColor, tempPage, content);
				pageView.add(ap);
			}
		}
		
	}

	public List<View> getAllViews(){
		cutList();
		return pageView;
		
	}
	public void addLine(int start, int end, float lineCount,

	float wordSpaceOffset, ArrayList<Line> lineList) {

		if (lineList != null) {

			Line Line = new Line();

			Line.setLineCount(lineCount);

			Line.setStart(start);

			Line.setEnd(end);

			Line.setWordSpaceOffset(wordSpaceOffset);

			lineList.add(Line);

		}
	}
	
	public void addPage(ArrayList<Line> lineList,ArrayList<ArrayList<Line>> pagelist){
		if (lineList != null&&pagelist!=null) {
			pagelist.add(lineList);
		}
	}
	
}
