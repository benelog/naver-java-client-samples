package com.naver.openapi.search;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.naver.openapi.FixedClassPathResourceConnector;
import com.naver.openapi.ResourceConnector;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

public class SearchApiClientTest {
	@Test
	public void newsSearch() throws IOException {
		ResourceConnector connector = new FixedClassPathResourceConnector("searchResult.xml");
		SearchApiClient naver = new SearchApiClient("", connector);

		SearchCondition cond = new SearchCondition();
		cond.setQuery("미역국");
		cond.setTarget(SearchCondition.Category.NEWS);
		cond.setDisplay(10);
		cond.setStart(1);

		Channel searched = naver.search(cond);
		
		@SuppressWarnings("unchecked")
		List<Item> items = searched.getItems();
		
		assertThat(items.size(), is(10));
	}
}
