package com.zonesion.web.EnergyManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zonesion.util.js.JSRestInterface;
import com.zonesion.web.EnergyManagement.R;
import com.zonesion.web.EnergyManagement.SQLite.MonthSQLiteHelper;
import com.zonesion.zxbee.ble.BLEService;
import com.zonesion.zxbee.ble.BLEService.LocalBinder;
import com.zonesion.zxbee.ble.beacon.MTBeacon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;
/**
 * 原创
 */
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	WebView mWebView;
	private Handler mHandler = new Handler();
	private Button btn_getIntoRecharge;
	private String month;
	
	int REQUEST_ENABLE_BT = 1001;
	
	static final String KEY_BIND_DEV_ADDR = "_bind_dev_addr";

	String mCurrentBindAddr ="";
	JSRestInterface mJSRestInterface;
	
	BluetoothManager mBluetoothManager;
	BluetoothAdapter mBluetoothAdapter;
	
	SharedPreferences mSharedPreferences;
	
    @SuppressLint({"NewApi", "JavascriptInterface"})
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//
        setContentView(R.layout.activity_main);
        
   	 	
   	 	mSharedPreferences= getSharedPreferences("xble", 
   			Activity.MODE_PRIVATE);
   	 	
   		mWebView = (WebView) findViewById(R.id.webView);
   		mWebView.getSettings().setDefaultTextEncodingName("UTF-8") ;
   		mWebView.getSettings().setJavaScriptEnabled(true);
   		mWebView.getSettings().setUseWideViewPort(true); 
   		mWebView.getSettings().setLoadWithOverviewMode(true);
   		mWebView.getSettings().setDomStorageEnabled(true);


   		mWebView.setWebViewClient(new WebViewClient(){
               public boolean shouldOverrideUrlLoading(WebView view, String url) {
                   view.loadUrl(url);
                   return true;        
               }        
   		});

   		mWebView.setWebChromeClient(new WebChromeClient() { 
   			public void onConsoleMessage(String message, int lineNumber, String sourceID) { 
   				Log.d("JScript Log", sourceID+":"+lineNumber+": "+message ); 
   			}
	   		 @Override
	         public void onReceivedTitle(WebView view, String title) {
	   			MainActivity.this.setTitle(title);//a textview
	         }
   		});
   		
   		
   		mJSRestInterface = new JSRestInterface(this, mWebView, mHandler);
   		
   		mWebView.addJavascriptInterface(mJSRestInterface, "droid");
   		
        mWebView.loadUrl("file:///android_asset/index.html");
         

		
        mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		if (null == mBluetoothManager) {
			return ;
		}
		mBluetoothAdapter = mBluetoothManager.getAdapter();
		if (null == mBluetoothAdapter) {
			return ;
		}
		//
		mWebView.addJavascriptInterface(new JS(),"android");

		btn_getIntoRecharge=findViewById(R.id.btn_getintorecharge);
		btn_getIntoRecharge.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this,month,Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(MainActivity.this,RechargeActivity.class);
				intent.putExtra("monthquantity",month);
				startActivity(intent);
			}
		});
    }
    
    @SuppressLint("NewApi")
	public void onResume(){
    	super.onResume();
    	//startLeScan();
    }
    
    @SuppressLint("NewApi")
	public void onPause() {
    	
    	//stopLeScan();
    	super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK) {
        	callJS("getback", "");
        	return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    
	@SuppressLint("NewApi")
	private void callJS(String fun, String args) {

		final String s = "javascript:" + fun + "(" + args + ")";

		mHandler.post(new Runnable() {
			public void run() {

				mWebView.loadUrl(s);
			}
		});
	}
    
    
    public void onDestroy(){
    	//stopScanBle();
    	//unbindService(connection);
    	if(mBluetoothGatt != null){
			mBluetoothGatt.disconnect();
			mBluetoothGatt.close();
			mBluetoothGatt = null;
		}
    	
    	super.onDestroy();
    	System.exit(0);
    }

    

	
	private List<MTBeacon> scan_devices = new ArrayList<MTBeacon>();
	
	@SuppressLint("NewApi") 
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

		@Override
		public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
			int i = 0;


			for (i = 0; i < scan_devices.size(); i++) {
				if (0 == device.getAddress().compareTo(
						scan_devices.get(i).GetDevice().getAddress())) {
					scan_devices.get(i).ReflashInf(device, rssi, scanRecord); //

					String s = "{mac:\""+device.getAddress()+"\",name:\""+device.getName()+"\", rssi:"+rssi+"}";
					mJSRestInterface.onLeScan(1, s);

					return;
				}
			}

			scan_devices.add(new MTBeacon(device, rssi, scanRecord));
			String s = "{mac:\""+device.getAddress()+"\",name:\""+device.getName()+"\", rssi:"+rssi+"}";
			mJSRestInterface.onLeScan(0, s);
		}
	};


	private boolean scan_flag = false;
	boolean request_onBind = false;
	Handler search_timer = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				for (int i = 0; i < scan_devices.size();) { //
					if (scan_devices.get(i).getActiveCount() == 0) {
						MTBeacon device = scan_devices.get(i);

						String s = "{mac:\""+device.GetDevice().getAddress()+"\",name:\""+device.GetDevice().getName()+"\", rssi:"+0+"}";
						mJSRestInterface.onLeScan(2, s);
						scan_devices.remove(i);
					} else {
						scan_devices.get(i).reduceActiveCount();
						i++;
					}

				}
				if (scan_flag)search_timer.sendEmptyMessageDelayed(1, 1000);
			}
			if (msg.what == 2) {
				if(mBluetoothGatt != null){
					mBluetoothGatt.disconnect();
					mBluetoothGatt.close();
					mBluetoothGatt = null;
				}
				//mJSRestInterface.onBind(mCurrentBindAddr);
				if (mCurrentBindAddr.length() > 0) {
					if (request_onBind) {
						request_onBind = false;
					}
					connectBle();
				}
			}
		}
	};
	private BluetoothGatt mBluetoothGatt;


	public void reqConnect(String mac) {
		if (!mCurrentBindAddr.equals(mac)) {

			mCurrentBindAddr = mac;

			request_onBind = true;


			search_timer.sendEmptyMessage(2);
		}
		//connectBle();
	}
	private void connectBle() {
		BluetoothDevice device_tmp = null;
		try {
			device_tmp = mBluetoothAdapter.getRemoteDevice(mCurrentBindAddr);
		} catch(Exception e) {

		}
		if(device_tmp == null){
			Toast.makeText(getApplicationContext(), "设备不存在，请重新选择！",
				     Toast.LENGTH_SHORT).show();
			return;
		}

		mBluetoothGatt = device_tmp.connectGatt(getApplicationContext(), false,
				mGattCallback);
	}
	public void LeDisconnect(){
		if(mBluetoothGatt != null){
			mBluetoothGatt.disconnect();
			mBluetoothGatt.close();
			mBluetoothGatt = null;
		}
	}
	boolean connect_flag = false;
	private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

		@Override
		public void onConnectionStateChange(BluetoothGatt gatt, int status,
				int newState) {
			super.onConnectionStateChange(gatt, status, newState);
			if (newState == BluetoothProfile.STATE_CONNECTED) { //
				System.out.println("CONNECTED");
				connect_flag = true;
				mBluetoothGatt.discoverServices();

				String mac = mBluetoothGatt.getDevice().getAddress();




			} else if (newState == BluetoothProfile.STATE_DISCONNECTED) { //
				System.out.println("UNCONNECTED");
				connect_flag = false;
				//broadcastUpdate(ACTION_STATE_DISCONNECTED);
				mJSRestInterface.onLeUnconnect();

				search_timer.sendEmptyMessageDelayed(2, 3000);
			} 

		}  

		@Override
		public void onServicesDiscovered(BluetoothGatt gatt, int status) {
			super.onServicesDiscovered(gatt, status);
			if (status == BluetoothGatt.GATT_SUCCESS) {
				System.out.println("onServicesDiscovered");
				
					
					UUID su = UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb");
					UUID cu = UUID.fromString("0000aaa1-0000-1000-8000-00805f9b34fb"); 
					BluetoothGattCharacteristic c = gatt.getService(su).getCharacteristic(cu);

					gatt.setCharacteristicNotification(c, true);
					BluetoothGattDescriptor descriptor = c.getDescriptor(UUID
									.fromString("00002902-0000-1000-8000-00805f9b34fb"));
					descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
					gatt.writeDescriptor(descriptor);
				 
					mJSRestInterface.onLeConnect();
			}
		} 

		@Override
		public void onDescriptorRead(BluetoothGatt gatt,
				BluetoothGattDescriptor descriptor, int status) {
			super.onDescriptorRead(gatt, descriptor, status);


		}

		@Override
		public void onCharacteristicRead(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic, int status) {
			super.onCharacteristicRead(gatt, characteristic, status);
			
			if (status == BluetoothGatt.GATT_SUCCESS) {

			}
		} 

		@Override
		public void onCharacteristicChanged(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic) {
			super.onCharacteristicChanged(gatt, characteristic);

			String dat = new String(characteristic.getValue());
			Log.d("ble", gatt.getDevice().getAddress()+" >>> "+dat);
			mJSRestInterface.onLeMessage(dat);
		}
		 
  
		@Override 
		public void onCharacteristicWrite(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic, int status) {
			super.onCharacteristicWrite(gatt, characteristic, status);
			//broadcastUpdate(ACTION_WRITE_OVER, status);
			Log.d("liren_ble", "onCharacteristicWrite....");
			//mSendQue.remove(0);
			synchronized (mLock){
				mWaitSend = mWaitSend.substring(wSendLength);
			}
			mTimerSend.sendEmptyMessage(0);
		}

	};  
	      
	long mSendTm = 0;

	String mWaitSend = ""; 
	int wSendLength = 0;
	Handler mTimerSend = new Handler(){
		@Override
		public void handleMessage(Message m) { 
			if (m.what == 0) {
				if (mWaitSend.length() > 0) {
					String st;
					synchronized (mLock){
						if (mWaitSend.length()>20) {
							st = mWaitSend.substring(0, 20);
							//mWaitSend = mWaitSend.substring(20);
							
						} else {
							st = mWaitSend;
							//mWaitSend = "";
						}
					}
					wSendLength = st.length();

					UUID su = UUID.fromString("0000aaa0-0000-1000-8000-00805f9b34fb");
					UUID cu = UUID.fromString("0000aaa1-0000-1000-8000-00805f9b34fb");
					if (mBluetoothGatt != null) {
						
						BluetoothGattCharacteristic c = mBluetoothGatt.getService(su).getCharacteristic(cu);
						c.setValue(st);
						Log.d("liren_ble", "characteristicWrite.....");
						Log.d("ble", " >>> "+st);
						mBluetoothGatt.writeCharacteristic(c);
						//mTimerSend.sendEmptyMessageDelayed(1, 5000);
					}
				}
			} else if (m.what == 1) {
				//Log.d("liren_ble", "send timeout "+mSendQue.get(0));
				//if (mSendQue.size()>0){
					//	mSendQue.remove(0);
				//}
				//mTimerSend.sendEmptyMessage(0);
			}
		}
	};
	Object mLock = new Object();
	public void sendMessage(String msg) {
		synchronized (mLock){
			if (mWaitSend.length() == 0) {
				mWaitSend = msg;
				mTimerSend.obtainMessage(0).sendToTarget();
			} else {
				mWaitSend = mWaitSend + msg;
			}
		}/*
		mSendQue.add(msg);
		if (mSendQue.size() == 1) {
			mTimerSend.obtainMessage(0).sendToTarget();
		}*/
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 // TODO Auto-generated method stub
		 mJSRestInterface.onActivityResult(requestCode, resultCode, data);
	 }

	public class JS{
		@JavascriptInterface
		public void get(String p){
			Log.i("aa",p);
			month=p;

		}

	}
}
