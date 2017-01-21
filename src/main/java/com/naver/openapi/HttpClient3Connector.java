package com.naver.openapi;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PreDestroy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * @author benelog
 */
public class HttpClient3Connector implements ResourceConnector {
	private HttpClient httpClient;
	private MultiThreadedHttpConnectionManager connManager;

	public HttpClient3Connector(int connectTimeoutMilsec, int readTimeoutMilsec){
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(connectTimeoutMilsec);
		params.setSoTimeout(readTimeoutMilsec);
		params.setDefaultMaxConnectionsPerHost(20);
		params.setMaxTotalConnections(40);
		
		connManager = new MultiThreadedHttpConnectionManager();
		connManager.setParams(params);
		this.httpClient = new HttpClient(connManager);
	}
	
	@Override
	public InputStream open(String url) throws IOException {
		GetMethod request = new GetMethod(url);
		httpClient.executeMethod(request);
		return request.getResponseBodyAsStream();
	}
	
	@PreDestroy
	public void releaseConnectionPool(){
		connManager.shutdown();
	}
}
