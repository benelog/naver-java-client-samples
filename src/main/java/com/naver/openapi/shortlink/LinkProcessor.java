package com.naver.openapi.shortlink;


public interface LinkProcessor {
	Link shorten(String url);
}