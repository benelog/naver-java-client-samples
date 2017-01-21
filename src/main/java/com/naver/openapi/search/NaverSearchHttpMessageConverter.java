package com.naver.openapi.search;

import org.springframework.http.MediaType;
import org.springframework.http.converter.feed.AbstractWireFeedHttpMessageConverter;

import com.sun.syndication.feed.rss.Channel;

public class NaverSearchHttpMessageConverter extends AbstractWireFeedHttpMessageConverter<Channel> {

	public NaverSearchHttpMessageConverter() {
		super(new MediaType("text", "xml"));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Channel.class.isAssignableFrom(clazz);
	}
}
