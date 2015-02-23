package org.gdocument.gchattoomuch.lib.task;


import org.gdocument.gchattoomuch.lib.log.Logger;
import org.gdocument.gchattoomuch.lib.util.NetworkUtilities;

import android.os.AsyncTask;


/**
 * Represents an asynchronous task used to authenticate a user against the
 * SampleSync Service
 */
public class UserLoginTask2 extends AsyncTask<Object, Void, String> {

    private static final String TAG = UserLoginTask2.class.getName();

	// Service Name
	final static String SPREADSHEET_API_SERVICE_NAME = "wise";
	final static String DOCUMENT_LIST_API_SERVICE_NAME = "writely";

	/** The Intent flag to confirm credentials. */
    public static final String PARAM_CONFIRM_CREDENTIALS = "confirmCredentials";

    /** The Intent extra to store password. */
    public static final String PARAM_PASSWORD = "password";

    /** The Intent extra to store username. */
    public static final String PARAM_USERNAME = "username";

    /** The Intent extra to store username. */
    public static final String PARAM_AUTHTOKEN_TYPE = "authtokenType";

    private String mConfirmCredentials = null;
	private String mMessage;
	private String mUsername;
	private String mPassword;

    public UserLoginTask2(String confirmCredentials, String message, String username, String password) {
    	this.mConfirmCredentials = confirmCredentials;
		this.mMessage = message;
		this.mUsername = username;
		this.mPassword = password;
	}

    @Override
    protected String doInBackground(Object... params) {
        // We do the actual work of authenticating the user
        // in the NetworkUtilities class.
        try {
			String service = SPREADSHEET_API_SERVICE_NAME;
			logMe(TAG, "UserLoginTask NetworkUtilities.authenticate BEFORE mMessage:"+mMessage+" mUsername:"+mUsername+" mPassword:"+mPassword+" service:"+service);
        	String authtockenSpreadsheet = NetworkUtilities.authenticate(mUsername, mPassword, service);
        	logMe(TAG, "UserLoginTask NetworkUtilities.authenticate AFTER authtockenSpreadsheet:"+authtockenSpreadsheet);

			service = DOCUMENT_LIST_API_SERVICE_NAME;
			logMe(TAG, "UserLoginTask NetworkUtilities.authenticate BEFORE mMessage:"+mMessage+" mUsername:"+mUsername+" mPassword:"+mPassword+" service:"+service);
        	String authtockenDocument = NetworkUtilities.authenticate(mUsername, mPassword, service);
        	logMe(TAG, "UserLoginTask NetworkUtilities.authenticate AFTER authtockenDocument:"+authtockenDocument);

        	String authtocken = SPREADSHEET_API_SERVICE_NAME+":"+authtockenSpreadsheet
        	+ ";"
        	+ DOCUMENT_LIST_API_SERVICE_NAME+":"+authtockenDocument;

        	return authtocken;
        } catch (Exception ex) {
            logEr(TAG, "UserLoginTask.doInBackground: failed to authenticate");
            logMe(TAG, ex.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(final String authToken) {
        // On a successful authentication, call back into the Activity to
        // communicate the authToken (or null for an error).
        onAuthenticationResult(authToken);
    }

    @Override
    protected void onCancelled() {
        // If the action was canceled (by the user clicking the cancel
        // button in the progress dialog), then call back into the
        // activity to let it know.
        onAuthenticationCancel();
    }

    /**
     * Called when the authentication process completes (see attemptLogin()).
     *
     * @param authToken the authentication token returned by the server, or NULL if
     *            authentication failed.
     */
    private void onAuthenticationResult(String authToken) {
        logMe(TAG, "onAuthenticationResult authToken:"+authToken+" START");
//
//        boolean success = ((authToken != null) && (authToken.length() > 0));
//        logMe(TAG, "onAuthenticationResult success:" + success + " mConfirmCredentials:"+mConfirmCredentials);
//
//        // Our task is complete, so clear it out
//        mAuthTask = null;
//
//        // Hide the progress dialog
//        hideProgress();
//
//        if (success) {
//            if (!mConfirmCredentials) {
//                finishLogin(authToken);
//            } else {
//                finishConfirmCredentials(success);
//            }
//        } else {
//            logEr(TAG, "onAuthenticationResult: failed to authenticate");
//            if (mRequestNewAccount) {
//                // "Please enter a valid username/password.
//                mMessage.setText(getText(R.string.login_activity_loginfail_text_both));
//            } else {
//                // "Please enter a valid password." (Used when the
//                // account is already in the database but the password
//                // doesn't work.)
//                mMessage.setText(getText(R.string.login_activity_loginfail_text_pwonly));
//            }
//        }
        logMe(TAG, "onAuthenticationResult END");
    }

    private void onAuthenticationCancel() {
        logMe(TAG, "onAuthenticationCancel()");
//
//        // Our task is complete, so clear it out
//        mAuthTask = null;
//
//        // Hide the progress dialog
//        hideProgress();
    }

	private static void logMe(String tag, String msg) {
		Logger.logMe(tag, msg);
//		//System.out.println(tag+" "+msg);
//        Log.i(tag, msg);
    }

	private static void logEr(String tag, String msg) {
		Logger.logEr(tag, msg);
    }
}
