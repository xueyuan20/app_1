package com.xy.oalarm;

import java.io.IOException;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.text.format.Time;
import android.util.Log;

public class OAlarmService extends Service {
	protected static final String TAG = "XUEYUAN";

	private static final long[] sVibratePattern = new long[] { 500, 500 };
	private static final int TIMER_PERIOD = 3*1000;//ÐÄÌøÂö³åÖÜÆÚ5Ãë

	private OAlarmReceiver mAlarmReceiver;
	private OAlarmDbManager mAlarmDBManager;

	private MediaPlayer mPlayer;
	private Vibrator mVibrator;

	private Thread mHeartBeat = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			mHandler.postDelayed(this, TIMER_PERIOD);
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			Log.e(TAG, "Current time: [hour] "+today.hour + ", [minute] "+today.minute);
			Cursor cursor = mAlarmDBManager.queryAlarmList();
			if (cursor != null && cursor.moveToFirst()) {
				int hour = 0;
				int minute = 0;
				do {
					int columnCount = cursor.getColumnCount();
					Log.e(TAG, "Cursor Column Count : " + String.valueOf(columnCount));
					Log.i(TAG, "Cursor Count :" + cursor.getCount());
					hour = cursor.getInt(cursor.getColumnIndex(Constant.ALARM_HOUROFDAY));
					minute = cursor.getInt(cursor.getColumnIndex(Constant.ALARM_MINUTE));
					Log.e(TAG, "[set hour]"+hour + ", [set minute]"+minute);
					if( today.hour == hour && today.minute == minute ){
						Log.e(TAG, "start Ring Ring Ring Ring Ring Ring Ring Ring");
						/*
						 * Start Ring Service
						 */
						mHandler.sendEmptyMessage(Constant.MESSAGE_RINGING);
						
					}
				} while (cursor.moveToNext());
			}
		}
	});

	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch (msg.what) {
			case Constant.MESSAGE_RINGING:
				Log.e(TAG, "start Alarm");
				startAlarm();
				break;

			case Constant.MESSAGE_NOTIFICATION:
				Log.e(TAG, "show notification");

				break;

			case Constant.MESSAGE_PLAY_STOP:
				Log.e(TAG, "stop playing");
				if (mPlayer!=null && mPlayer.isPlaying()) {
					mPlayer.stop();
				}
				if (mVibrator!=null) {
					mVibrator.cancel();
				}
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
		/**
		 * initialize
		 */
		mAlarmReceiver = new OAlarmReceiver();
		IntentFilter filter = new IntentFilter(Constant.ACTION_ALARM);
		registerReceiver(mAlarmReceiver, filter);

		/**
		 * initialize DB manager
		 */
		mAlarmDBManager =
				((OAlarmApplication)getApplicationContext()).getOAlarmDBManager();

		/**
		 * start Heart Beat
		 */
		mHeartBeat.start();

		/**
		 * initialize Alarm Player
		 */
		mPlayer = new MediaPlayer();
		mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				Log.e(TAG, "Error occurred while playing audio.");
                mp.stop();
                mp.release();
                mPlayer = null;
                return true;
			}
		});

		/**
		 * initialize vibrate
		 */
		mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
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

	private void startAlarm() {
		// TODO Auto-generated method stub
		try {
			setDataSourceFromResource(getResources(), mPlayer, R.raw.in_call_alarm);
			mVibrator.vibrate(sVibratePattern, 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mPlayer.stop();
		}
	}


    private void setDataSourceFromResource(Resources resources,
            MediaPlayer player, int res) throws java.io.IOException {
    	player = MediaPlayer.create(getApplicationContext(), R.raw.in_call_alarm);
    	player.start();
    	mHandler.sendEmptyMessageDelayed(Constant.MESSAGE_PLAY_STOP, 10*1000);
    }
}
