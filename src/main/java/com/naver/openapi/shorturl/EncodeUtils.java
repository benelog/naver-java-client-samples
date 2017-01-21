package com.naver.openapi.shorturl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeUtils {

	public static String encodeUtf8(String originalStr) {
		try {
			return URLEncoder.encode(originalStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
}
