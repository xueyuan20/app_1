package com.xy.oalarm;

import android.app.Application;

public class OAlarmApplication extends Application {
	private OAlarmDbManager mDBManager;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mDBManager = new OAlarmDbManager(getApplicationContext());
		mDBManager.initDB();
	}

	public OAlarmDbManager getOAlarmDBManager(){
		return mDBManager;
	}
}
