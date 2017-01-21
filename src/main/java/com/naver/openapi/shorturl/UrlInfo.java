package com.naver.openapi.shorturl;

public class UrlInfo {
	private String hash;
	private String orgUrl;
	private String url;
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Result [hash=" + hash + ", orgUrl=" + orgUrl + ", url=" + url
				+ "]";
	}
}
