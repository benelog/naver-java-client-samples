package com.naver.openapi.shorturl;

public interface ShortUrlApiClient {
	ShortUrlResponse shorten(String url);
}