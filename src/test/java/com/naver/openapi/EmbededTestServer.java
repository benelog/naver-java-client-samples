package com.naver.openapi;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author benelog
 */
public class EmbededTestServer {
	private final Logger log = LoggerFactory.getLogger(EmbededTestServer.class);
	private Server server;
	private final int port;
	private final ConcurrentMap<String, ReserveredResponse> requestToResponse = new ConcurrentHashMap<String, ReserveredResponse>();
	
	private String DEFAULT_CONTENT_TYPE = "text/html;charset=utf-8";

	public EmbededTestServer(int port) {
		this.port = port;
	}
	
	public void reserveResponse(String requestUri, String content){
		ReserveredResponse response = new ReserveredResponse(content, DEFAULT_CONTENT_TYPE);
		requestToResponse.put(requestUri, response);
	}

	public void reserveResponse(String requestUri, String content, String contentType){
		ReserveredResponse response = new ReserveredResponse(content, contentType);
		requestToResponse.put(requestUri, response);
	}
	
	public void start() {
		server = new Server(port);
		server.setHandler(new ReservedContentHandler());
		try {
			server.start();
		} catch (Exception e) {
			throw new IllegalStateException("fail to start test server" , e);
		}
	}

	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			throw new IllegalStateException("fail to stop test server" , e);
		}
	}

	class ReservedContentHandler extends AbstractHandler {
		@Override
		public void handle(String tagetUri, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			log.info("request:{}", tagetUri);
			ReserveredResponse reserved = requestToResponse.get(tagetUri);
			
			response.setContentType(reserved.getContentType());
			response.setStatus(HttpServletResponse.SC_OK);
			baseRequest.setHandled(true);
			response.getWriter().print(reserved.getContent());
		}
	}
	
	private static class ReserveredResponse {
		public ReserveredResponse(String content, String contentType){
			this.content = content;
			this.contentType = contentType;
		}
		private String contentType;
		private String content;
		public String getContentType() {
			return contentType;
		}
		public String getContent() {
			return content;
		}
	}
}
