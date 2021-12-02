<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="edu.xcdq.bean.News"%>
<%@ page import="edu.xcdq.service.impl.NewsServiceImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>My JSP 'adminNewsView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%@include file="common/common.jsp" %>
  <body>
  <%
	String id = request.getParameter("id");
	  NewsServiceImpl newsService = new NewsServiceImpl();
	  News news = newsService.getNewsById(Integer.parseInt(id));
	request.setAttribute("news", news);
  %>
传过来的ID：${param.id}
<div class="main-text-box-tbg">
	<div class="main-text-box-bbg">
		<div class="article-box">
			<h1><c:out value="${news.title }" escapeXml="true" /></h1>
			<div class="source-bar">发布者：<c:out value="${news.author }"/> 分类：新闻信息  更新时间：<c:out value="${news.createDate }"/>" </div>
			<div class="article-content">
				<span class="article-summary"><b>摘要：<c:out value="${news.summary }" default="暂无" /></b></span>
				<% if(news.getPicPath() == null || news.getPicPath().equals("")){ %>
				新闻图片：暂无<br/>
				<%}else{%>
				<img src="<%=request.getContextPath() %>/upload/<%=news.getPicPath() %>"/><br/>
				<%} %>
				<c:out value="${news.content }"/>
			</div>
		</div>
	</div>
</div>
  	
  	
  	<div align="center">
  		<button type="button" class="page-btn" name="return" onclick="javascript:location.href='newsDetailList.jsp'">返回</button>
  	</div>
  </body>
</html>
