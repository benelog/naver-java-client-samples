package com.naver.openapi.search;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.naver.openapi.ResourceConnector;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.WireFeedInput;
import com.sun.syndication.io.XmlReader;

public class SearchApiClient {
	private static final String OPEN_API_URL = "http://openapi.naver.com/search";
	private String key;
	private ResourceConnector connector;
	private WireFeedInput input = new WireFeedInput();
	private final Logger log = LoggerFactory.getLogger(SearchApiClient.class);
	
	public SearchApiClient(String ApiKey,ResourceConnector connector) {
		this.key = ApiKey;
		this.connector = connector;
	}

	public Channel search(SearchCondition param) {
		String apiUrl = buildSearchUrl(param);
		log.debug("requested api url :{}", apiUrl);
		
		Channel channel = null;
		XmlReader reader = null;
		try {
			InputStream inStream = connector.open(apiUrl);
		   reader = new XmlReader(inStream);
			channel = (Channel) input.build(reader);
		} catch (FeedException e) {
			throw new IllegalStateException("fail to parse api result : " + apiUrl, e);
		} catch (IOException e) {
			throw new IllegalStateException("cannot connect to server : " + apiUrl, e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return channel;
	}
	

	private String buildSearchUrl(SearchCondition param) {

		String encodedQuery = null;
		try {
			encodedQuery = URLEncoder.encode(param.getQuery(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Assert.state(false, "이 곳은 실행되지 않는다. UTF-8을 넘겼으므로 잘못된 인코딩일리 없다");
		}

		StringBuilder request = new StringBuilder(OPEN_API_URL);
		request.append("?target=" + param.getTarget());
		request.append("&key=" + key);
		request.append("&start=" + param.getStart());
		request.append("&display=" + param.getDisplay());
		request.append("&query=" + encodedQuery);
		if (param.getSort() != null) {
			request.append("&sort=" + param.getSort());
		}
		return request.toString();
	}
}
