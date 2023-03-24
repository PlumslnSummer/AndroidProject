package com.zonesion.util.js;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.qr_codescan.MipcaActivityCapture;

import com.zonesion.web.EnergyManagement.MainActivity;
import com.zonesion.web.EnergyManagement.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
/**
 * 此代码为原创
 */
public class JSRestInterface {
	private static final String TAG  = "JSRestInterface";
	
	WebView mWebView;
	Handler mHandler;
	MainActivity mActivity; 
	
	SharedPreferences mSp;
	
	public JSRestInterface(MainActivity a, WebView v, Handler h) {
		mWebView = v;
		mHandler = h;
		mActivity = a; 
		mSp = mActivity.getSharedPreferences("test", Activity.MODE_PRIVATE);
		initPlayArarm();
	}
	@JavascriptInterface
	public void test(final String a) {
		mHandler.post(new Runnable() {       
            public void run() {       
            	System.out.println(a);
                mWebView.loadUrl("javascript:alert(\""+a+"\");");       
            }        
        });    
	} 
	
	
	@JavascriptInterface
	public void getProperty(String name, String cb) {
		 String v = mSp.getString(name, "");
		 callJS(cb, "\'"+v+"\'");
	}
	@JavascriptInterface
	public void setProperty(String name, String v) {
		if (v == null) v = "";
		mSp.edit().putString(name, v).commit();
	}
	
	static final int SCAN_CODE_RESULT = 1001;
	
	String mScanCb = null;
	@JavascriptInterface
	public void requestScanQR(String cb) {
		mScanCb = cb;
		 Intent intent = new Intent(mActivity, MipcaActivityCapture.class);
		 mActivity.startActivityForResult(intent, SCAN_CODE_RESULT);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == SCAN_CODE_RESULT) {
	         if (resultCode == Activity.RESULT_OK) {
	        	 Bundle bundle = data.getExtras();
	             String scanResult = bundle.getString("result");
	             
	             Log.d("xxxxxxxxxxxxxxxx", "xxxxxxxxxxxxxxxxxx xscan "+ scanResult) ;              
	             if (mScanCb != null && mScanCb.length() > 0) {
	            	 callJS(mScanCb, "\'"+scanResult+"\'");
	             }
	         }
	     }
	}

	
	@JavascriptInterface
	public void get( String url, String cb) {
    	try {
			String res = doRest("GET", url, null);
			callJS(cb, String.format("null,'%s'", res));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			callJS(cb, "{error:\"GET\",url:'"+url+"'}");
		}
	}


	@SuppressLint("NewApi")
	private void callJS(String fun, String args) {
		
		final String s = "javascript:"+fun+"("+args+")";
		
		mHandler.post(new Runnable() {       
            public void run() {  
            	Log.d(TAG, s);
            	mWebView.loadUrl(s);
            }        
        });
	}
  
	@JavascriptInterface
	String doRest(String type, String surl, String data) throws Exception {
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		URL url = new URL(surl);
		System.out.println("doRest()" + surl);
		connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(5000);
		connection.setRequestMethod(type);
		connection.setRequestProperty("ContentType", "text;charset=utf-8");

		if (data != null) {
			connection.setDoOutput(true);
			OutputStream os = connection.getOutputStream();
			os.write(data.getBytes("utf-8"));//
		}
		connection.setDoInput(true);
		in = new InputStreamReader(connection.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(in);
		StringBuffer strBuffer = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			strBuffer.append(line);
		}
		connection.disconnect();
		return strBuffer.toString();
	}
////////////////////////////////////////////////////////
	private MediaPlayer mMdeiaPlayer;
	private void initPlayArarm() {
		mMdeiaPlayer = MediaPlayer.create(
			mActivity.getBaseContext(), R.raw.alarm_1);
		mMdeiaPlayer.setOnCompletionListener(onCompletion);
	}
	private MediaPlayer.OnCompletionListener onCompletion = new MediaPlayer.OnCompletionListener(){
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			
			try {
				mMdeiaPlayer.prepare();
				//mMdeiaPlayer.seekTo(0);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cnt > 0){
				cnt -= 1;
				mMdeiaPlayer.start();
			}
		}
	};
	int cnt = 0;
	@JavascriptInterface
	public void playAlarm()
	{
		
		if (cnt == 0) cnt = 3; 
		//mMdeiaPlayer.setLooping(true);
		
		mMdeiaPlayer.start();
	}
	@JavascriptInterface
	public void stopAlarm()
	{
		if (false){
		mMdeiaPlayer.stop();
		try {
			mMdeiaPlayer.prepare();
			mMdeiaPlayer.seekTo(0);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	//////////////////////////////////////////////////////
	public void onLeScan(int st, String dev)
	{
		
	}
	/*public void onBind(String dev)
	{
		callJS("onBind", dev);
	}*/
	public void onLeUnconnect()
	{
		callJS("onLeUnconnect", "");
	}
	public void onLeConnect()
	{
		callJS("onLeConnect", "");
	}
	public void onLeMessage(String dat)
	{
		callJS("onLeMessage", "'"+dat+"'");
	}
	@JavascriptInterface
	public void LeReqConnect(String mac)
	{
		mActivity.reqConnect(mac);
	}

	@JavascriptInterface
	public void confirmBack() {
		mActivity.onDestroy();
		System.exit(0);
	}	
	@JavascriptInterface
	public void LeReqDisconnect()
	{
		mActivity.LeDisconnect();
	}
	@JavascriptInterface
	public void LeSendMessage(String msg)
	{

		mActivity.sendMessage(msg);
	}
}
