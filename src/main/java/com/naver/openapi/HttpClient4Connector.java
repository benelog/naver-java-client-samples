package com.naver.openapi;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PreDestroy;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.CoreConnectionPNames;

/**
 * @author benelog
 */
public class HttpClient4Connector implements ResourceConnector {
	private HttpClient httpClient;
	private ThreadSafeClientConnManager connManager;

	public HttpClient4Connector(int connectTimeoutMilsec, int readTimeoutMilsec){
		connManager = new ThreadSafeClientConnManager();
		connManager.setMaxTotal(40);
		connManager.setDefaultMaxPerRoute(20);
		
		HttpClient client = new DefaultHttpClient(connManager);
		client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectTimeoutMilsec);
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, readTimeoutMilsec);

		this.httpClient = client;
	}
	
	@Override
	public InputStream open(String url) throws IOException {
		HttpGet request = new HttpGet(url);
		HttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		return entity.getContent();
	}
	
	@PreDestroy
	public void releaseConnectionPool(){
		connManager.shutdown();
	}
}
