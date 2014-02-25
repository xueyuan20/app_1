package com.xy.oalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Alarm Database Manager
 * @author 80048916
 *
 */
public class OAlarmDbManager {
	private final static String TAG = "XUEYUAN";
	private TimeDBHelper mDBHelper;
	private SQLiteDatabase mDatabase;
	private static int count = 0;

	public OAlarmDbManager(Context context){
		mDBHelper = new TimeDBHelper(context);
		mDatabase = mDBHelper.getReadableDatabase();
	}

	public void initDB() {
		// TODO Auto-generated method stub
		
	}

	protected void updateDB(TimeItem time) {
		// TODO Auto-generated method stub
		Log.i(TAG, "update DB!");
		ContentValues values = new ContentValues();
		values.put(Constant.ALARM_ID, mDatabase.getMaximumSize());
		values.put(Constant.ALARM_NAME, "testAlarm");
		values.put(Constant.ALARM_HOUROFDAY, time.mHourOfDay);
		values.put(Constant.ALARM_MINUTE, time.mMinuteOfHour);
		mDBHelper.insertColumn(values);
	}

	protected void insertItem(TimeItem time) {
		Log.i(TAG, "insert DB!");
		if (time == null) {
			return ;
		}
		ContentValues values = new ContentValues();
		values.put(Constant.ALARM_ID, count++);
		values.put(Constant.ALARM_NAME, "testAlarm");
		values.put(Constant.ALARM_HOUROFDAY, time.mHourOfDay);
		values.put(Constant.ALARM_MINUTE, time.mMinuteOfHour);
		mDBHelper.insertColumn(values);
	}

	protected Cursor query(String query) {
		if (mDBHelper != null) {
			return mDBHelper.query();
		}
		return null;
	}

	protected Cursor queryAlarmList() {
		if (mDBHelper != null) {
			return mDBHelper.query();
		}
		return null;
	}
}
