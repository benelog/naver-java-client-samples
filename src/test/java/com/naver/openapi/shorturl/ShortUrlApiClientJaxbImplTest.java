package com.naver.openapi.shorturl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.naver.openapi.FixedClassPathResourceConnector;
import com.naver.openapi.ResourceConnector;

public class ShortUrlApiClientJaxbImplTest {
	@Test
	public void reponseShouldBeParsed() {
		//http://openapi.naver.com/shorturl.xml?key=ff70162a8973485bd20da8bb4b6348b9&url=dev.naver.com
		//given
		ResourceConnector connector = new FixedClassPathResourceConnector("dev.naver.com.shorturl.xml");

		//when
		ShortUrlApiClientJaxbImpl client = new ShortUrlApiClientJaxbImpl("not real server",connector);
		ShortUrlResponse response = client.shorten("http://dev.naver.com");
		
		//then
		assertThat(response.getMessage(),is("ok"));
		assertThat(response.getResult().getOrgUrl(),is("http://dev.naver.com"));
	}
	
	
	@Test
	public void reponseShouldBeParsedWhenInvalidKey() {
		//given
		ResourceConnector connector = new FixedClassPathResourceConnector("dev.naver.com.shorturl.invalidKey.xml");

		//when
		ShortUrlApiClientJaxbImpl client = new ShortUrlApiClientJaxbImpl("not real server",connector);
		ShortUrlResponse response = client.shorten("http://dev.naver.com");
		
		//then
		assertThat(response.getMessage(),is("Unregistered key (등록되지 않은 키입니다.)"));
	}
}
