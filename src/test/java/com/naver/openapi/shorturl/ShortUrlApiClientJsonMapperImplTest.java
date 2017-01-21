package com.naver.openapi.shorturl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.naver.openapi.FixedClassPathResourceConnector;
import com.naver.openapi.ResourceConnector;

public class ShortUrlApiClientJsonMapperImplTest {
	@Test
	public void testShorten3() {
		//given
		//http://openapi.naver.com/shorturl.json?key=ff70162a8973485bd20da8bb4b6348b9&url=dev.naver.com
		ResourceConnector connector = new FixedClassPathResourceConnector("dev.naver.com.shorturl.json");
		ShortUrlApiClient client = new ShortUrlApiClientJsonMapperImpl("not real server",connector);

		//when
		ShortUrlResponse response = client.shorten("http://dev.naver.com");
		
		assertThat(response.getMessage(),is("ok"));
		assertThat(response.getCode(),is("200"));
		assertThat(response.getResult().getOrgUrl(),is("http://dev.naver.com"));
		assertThat(response.getResult().getUrl(),is("http://me2.do/5Fi4HHW"));
	}
}
