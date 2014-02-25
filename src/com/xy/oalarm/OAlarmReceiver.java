package com.xy.oalarm;

import java.util.Calendar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class OAlarmReceiver extends BroadcastReceiver {
	private static final String TAG = "XUEYUAN";

	public OAlarmReceiver(){
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (Constant.ACTION_ALARM.equals(intent.getAction())) {
            //第1步中设置的闹铃时间到，这里可以弹出闹铃提示并播放响铃
            //可以继续设置下一次闹铃时间;
			Log.e(TAG, "receive message to test alarm");

			String time = "";
			// Elaborate mechanism to find out when the day rolls over
			Bundle bundle = intent.getExtras();
	        Calendar today = Calendar.getInstance();
	        today.set(Calendar.HOUR_OF_DAY, bundle.getInt(Constant.ALARM_HOUROFDAY));
	        today.set(Calendar.MINUTE, bundle.getInt(Constant.ALARM_MINUTE));
	        today.set(Calendar.SECOND, 0);
	        long alarmTimeUTC = today.getTimeInMillis();
	        time = String.valueOf(alarmTimeUTC);
			Toast.makeText(context, time, Toast.LENGTH_LONG).show();
			setAlarmTimer(context, alarmTimeUTC);
            return;
        }
	}

	private void setAlarmTimer(Context context, long timer){
		if (context == null) {
			return;
		}
//		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
//		Intent intent = new Intent(Constant.ACTION_ALARM);
//		PendingIntent sender = PendingIntent.getBroadcast(
//			 context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
//		int interval = 60 * 1000;
//		//闹铃间隔， 这里设为1分钟闹一次，在第2步我们将每隔1分钟收到一次广播
//		am.setRepeating(AlarmManager.RTC_WAKEUP, timer, interval, sender);

	}
}
