package com.example.view;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.DisplayMetrics;

/**
 * @ClassName: DeviceInfo 
 * @Description: 获取设备信息
 * @author yuanxw
 * @date 2015-4-8 下午2:21:18 
 * @copyright DPX 
 */


public class DeviceInfo {

	/**
	 * 判断网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetAvailable(Context context) {
		NetworkInfo networkInfo = getNetWorkInfo(context);

		if (networkInfo != null) {

			return networkInfo.isAvailable();
		}
		return false;
	}
	
	public static NetworkInfo getNetWorkInfo(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

		return activeNetInfo;
	}
	
	/**
	 * @Description：获取屏幕的宽度
	 * @param context
	 * @return
	 */
	public static int getScreentWidth(Context context) {

		return getDisplayMetrics(context).widthPixels;
	}
	
	/**
	 * @Description：获取屏幕的宽度
	 * @param context
	 * @return
	 */
	public static int getScreentHeight(Context context) {

		return getDisplayMetrics(context).heightPixels;
	}
	
	/**
	 * 获取 DisplayMetrics
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics metric = new DisplayMetrics();

		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);

		return metric;
	}
	
	/**
	 * 获取屏幕比例
	 * @param context
	 * @return
	 */
	public static float getDisplayRatio(Context context){

		float ratioWidth = (float)getScreentWidth(context)/ 720;  
		float ratioHeight = (float)getScreentHeight(context)/ 1280; 
		float Ratio = Math.min(ratioWidth, ratioHeight);
		return Ratio;
	}
	
	/**
	 * 获取状态栏高度
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context){
		 int result = 0;
		  int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		  if (resourceId > 0) {
		      result = context.getResources().getDimensionPixelSize(resourceId);
		  }
		  return result;
	}
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
    
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    }  
    
    
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
    
    public static int sp2px(Context context, float spValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (spValue * fontScale + 0.5f); 
    } 
    
    /**
	 * 获取版本versionName
	 * 
	 * @param context
	 * @param isRemovePoint
	 * @return
	 */
	public static String getVersionName(Context context, boolean isRemovePoint) {

		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(
					"com.dpx.kujiang", 0);
			if (isRemovePoint) {
				String versionName[] = info.versionName.split("\\.");
				StringBuilder builder = new StringBuilder();
				for (String versonname : versionName) {
					builder.append(versonname);
				}
				return builder.toString();
			} else {
				return info.versionName;
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}

	}
	
	/**
	 * 获取手机ip值
	 * @param context
	 * @return
	 */
	public static String getlocalip(Context context){  
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);    
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();    
        int ipAddress = wifiInfo.getIpAddress();   
        if(ipAddress==0)return null;  
        return ((ipAddress & 0xff)+"."+(ipAddress>>8 & 0xff)+"."  
                +(ipAddress>>16 & 0xff)+"."+(ipAddress>>24 & 0xff));  
    }  
	
	
	/**
	 * 获取屏幕亮度
	 * @param activity
	 * @return
	 */
	public static int getScreenBrightness(Activity activity) {
	    int value = 0;
	    ContentResolver cr = activity.getContentResolver();
	    try {
	        value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
	    } catch (SettingNotFoundException e) {
	        
	    }
	    return value;
	}
	
	/**
	  * 有声有震动
	  * @param audio
	  */
	 public static void ringAndVibrate(AudioManager audio) {
	  audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);        
	  audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,AudioManager.VIBRATE_SETTING_ON);       
	  audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,AudioManager.VIBRATE_SETTING_ON);
	 }
	 
	 /**
	  * 无声有震动
	  * @param audio
	  */
	 public static void vibrate(AudioManager audio) {
	  audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);        
	  audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_RINGER,AudioManager.VIBRATE_SETTING_ON);       
	  audio.setVibrateSetting(AudioManager.VIBRATE_TYPE_NOTIFICATION,AudioManager.VIBRATE_SETTING_ON);
	 }
	 
	 
	 /**
	  * 获取栈顶Activity
	  * @param context
	  * @return
	  */
	 public static String getTopActivityNameAndProcessName(Context context){
			String processName=null;
			String topActivityName=null;
			 ActivityManager activityManager =
			(ActivityManager)(context.getSystemService(android.content.Context.ACTIVITY_SERVICE )) ;
		     List<RunningTaskInfo> runningTaskInfos = activityManager.getRunningTasks(1) ;
		     if(runningTaskInfos != null){
		    	 ComponentName f=runningTaskInfos.get(0).baseActivity;
		    	 String topActivityClassName=f.getClassName();
		    	 String temp[]=topActivityClassName.split("\\.");
		    	 //栈顶Activity的名称
		    	 topActivityName=temp[temp.length-1];
		    	 int index=topActivityClassName.lastIndexOf(".");
		    	//栈顶Activity所属进程的名称
		    	 processName=topActivityClassName.substring(0, index);
		    	 System.out.println("---->topActivityName="+topActivityName+",processName="+processName);
		    	 
		     }
		     return topActivityName;
		}
	 
}
