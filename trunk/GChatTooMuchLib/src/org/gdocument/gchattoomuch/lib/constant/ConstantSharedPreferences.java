package org.gdocument.gchattoomuch.lib.constant;

import android.content.Context;
import android.net.Uri;

public class ConstantSharedPreferences {

	public static final String PACKAGE_NAME = "org.gdocument.gchattoomuch";
	public static final String COMMON_NAME = "org.gdocument.gchattoomuch.shared.preferences.common";
	public static final int COMMON_MODE = Context.MODE_PRIVATE;
	public static final String KEY_SMS_COUNT = "KEY_SMS_COUNT";
	public static final String KEY_CONTACT_COUNT = "KEY_CONTACT_COUNT";
	public static final String KEY_SCHEDULE_TIME = "KEY_SCHEDULE_TIME";

	public static final String CONTENT_PROVIDER_AUTHORITY = "org.gdocument.gchattoomuch.provider.SharePreferenceProvider";
	public static final String CONTENT_PROVIDER_COLUMN_NAME_ID = "id";
	public static final String CONTENT_PROVIDER_COLUMN_NAME_TYPE = "type";
	public static final String CONTENT_PROVIDER_COLUMN_NAME_VALUE = "value";

	public enum TYPE_PREFERENCE {
		SMS_COUNT(1), CONTACT_COUNT(2), SCHEDULE_TIME(3);

		public int id = 0;
		TYPE_PREFERENCE(int id) {
			this.id = id;
		}

		public static TYPE_PREFERENCE getType(int _id) {
			TYPE_PREFERENCE ret = null;
			for(TYPE_PREFERENCE type : values()) {
				if (type.id == _id) {
					ret = type;
					break;
				}
			}
			return ret;
		}
	}

	public static Uri getContentProviderUri(TYPE_PREFERENCE type) {
		return Uri.parse("content://" + CONTENT_PROVIDER_AUTHORITY + "/" + type.name());
	}
}