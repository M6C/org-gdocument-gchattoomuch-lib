package org.gdocument.gchattoomuch.p2p.common;

import com.cameleon.common.tool.DeviceTool;

public class P2PConstant {

	@SuppressWarnings("unused")
	private final static String P2P_LOCALHOST = "127.0.0.1";
	private final static String P2P_MASK = "192.168";

	private final static String P2P_SERVER_MAC_ADDRESS = "48:5A:3F:67:03:9B";
//	private final static String P2P_SERVER_HOST = "192.168.0.37"; // S3
	private final static String P2P_SERVER_HOST = "192.168.0.39"; // Note 4
	private final static int P2P_SERVER_PORT = 7101;

	private final static String P2P_CLIENT_HOST = "192.168.0.29";
	private final static int P2P_CLIENT_PORT = 7201;

	public final static int P2P_UPLOAD_TIMEOUT = 60000; //In milliseconds (0 Infinit) 
	public final static int P2P_DOWNLOAD_TIMEOUT = 0; //5 * 60000; //In milliseconds (0 Infinit) 

	private P2PConstant() {
	}

	public static String getHost() {
		String address = DeviceTool.getIPAddress(true);
		if (address != null && (address.equals(P2P_SERVER_HOST) || !address.startsWith(P2P_MASK))) {
			return P2P_LOCALHOST;
		} else {
			return P2P_SERVER_HOST;
		}
	}

	public static String getHostClient() {
		String address = DeviceTool.getIPAddress(true);
		if (address != null && (address.equals(P2P_SERVER_HOST) || !address.startsWith(P2P_MASK))) {
			return P2P_LOCALHOST;
		} else {
			return P2P_CLIENT_HOST;
		}
	}

	public static int getPort() {
		return P2P_SERVER_PORT;
	}

	public static int getPortClient() {
		return P2P_CLIENT_PORT;
	}
}