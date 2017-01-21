package com.naver.openapi;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @author benelog
 */
public class SimpleConnectorTest {

	ConcurrentMap<String, String> reponses = new ConcurrentHashMap<String, String>();
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
		SimpleConnector connector = new SimpleConnector(1000, 1000);

		InputStream stream = connector.open("http://localhost:" + TEST_PORT + "/go");

		String content = IOUtils.toString(stream);
		IOUtils.closeQuietly(stream);
		assertThat(content, is("myContents!!"));
	}
}
