package com.example.view;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


public class StringUtil {

	private final static long dayLevelValue = 24 * 60 * 60 * 1000;
	private final static long hourLevelValue = 60 * 60 * 1000;
	private final static long minuteLevelValue = 60 * 1000;
	private final static long secondLevelValue = 1000;

	/**
	 * 判断字符创的内容是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (null == str || "".equals(str.trim()) || "null".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据打赏名来获得背景图
	 * 
	 * @param treasure
	 * @return
	 */

	/**
	 * 根据打赏名来获得量词
	 * 
	 * @param treasure
	 * @return
	 */
	public static String selectTreasureLC(String treasure) {
		if (treasure.equals("浴液")) {
			return "瓶";
		} else if (treasure.equals("精油")) {
			return "瓶";
		} else if (treasure.equals("香波")) {
			return "瓶";
		} else if (treasure.equals("肥皂")) {
			return "块";
		} else if (treasure.equals("大力丸")) {
			return "颗";
		}
		return "";
	}

	/**
	 * 数字转成万
	 * 
	 * @param num
	 * @return
	 */
	public static String numToW(String num) {
		if (!StringUtil.isEmpty(num) && num.length() > 4) {
			String xianshi = num.substring(0, num.length() - 4);
			int dianhou = Integer.parseInt(num.substring(num.length() - 4,
					num.length() - 3));
			if (dianhou > 4) {
				return xianshi + "." + dianhou + "万";
			} else {
				return xianshi + "万";
			}
		} else {
			return num;
		}
	}

	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	public static String formartGiftDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH时mm分");
		return sdf.format(new Date(Long.parseLong(date) * 1000L));
	}

	public static String getRecord(String type) {
		switch (Integer.parseInt(type)) {
		case 1:
			return "买豆子";
		case 2:
			return "买挖掘机";
		case 3:
			return "登录奖励";
		case 4:
			return "兑换礼物";
		case 5:
			return "送豆子";
		case 6:
			return "得豆子";
		case 7:
			return "买会员";
		case 8:
			return "刷新任务";
		case 9:
			return "任务奖励";
		case 10:
			return "系统手动处理";
		default:
			return "未知记录";
		}
	}

	/**
	 * 获取还是使用豆子
	 * 
	 * @param type
	 * @return
	 */
	public static String getoruse(String type) {
		switch (Integer.parseInt(type)) {
		case 1:
			return "+";
		case 2:
			return "-";
		case 3:
			return "+";
		case 4:
			return "-";
		case 5:
			return "-";
		case 6:
			return "+";
		case 7:
			return "-";
		case 8:
			return "-";
		case 9:
			return "+";
		case 10:
			return "";
		default:
			return "";
		}
	}

	public static String getDifference(long period) {// 根据毫秒差计算时间差
		String result = null;

		int day = getDay(period);
		int hour = getHour(period - day * dayLevelValue);
		int minute = getMinute(period - day * dayLevelValue - hour
				* hourLevelValue);
		int second = getSecond(period - day * dayLevelValue - hour
				* hourLevelValue - minute * minuteLevelValue);

		result = (day > 0 ? day + "天" : "") + (hour > 0 ? hour + "时" : "")
				+ (minute > 0 ? minute + "分" : "") + second + "秒";
		return result;
	}

	public static int getDay(long period) {
		return (int) (period / dayLevelValue);
	}

	public static int getHour(long period) {
		return (int) (period / hourLevelValue);
	}

	public static int getMinute(long period) {
		return (int) (period / minuteLevelValue);
	}

	public static int getSecond(long period) {
		return (int) (period / secondLevelValue);
	}

	public static String tranReviewTime(String date) {

		SimpleDateFormat sdfY = new SimpleDateFormat("MM-dd");
		SimpleDateFormat sdfH = new SimpleDateFormat("HH:mm");
		Long dateLong = Long.parseLong(date) * 1000;
		Long nowLong = System.currentTimeMillis();

		String dtime = sdfY.format(new Date(dateLong));

		String htime = sdfH.format(new Date(dateLong));

		if ((nowLong - dateLong) < minuteLevelValue) {
			return "刚刚";
		}

		else if ((nowLong - dateLong) < hourLevelValue) {
			return (nowLong - dateLong) / minuteLevelValue + "分钟前";
		}

		else if ((nowLong - dateLong) < dayLevelValue) {
			return (nowLong - dateLong) / hourLevelValue + "小时前";
		}

		else if ((nowLong - dateLong) < dayLevelValue * 3) {
			int d = (int) ((nowLong - dateLong) / dayLevelValue);

			if (d == 1) {
				return "昨天" + htime;
			} else {
				return "前天" + htime;
			}
		}

		return dtime;
	}

	/**
	 * 获得追书等级背景
	 * 
	 * @param level
	 * @return
	 */

	public static String getUnreadCount(int count) {
		if (count > 0) {
			return count + "";
		} else {
			return "";
		}
	}



	public static String getSignLevelName(int level) {

		if (level >= 100) {
			return "钻粉";
		} else if (level >= 70) {
			return "金粉";
		} else if (level >= 45) {
			return "银粉";
		} else if (level >= 25) {
			return "铜粉";
		} else if (level >= 10) {
			return "铁粉";
		}

		return "";
	}

	/**
	 * * 去除特殊字符或将所有中文标号替换为英文标号
	 * 
	 * @param str
	 * @return
	 */
	public static String stringFilter(String str) {
		str = str.replaceAll("【", "[").replaceAll("】", "]")
				.replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
		String regEx = "[『』]"; // 清除掉特殊字符
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	
	/**
	 * 文字转表情
	 * @param context
	 * @param tv
	 * @param source
	 * @return
	 */
	
	
	public static long getTimeStamp(String mdate){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Date date = null;
		try {
			date = format.parse(mdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	    return date.getTime();
	}
	
	
	public static String getChapterTime(long date){
		SimpleDateFormat format =  new SimpleDateFormat("MM-dd HH:mm");  
		String d = format.format(date);  
	    return d;
	}
	
	/**
	 * 获取当前年月
	 * @return
	 */
	 public static String findYearMonth()
	{
	        /**
	         * 声明一个int变量year
	         */
	        int year;
	        /**
	         * 声明一个int变量month
	         */
	        int month;
	        /**
	         * 声明一个字符串变量date
	         */
	        String date;
	        /**
	         * 实例化一个对象calendar
	         */
	        Calendar calendar = Calendar.getInstance();
	        /**
	         * 获取年份
	         */
	        year = calendar.get(Calendar.YEAR);
	        /**
	         * 获取月份
	         */
	        month = calendar.get(Calendar.MONTH) + 1;
	        /**
	         * 拼接年份和月份
	         */
	        date = year + "-" + ( month<10 ? "0" + month : month);
	        /**
	         * 返回当前年月
	         */
	        return date;
	    }

}
