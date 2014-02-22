package com.xy.oalarm;

import java.util.Calendar;
import java.util.Timer;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
	private static final String TAG = "AlarmReceiver";

	public AlarmBroadcastReceiver(){
		
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if (Constant.ACTION_ALARM.equals(intent.getAction())) {
            //��1�������õ�����ʱ�䵽��������Ե���������ʾ����������
            //���Լ���������һ������ʱ��;
			Log.e(TAG, "receive message to test alarm");

			String time = "";
			// Elaborate mechanism to find out when the day rolls over
	        Calendar today = Calendar.getInstance();
	        today.set(Calendar.HOUR_OF_DAY, 17);
	        today.set(Calendar.MINUTE, 10);
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
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(Constant.ACTION_ALARM);
		PendingIntent sender = PendingIntent.getBroadcast(
			 context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		int interval = 60 * 1000;
		//�������� ������Ϊ1������һ�Σ��ڵ�2�����ǽ�ÿ��1�����յ�һ�ι㲥
		am.setRepeating(AlarmManager.RTC_WAKEUP, timer, interval, sender);
	}
}
