package com.xy.oalarm;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * OAlarmProvider, manager the access to local data.
 * @author 80048916
 *
 */
public class OAlarmProvider extends ContentProvider {
	// authority
	private static final String AUTHORITY = "com.xy.oalarm.OAlarmProvider";
	private static final String PATH = "oAlarm";
	private static final int	ALARM_CODE = 1;
	private static final String TAG = "XUEYUAN";
	private TimeDBHelper mHelper;
	private static UriMatcher MATCHER;
	static {
		MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		MATCHER.addURI(AUTHORITY, PATH, ALARM_CODE);
	}

	public void testProvider(){
		String url = "content://com.xy.oalarm.OAlarmProvider/oAlarm/1";
		Log.e(TAG, "code = "+MATCHER.match(Uri.parse(url)));
	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri arg0, ContentValues arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri arg0, String[] arg1, String arg2, String[] arg3,
			String arg4) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Uri arg0, ContentValues arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return 0;
	}

}
