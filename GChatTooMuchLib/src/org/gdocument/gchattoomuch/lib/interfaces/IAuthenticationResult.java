package org.gdocument.gchattoomuch.lib.interfaces;


public interface IAuthenticationResult {
	public void onAuthenticationFinish(String authToken);
	public void onAuthenticationResult(String authToken);
	public void onAuthenticationCancel();
}
