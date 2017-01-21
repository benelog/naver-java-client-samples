<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.naver.openapi.search.SearchCondition" %>
<%@ page import="com.naver.openapi.search.SearchCondition" %>
<%@ page import="com.naver.openapi.search.SearchApiClient" %>
<%
/* 	String KEY = "??"; // API key를 넣으세요 
	UrlConnector connector = new SimpleUrlConnector(1000, 1000);
	NaverSearchApiClient naver = new NaverSearchApiClient(KEY, connector);
	SearchCondition cond = new SearchCondition();
	cond.setDisplay(10);
	cond.setStart(1);
	cond.setQuery("미역국");
	cond.setTarget(SearchCondition.Category.NEWS);
	request.setAttribute("searched", naver.search(cond)); */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Naver Open API를 이용한 검색</title>
</head>
<body>
<c:forEach var='page' items='${searched.entries}'>
  <p>
  <a href="${page.link}"> ${page.title} </a> <br/>
  ${item.description.value}
  </p>
</c:forEach> 
</body>
</html>