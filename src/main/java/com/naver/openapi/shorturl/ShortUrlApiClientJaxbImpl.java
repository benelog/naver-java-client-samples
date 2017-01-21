package com.naver.openapi.shorturl;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.naver.openapi.ResourceConnector;

public class ShortUrlApiClientJaxbImpl implements ShortUrlApiClient {
	private String apiKey;
	private ResourceConnector connector;
	private JAXBContext jaxbContext;

	public ShortUrlApiClientJaxbImpl(String apiKey, ResourceConnector connector) {
		this.apiKey = apiKey;
		this.connector = connector;
		try {
			this.jaxbContext = JAXBContext.newInstance(ShortUrlResponse.class);
		} catch (JAXBException e) {
			throw new IllegalStateException("fail to initialize jaxb context",e);
		}
	}

	public ShortUrlResponse shorten(String url) {
		ShortUrlResponse parsedResult = null;
		String reqUrl = buildApiRequestUrl(url);
		try {
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			InputStream input = connector.open(reqUrl);
			StreamSource source = new StreamSource(input);
			parsedResult = (ShortUrlResponse) unmarshaller.unmarshal(source);
		} catch (JAXBException e) {
			throw new IllegalStateException("fail to create unmarshaller",e);
		} catch (IOException e) {
			throw new IllegalStateException("fail to initialize jaxb context",e);
		}
		return parsedResult;
	}
	
	private String buildApiRequestUrl(String urlParam){
		StringBuilder reqUrl = new StringBuilder("http://openapi.naver.com/shorturl.xml");
		reqUrl.append("?key=" + this.apiKey);
		reqUrl.append("&url=" + EncodeUtils.encodeUtf8(urlParam));
		return reqUrl.toString();
	}
}
