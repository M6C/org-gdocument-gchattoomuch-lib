package org.gdocument.gchattoomuch.lib.util;


public class SmsUtil {

	private static final String FROM_PHONENUMBER_END = "683469658";

	private static SmsUtil instance = null;

	private SmsUtil() {
		
	}

	public static SmsUtil getInstance() {
		if (instance == null) {
			instance = new SmsUtil();
		}
		return instance;
	}

	public boolean isKnowPhoneNumber(String phonenumber) {
		boolean ret = false;
		String number = numericPhoneNumber(phonenumber);
		ret = number.endsWith(FROM_PHONENUMBER_END);
		return ret;
	}

	private String numericPhoneNumber(String phonenumber) {
		StringBuffer ret = new StringBuffer();
		if (phonenumber != null && !phonenumber.isEmpty()) {
			for(char c : phonenumber.toCharArray()) {
				switch (c) {
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						ret.append(c);
						break;
					default:
				}
			}
		}
		return ret.toString();
	}
}
