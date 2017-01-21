package com.naver.openapi.shorturl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.naver.openapi.ResourceConnector;
import com.naver.openapi.SimpleConnector;

public class ShortUrlApiClientJaxbImplIntegrationTest {
	@Test
	public void urlShouldBeShorten() {
		//http://openapi.naver.com/shorturl.xml?key=ff70162a8973485bd20da8bb4b6348b9&url=dev.naver.com
		//given
		ResourceConnector connector = new SimpleConnector(1000,1000);
		String apiKey = "ff70162a8973485bd20da8bb4b6348b9";
		ShortUrlApiClientJaxbImpl client = new ShortUrlApiClientJaxbImpl(apiKey, connector);

		//when
		ShortUrlResponse response = client.shorten("http://www.naver.com");
		
		//then
		assertThat(response.getMessage(),is("ok"));
		assertThat(response.getResult().getOrgUrl(),is("http://www.naver.com"));
	}
}
