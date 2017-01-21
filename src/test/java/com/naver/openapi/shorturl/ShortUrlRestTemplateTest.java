package com.naver.openapi.shorturl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class ShortUrlRestTemplateTest {

	RestTemplate client = createRestTemplate();

	private RestTemplate createRestTemplate() {
		SimpleClientHttpRequestFactory reqFactory = new SimpleClientHttpRequestFactory();
		reqFactory.setConnectTimeout(1000);
		reqFactory.setReadTimeout(1000);
		return new RestTemplate(reqFactory);
	}
	
	@Test
	public void testShorten() {
		//given
		Map<String,String> params = new HashMap<String,String>();
		String url = "http://openapi.naver.com/shorturl.xml?key={key}&url={url}";
		params.put("key", "ff70162a8973485bd20da8bb4b6348b9");
		params.put("url", "http://dev.naver.com");
		
		//when
		ShortUrlResponse response = client.getForObject(url, ShortUrlResponse.class, params);
		
		//then
		assertThat(response.getMessage(),is("ok"));
		assertThat(response.getResult().getOrgUrl(),is("http://dev.naver.com"));
	}	
}
