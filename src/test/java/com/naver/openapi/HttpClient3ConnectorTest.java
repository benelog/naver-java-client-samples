package com.naver.openapi;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;


public class HttpClient3ConnectorTest {
	public static final int TEST_PORT = 19090;
	EmbededTestServer server = new EmbededTestServer(TEST_PORT);

	@Before
	public void setUp() throws Exception {
		server.start();
	}

	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void streamGot() throws InterruptedException, Exception {
		server.reserveResponse("/go", "myContents!!");
		ResourceConnector connector = new HttpClient3Connector(1000, 1000);

		InputStream stream = connector.open("http://localhost:" + TEST_PORT + "/go");

		String content = IOUtils.toString(stream);
		IOUtils.closeQuietly(stream);
		assertThat(content, is("myContents!!"));
	}
}
