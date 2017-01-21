package com.naver.openapi.shortlink;

public class Link {
	private String originalUrl;
	private String shortUrl;
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	@Override
	public String toString() {
		return "UrlInfo [originalUrl=" + originalUrl + ", shortUrl=" + shortUrl
				+ "]";
	}
}
