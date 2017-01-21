package com.naver.openapi.shortlink;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import com.naver.openapi.ResourceConnector;
import com.naver.openapi.shorturl.EncodeUtils;

public class LinkProcessorJsonStreamImpl implements	LinkProcessor {
	private String apiKey;
	private ResourceConnector connector;
	private JsonFactory factory = new JsonFactory();

	public LinkProcessorJsonStreamImpl(String apiKey, ResourceConnector connector) {
		this.apiKey = apiKey;
		this.connector = connector;
	}

	public Link shorten(String url) {
		Link parsedResult = new Link();
		JsonParser parser = null;
		String apiUrl = buildApiRequestUrl(url);
		try {
			InputStream input = connector.open(apiUrl);
			parser = factory.createJsonParser(input);
			while (parser.nextToken() != JsonToken.END_OBJECT) {
				String name = parser.getCurrentName();
				if ("url".equals(name)) {
					parsedResult.setShortUrl(parser.getText());
				} else if ("orgUrl".equals(name)) {
					parsedResult.setOriginalUrl(parser.getText());
				}
			}
			parser.nextToken();
		} catch (IOException e) {
			throw new IllegalStateException("fail to parse : " + url, e);
		} finally {
			if (parser != null) {
				try {
					parser.close();
				} catch (IOException e) {
					// ignore
				}
			}
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
