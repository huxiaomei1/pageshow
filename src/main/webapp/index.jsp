<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/common.css"/>
<%@ page import="edu.xcdq.dao.impl.NewsDaoImpl" %>
<%@ page import="edu.xcdq.service.NewsService" %>
<%@ page import="edu.xcdq.service.impl.NewsServiceImpl" %>
<%@ page import="edu.xcdq.bean.News" %>
<%@ page import="java.util.List" %>
<%@ page import="edu.xcdq.util.PageSupport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
    <table>
        <thead >
        <tr class="admin-list-head">
            <th>新闻标题</th>
            <th>新闻内容</th>
            <th>作者</th>
            <th>时间</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>
        <%
            //获取当前页码
            String currntPage=request.getParameter("pageIndex");
            if(currntPage==null)
                currntPage="1";
            int pageIndex=Integer.parseInt(currntPage);
            //获取新闻记录总数量
            NewsServiceImpl newsService = new NewsServiceImpl();
            int totalCount=newsService.getTotalCount();
            //每页显示记录数
            int pageSize=3;
            /*获取总页数*/
            PageSupport pages=new PageSupport();
            pages.setCurrentPageNo(pageIndex);
            pages.setPageSize(pageSize);
            pages.setTotalCount(totalCount);
            int totalPage=pages.getTotalPageCount();

            //控制首页和末页
            if(pageIndex<1)
                pageIndex=1;
            else if(pageIndex>totalPage)
                pageIndex=totalPage;

            //每页显示的新闻列表
            List<News> newsList=newsService.getPageNewsList(pageIndex, pageSize);
            session.setAttribute("newsList" , newsList);
            System.out.println(newsList.get(1).getCreateDate());
            /*int i=0;
            for(News news:newsList){
                i++;*/
        %>
        <c:forEach var="news" items="${newsList}"  varStatus="i"  >
            <tr <c:if test="${i.count%2==0 }" > class="admin-list-td-h2" </c:if>  >
                <td> ${news.id}   ${news.title } </td>
                <td> ${news.content} </td>
                <td> ${news.author} </td>
<%--                <td> ${news.createDate } </td>--%>
                <td>
                    <fmt:formatDate value="${news.createDate }" pattern="yyyy_MM_dd" />
                </td>
                <td>
                    <a href='
                            <c:url value="newsDetailView.jsp">
                                <c:param name="id" value="${news.id }"></c:param>
                            </c:url>
                                '>  修改
                    </a>
                        <a href="javascript:if(confirm('确认是否删除此新闻？')) location='adminNewsDel.jsp?id=2'">删除</a>
                </td>
            </tr>
        </c:forEach>


        <%--<tr <%if(i%2==0){ %>class="admin-list-td-h2"<%} %>  >
            <td> ${newsList[i].id}   ${newsList[i].title } </td>
            <td><%=news.getContent() %></td>
            <td><%=news.getAuthor() %></td>
            <td><%=news.getCreateDate() %></td>
            <td><a href=''>修改</a>
                <a href="javascript:if(confirm('确认是否删除此新闻？')) location='adminNewsDel.jsp?id=2'">删除</a>
            </td>
        </tr>
        <%
            }
        %>--%>

        </tbody>
    </table>

    <div class="page-bar">
        <ul class="page-num-ul clearfix">
            <li>共<%=totalCount %>条记录&nbsp;&nbsp; <%=pageIndex %>/<%=totalPage %>页</li>
            <%
                if(pageIndex>1){
            %>
            <a href="index.jsp?pageIndex=1">首页</a>
            <a href="index.jsp?pageIndex=<%=pageIndex-1%>">上一页</a>
            <% }
                if(pageIndex<totalPage){ %>
            <a href="index.jsp?pageIndex=<%=pageIndex+1%>">下一页</a>
            <a href="index.jsp?pageIndex=<%=totalPage%>">最后一页</a>
            <%} %>&nbsp;&nbsp;
        </ul>
        <span class="page-go-form"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" onClick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button>
		</span>
    </div>






</body>
</html>
