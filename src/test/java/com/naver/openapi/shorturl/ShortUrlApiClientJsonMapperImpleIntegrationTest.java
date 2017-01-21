package com.naver.openapi.shorturl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.naver.openapi.ResourceConnector;
import com.naver.openapi.SimpleConnector;

public class ShortUrlApiClientJsonMapperImpleIntegrationTest {
	@Test
	public void urlShouldBeShorten() {

		//given
		String originalUrl = "http://openapi.naver.com/map/getStaticMap?version=1.0&crs=EPSG:4326" +
				"&center=127.1141382,37.3599968&markers=127.1141382,37.3599968" +
				"&level=11&w=640&h=640&exception=inimage" +
				"&key=ff70162a8973485bd20da8bb4b6348b9&uri=localhost:8080";
		ResourceConnector connector = new SimpleConnector(1000,1000);
		String apiKey = "ff70162a8973485bd20da8bb4b6348b9";
		ShortUrlApiClientJsonMapperImpl client = new ShortUrlApiClientJsonMapperImpl(apiKey, connector);

		//when
		ShortUrlResponse response = client.shorten(originalUrl);
		
		//then
		assertThat(response.getMessage(),is("ok"));
		assertThat(response.getResult().getOrgUrl(),is(originalUrl));
	}
}
