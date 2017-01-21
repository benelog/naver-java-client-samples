package com.naver.openapi.shorturl;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class ShortUrlResponse {
	private String code;
	private UrlInfo result;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public UrlInfo getResult() {
		return result;
	}
	public void setResult(UrlInfo result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ShortUrlResponse [code=" + code + ", result=" + result
				+ ", message=" + message + "]";
	}
}
