package org.gdocument.gchattoomuch.lib.manager;

import org.gdocument.gchattoomuch.lib.constant.ConstantSharedPreferences;
import org.gdocument.gchattoomuch.lib.log.Logger;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;

public class SharedPreferenceManager {

	private static final String TAG = SharedPreferenceManager.class.getName();

	public static final long SERVICE_EXPORT_SCHEDULE_TIME_HOUR_1 = 1000 * 60 * 60;
	public static final long SERVICE_EXPORT_SCHEDULE_TIME_HOUR_24 = SERVICE_EXPORT_SCHEDULE_TIME_HOUR_1 * 24;
	public static final long SERVICE_EXPORT_SCHEDULE_TIME_SECOUND_30 = 1000 * 30;

	private static final int SERVICE_EXPORT_SMS_LIMITE_COUNT = 100;
	private static final int SERVICE_EXPORT_CONTACT_LIMITE_COUNT = 100;

	private static SharedPreferenceManager instance = null;

	private SharedPreferences sharedPreferences;

	protected SharedPreferenceManager(Context context) {
		try {
			Context sharedContext = context.createPackageContext(ConstantSharedPreferences.PACKAGE_NAME, ConstantSharedPreferences.COMMON_MODE);
			sharedPreferences = sharedContext.getSharedPreferences(ConstantSharedPreferences.COMMON_NAME, ConstantSharedPreferences.COMMON_MODE);
		} catch (NameNotFoundException e) {
			logMe(e);
		}
	}

	public static SharedPreferenceManager getInstance(Context context) {
		if (instance == null) {
			instance = new SharedPreferenceManager(context.getApplicationContext());
		}
		return instance;
	}

	private void saveSmsCount(int count) {
		Editor editor = sharedPreferences.edit();
		editor.putInt(ConstantSharedPreferences.KEY_SMS_COUNT, count);
		editor.commit();
	}

	private void saveContactCount(int count) {
		Editor editor = sharedPreferences.edit();
		editor.putInt(ConstantSharedPreferences.KEY_CONTACT_COUNT, count);
		editor.commit();
	}

	private void saveScheduleTime(long scheduleTime) {
		Editor editor = sharedPreferences.edit();
		editor.putLong(ConstantSharedPreferences.KEY_SCHEDULE_TIME, scheduleTime);
		editor.commit();
	}

	public long getServiceExportScheduleTime() {
		return sharedPreferences.getLong(ConstantSharedPreferences.KEY_SCHEDULE_TIME, SERVICE_EXPORT_SCHEDULE_TIME_HOUR_24);
	}

	public void setServiceExportScheduleTime(long scheduleTime) {
		saveScheduleTime(scheduleTime);
	}

	public int getServiceExportSmsLimitCount() {
		return sharedPreferences.getInt(ConstantSharedPreferences.KEY_SMS_COUNT, SERVICE_EXPORT_SMS_LIMITE_COUNT);
	}

	public void setServiceExportSmsLimitCount(int serviceCount) {
		saveSmsCount(serviceCount);
	}

	public int getServiceExportContactLimitCount() {
		return sharedPreferences.getInt(ConstantSharedPreferences.KEY_CONTACT_COUNT, SERVICE_EXPORT_CONTACT_LIMITE_COUNT);
	}

	public void setServiceExportContactLimitCount(int serviceCount) {
		saveContactCount(serviceCount);
	}

	@SuppressWarnings("unused")
	private void logMe(String msg) {
		Logger.logMe(TAG, msg);
	}

	@SuppressWarnings("unused")
	private static void logEr(String msg) {
		Logger.logEr(TAG, msg);
    }

	@SuppressWarnings("unused")
	private static void logMe(Exception ex) {
		Logger.logMe(TAG, ex);
	}
}