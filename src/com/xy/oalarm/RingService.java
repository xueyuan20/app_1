package com.xy.oalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

public class RingService extends Service {
	private MediaPlayer mPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		startRing();
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		startRing();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopRing();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * ��������
	 */
	private void startRing() {
		// TODO Auto-generated method stub

		Uri ringUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		
	}

	/**
	 * ֹͣ����
	 */
	private void stopRing() {
		// TODO Auto-generated method stub
		
	}

}
