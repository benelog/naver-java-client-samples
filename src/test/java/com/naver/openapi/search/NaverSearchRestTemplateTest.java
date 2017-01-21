package com.naver.openapi.search;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

public class NaverSearchRestTemplateTest {

	// 매번 request때마다 생성할 필요 없음
	RestTemplate client = createRestTemplate();

	private RestTemplate createRestTemplate() {
		SimpleClientHttpRequestFactory reqFactory = new SimpleClientHttpRequestFactory();
		reqFactory.setConnectTimeout(1000);
		reqFactory.setReadTimeout(1000);

		RestTemplate client = new RestTemplate(reqFactory);
		List<HttpMessageConverter<?>> convList = new ArrayList<HttpMessageConverter<?>>();
		convList.add(new NaverSearchHttpMessageConverter());
		client.setMessageConverters(convList);
		return client;
	}
	
	@Test
	public void testSearch() throws UnsupportedEncodingException {
		// given
		Map<String, String> params = new HashMap<String, String>();
		String url = "http://openapi.naver.com/search?key={key}&target={target}&query={query}&start={start}&display={display}";
		params.put("key", "daefad88cde80a2cba616e4ad23bdd11");
		params.put("target", "news");
		params.put("query", "네이버");
		params.put("start", "1");
		params.put("display", "15");

		// when
		Channel channel = client.getForObject(url, Channel.class, params);

		// then
		@SuppressWarnings("unchecked")
		List<Item> items = channel.getItems();
		for (Item page : items) {
			System.out.printf("제목: %s \n", page.getTitle());
			System.out.printf("주소: %s \n", page.getLink());
			System.out.printf("요약: %s \n", page.getDescription().getValue());
			System.out.println();
		}
		System.out.println(items.size());
	}

}
