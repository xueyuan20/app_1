package com.xy.oalarm;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;

public class OAlarmService extends Service {
	protected static final String TAG = "XUEYUAN";

	private static final int TIMER_PERIOD = 60*1000;//ÐÄÌøÂö³åÖÜÆÚ5Ãë

	private OAlarmReceiver mAlarmReceiver;
	private OAlarmDbManager mAlarmDBManager;

	private Thread mHeartBeat = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mHandler.postDelayed(this, TIMER_PERIOD);
			Log.e(TAG, "timer");
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			Cursor cursor = mAlarmDBManager.queryAlarmList();
			if (cursor != null && cursor.moveToFirst()) {
				TimeItem time;
				do {
					int columnCount = cursor.getColumnCount();
					Log.e(TAG, String.valueOf(columnCount));
					Log.i(TAG, Constant.ALARM_ID + ":" + cursor.getCount()
							);
					if( today.hour == cursor.getInt(cursor.getColumnIndex(Constant.ALARM_HOUROFDAY))
							&& today.minute == cursor.getInt(cursor.getColumnIndex(Constant.ALARM_MINUTE))){
						Log.e(TAG, "start Ring Ring Ring Ring Ring Ring Ring Ring");
					}
					
				} while (cursor.moveToNext());
			}
		}
	});

	private static Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				Log.e(TAG, "start Alarm");
				
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAlarmReceiver = new OAlarmReceiver();
		IntentFilter filter = new IntentFilter(Constant.ACTION_ALARM);
		registerReceiver(mAlarmReceiver, filter);
		mAlarmDBManager = ((OAlarmApplication)getApplicationContext()).getOAlarmDBManager();

		mHeartBeat.start();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mAlarmReceiver);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		return super.onStartCommand(intent, flags, startId);
	}


}
