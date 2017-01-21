package com.naver.openapi;

import java.io.IOException;
import java.io.InputStream;

public interface ResourceConnector {
	public InputStream open(String url) throws IOException;
}
