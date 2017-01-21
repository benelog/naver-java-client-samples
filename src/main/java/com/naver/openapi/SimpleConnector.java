package com.naver.openapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author benelog
 */
public class SimpleConnector implements ResourceConnector {

	private int connectTimeoutMilsec;
	private int readTimeoutMilsec;

	public SimpleConnector(int connectTimeoutMilsec, int readTimeoutMilsec){
		this.connectTimeoutMilsec = connectTimeoutMilsec;
		this.readTimeoutMilsec = readTimeoutMilsec;
	}
	
	@Override
	public InputStream open(String url) throws IOException {
		URLConnection con = new URL(url).openConnection();
		con.setConnectTimeout(this.connectTimeoutMilsec);
		con.setReadTimeout(this.readTimeoutMilsec);
		return con.getInputStream();
	}
}
