package com.naver.openapi.shortlink;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.naver.openapi.FixedClassPathResourceConnector;
import com.naver.openapi.ResourceConnector;

public class LinkProcessorJsonStreamImplTest {
	@Test
	public void linkShouldBeParsed() {
		//http://openapi.naver.com/shorturl.json?key=ff70162a8973485bd20da8bb4b6348b9&url=dev.naver.com
		//given
		ResourceConnector connector = new FixedClassPathResourceConnector("dev.naver.com.shorturl.json");
		LinkProcessorJsonStreamImpl client = new LinkProcessorJsonStreamImpl("not real server", connector);

		//when
		Link link = client.shorten("http://dev.naver.com");
		
		//then
		assertThat(link.getOriginalUrl(),is("http://dev.naver.com"));
	}
}
