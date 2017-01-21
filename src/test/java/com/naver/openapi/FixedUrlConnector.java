package com.naver.openapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FixedUrlConnector implements ResourceConnector {
	private URL fixed;
	public FixedUrlConnector(URL fixed){
		this.fixed = fixed;
	}
	@Override
	public InputStream open(String url) throws IOException {
		return fixed.openStream();
	}
}
