package com.xy.oalarm;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class AlarmService extends Service {
	protected static final String TAG = "AlarmService";
	private BootBroadcastReceiver mBootReceiver;
	private AlarmBroadcastReceiver mAlarmReceiver;
	private long mTimer = 0;
	private static AlarmService mInstance;

	private static Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case 0:
				Log.e(TAG, "start Alarm");
				Calendar today = Calendar.getInstance();
		        today.set(Calendar.HOUR_OF_DAY, 0);
		        today.set(Calendar.MINUTE, 0);
		        today.set(Calendar.SECOND, 0);
		        today.add(Calendar.DATE, 1);
		        long alarmTimeUTC = today.getTimeInMillis();
//				setAlarmTimer(getInstance(), alarmTimeUTC);
				break;

			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	public Handler getHandler(){
		return mHandler;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAlarmReceiver = new AlarmBroadcastReceiver();
		IntentFilter filter = new IntentFilter(Constant.ACTION_ALARM);
		registerReceiver(mAlarmReceiver, filter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(mAlarmReceiver);
//		unregisterReceiver(mBootReceiver);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		return super.onStartCommand(intent, flags, startId);
	}


}
