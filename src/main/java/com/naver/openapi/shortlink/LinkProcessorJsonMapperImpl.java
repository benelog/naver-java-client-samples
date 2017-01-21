package com.naver.openapi.shortlink;

import java.io.IOException;
import java.io.InputStream;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.naver.openapi.ResourceConnector;
import com.naver.openapi.shorturl.EncodeUtils;

public class LinkProcessorJsonMapperImpl implements LinkProcessor {
	private String apiKey;
	private ResourceConnector connector;
	private ObjectMapper mapper = new ObjectMapper();
	public LinkProcessorJsonMapperImpl(String apiKey, ResourceConnector connector) {
		this.apiKey = apiKey;
		this.connector = connector;
	}

	@Override
	public Link shorten(String url) {
		String apiUrl = buildApiRequestUrl(url);

		Link parsedResult = new Link();
		try {
			InputStream input = connector.open(apiUrl);
			JsonNode rootNode = mapper.readValue(input, JsonNode.class);
			JsonNode resultNode = rootNode.path("result");
			parsedResult.setOriginalUrl(resultNode.path("orgUrl").getTextValue());
			parsedResult.setShortUrl(resultNode.path("url").getTextValue());
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
