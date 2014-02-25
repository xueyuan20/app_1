package com.xy.oalarm;

/**
 * Current Time Class
 * @author 80048916
 *
 */
public class TimeItem {
	protected int mHourOfDay;
	protected int mMinuteOfHour;

	public TimeItem(int hour, int minute){
		mHourOfDay = hour;
		mMinuteOfHour = minute;
	}

	public void setHour(int hour){
		mHourOfDay = hour;
	}

	public int getHour(){
		return mHourOfDay;
	}

	public void setMinute(int minute){
		mMinuteOfHour = minute;
	}

	public int getMinute(){
		return mMinuteOfHour;
	}
}
