package com.naver.openapi;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FixedClassPathResourceConnector implements ResourceConnector {
	private String resourceName;
	public FixedClassPathResourceConnector(String resourceName){
		this.resourceName = resourceName;
	}
	@Override
	public InputStream open(String url) throws IOException {
		URL resource = this.getClass().getClassLoader().getResource(resourceName);
		return resource.openStream();
	}
}
