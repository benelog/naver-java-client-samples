package com.naver.openapi.shorturl;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.map.ObjectMapper;

import com.naver.openapi.ResourceConnector;

public class ShortUrlApiClientJsonMapperImpl implements ShortUrlApiClient {
	private String apiKey;
	private ResourceConnector connector;
	private ObjectMapper mapper = new ObjectMapper();
	public ShortUrlApiClientJsonMapperImpl(String apiKey, ResourceConnector connector) {
		this.apiKey = apiKey;
		this.connector = connector;
	}

	@Override
	public ShortUrlResponse shorten(String url) {
		ShortUrlResponse parsedResult = null;
		String reqUrl = buildApiRequestUrl(url);
		try {
			InputStream input = connector.open(reqUrl);
			parsedResult = mapper.readValue(input,ShortUrlResponse.class);
		} catch (IOException e) {
			throw new IllegalStateException("fail to parse " + url, e);
		}
		return parsedResult;
	}
	
	private String buildApiRequestUrl(String urlParam){
		StringBuilder reqUrl = new StringBuilder("http://openapi.naver.com/shorturl.json");
		reqUrl.append("?key=" + this.apiKey);
		reqUrl.append("&url=" + EncodeUtils.encodeUtf8(urlParam));
		return reqUrl.toString();
	}
}
