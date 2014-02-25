package com.xy.oalarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TimeDBHelper extends SQLiteOpenHelper {
	private static final String TAG = "XUEYUAN";
	private static String DB_NAME = "OAlarm";
	private static int DB_VERSION = 1;
	private SQLiteDatabase mDatabase;

	private String[] ALARM_COLUMNS = {
			Constant.ALARM_ID,
			Constant.ALARM_HOUROFDAY,
			Constant.ALARM_MINUTE,
	};

	public TimeDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
		Log.i(TAG, "constructor");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i(TAG, "oncreate");
		mDatabase = db;

		db.execSQL("CREATE TABLE "+DB_NAME +"("
				+ Constant.ALARM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Constant.ALARM_NAME + " TEXT DEFAULT NULL, "
				+ Constant.ALARM_HOUROFDAY + " INTEGER, "
				+ Constant.ALARM_MINUTE + " INTEGER, "
				+ Constant.ALARM_TYPE + " INTEGER DEFAULT 0"
				+ ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

		Log.i(TAG, "upgrade database!");
	}

	
	public void insertColumn(ContentValues values){
		Log.i(TAG, "insert");
		if (values.containsKey(Constant.ALARM_ID)
				&& values.containsKey(Constant.ALARM_NAME)
				&& values.containsKey(Constant.ALARM_HOUROFDAY)
				&& values.containsKey(Constant.ALARM_MINUTE)) {			
			Log.i(TAG, Constant.ALARM_ID + " = "
					+ values.getAsString(Constant.ALARM_ID));
			Log.i(TAG, Constant.ALARM_NAME + " = "
					+ values.getAsString(Constant.ALARM_NAME));
			Log.i(TAG, Constant.ALARM_HOUROFDAY + " = "
					+ values.getAsString(Constant.ALARM_HOUROFDAY));
			Log.i(TAG, Constant.ALARM_MINUTE + " = "
					+ values.getAsString(Constant.ALARM_MINUTE));
		}

		mDatabase = getReadableDatabase();
		mDatabase.insert(DB_NAME, null, values);
	}

	public Cursor query(){
		Cursor cursor;
		Log.i(TAG, "isOpen = "+mDatabase.isOpen());
		mDatabase = getReadableDatabase();
		cursor = mDatabase.query(DB_NAME, ALARM_COLUMNS,
				Constant.ALARM_NAME + " = ? ", new String[]{"testAlarm"},
				null, null, null);
		if (cursor.moveToFirst()) {
			Log.i(TAG, "readable");
		}
		return cursor;
	}

	public synchronized SQLiteDatabase getReadDatabase(){
		if (mDatabase != null && mDatabase.isOpen()) {
			return mDatabase;
		}

		SQLiteDatabase db;
		try {
			db = getReadableDatabase();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return db;
	}
}
