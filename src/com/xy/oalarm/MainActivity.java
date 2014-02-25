package com.xy.oalarm;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	private static final String TAG = "XUEYUAN";
	private Button mTestAlarm;
	private Button mAddAlarm;
	private TimePicker mTimePicker;

	private TimeItem mCurrentTime;
	private OAlarmDbManager mDBManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDBManager = ((OAlarmApplication)getApplicationContext()).getOAlarmDBManager();
		initDB();

		mTestAlarm = (Button)findViewById(R.id.startAlarm);
		mTestAlarm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Constant.ACTION_ALARM);
				Bundle bundle = new Bundle();
				bundle.putInt(Constant.ALARM_HOUROFDAY, mCurrentTime.mHourOfDay);
				bundle.putInt(Constant.ALARM_MINUTE, mCurrentTime.mMinuteOfHour);
				intent.putExtras(bundle);
				sendBroadcast(intent);
			}
		});
		mTimePicker = (TimePicker)findViewById(R.id.timepicker);
		mTimePicker.setCurrentHour(mCurrentTime.mHourOfDay);
		mTimePicker.setCurrentMinute(mCurrentTime.mMinuteOfHour);
		mTimePicker.setIs24HourView(true);
		mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				mCurrentTime.mHourOfDay = hourOfDay;
				mCurrentTime.mMinuteOfHour = minute;
			}
		});

		mAddAlarm = (Button)findViewById(R.id.add_alarm);
		mAddAlarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub

				((OAlarmApplication)getApplicationContext()).getOAlarmDBManager().insertItem(mCurrentTime);
				updateAlarms();
			}
		});
		/**
		 * Start Alarm Service
		 */
		startService(new Intent(MainActivity.this, OAlarmService.class));
	}

	/**
	 * Update alarm list after time changed.
	 */
	protected void updateAlarms() {
		// TODO Auto-generated method stub
		Cursor cursor = mDBManager.queryAlarmList();
		if (cursor != null && cursor.moveToFirst()) {
			do {
				int columnCount = cursor.getColumnCount();
				Log.e(TAG, String.valueOf(columnCount));
				Log.i(TAG, Constant.ALARM_ID + ":" + cursor.getCount());
				for (int i = 0; i < columnCount; i++) {
					Log.i(TAG, cursor.getColumnName(i)+" = " + cursor.getInt(i));
				}
			} while (cursor.moveToNext());
		}
	}

	/**
	 * 
	 */
	protected void initDB() {
		Log.i(TAG, "initialize DB!");
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		mCurrentTime = new TimeItem(today.hour, today.minute);
		mDBManager.insertItem(null);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
