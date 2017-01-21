package com.naver.openapi.search;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.naver.openapi.ResourceConnector;
import com.naver.openapi.SimpleConnector;
import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

public class SearchApiClientIntegrationTest {

	private static final String API_Key = "daefad88cde80a2cba616e4ad23bdd11"; // 개발자센터에서 받은 키
	
	@Test
	public void newsSearch(){
		
		ResourceConnector connector = new SimpleConnector(1000, 1000);
		SearchApiClient naver = new SearchApiClient(API_Key, connector);
		
		SearchCondition cond = new SearchCondition();
		cond.setQuery("미역국");
		cond.setTarget(SearchCondition.Category.NEWS);
		cond.setDisplay(10);
		cond.setStart(1);
		
		Channel searched = naver.search(cond);
		
		@SuppressWarnings("unchecked")
		List<Item> items = searched.getItems();
		
		assertThat(items.size(), is(10));
		for(Item page : items){
			System.out.printf("제목: %s \n", page.getTitle());
			System.out.printf("주소: %s \n", page.getLink());
			System.out.printf("요약: %s \n", page.getDescription().getValue());
			System.out.println();
		}
	}
}
