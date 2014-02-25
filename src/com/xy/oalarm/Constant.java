package com.xy.oalarm;

/**
 * Constants Class
 * @author 80048916
 *
 */
public class Constant {

	/**
	 * Intent Actions
	 */
	public static String ACTION_ALARM = "android.alarm.demo.action";

	/**
	 * Database Keys
	 */
	public static final String ALARM_ID = "_id";

	public static final String ALARM_NAME = "_name";

	public static final String ALARM_HOUROFDAY = "_hourOfDay";

	public static final String ALARM_MINUTE = "_minute";

	public static final String ALARM_TYPE = "_type";

	/**
	 * Alarm Columns
	 */

	/**
	 * Message ID : 0, told application to start ring service.
	 */
	public static final int MESSAGE_RINGING = 0;

	/**
	 * Message ID : 1, told application to start show notification.
	 */
	public static final int MESSAGE_NOTIFICATION = 1;

	/**
	 * Message ID : 2, told application to stop playing.
	 */
	protected static final int MESSAGE_PLAY_STOP = 2;
}
