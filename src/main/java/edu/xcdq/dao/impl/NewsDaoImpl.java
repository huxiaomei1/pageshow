package edu.xcdq.dao.impl;

import edu.xcdq.bean.News;
import edu.xcdq.dao.BaseDao;
import edu.xcdq.dao.NewsDao;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewsDaoImpl extends BaseDao implements NewsDao {
    @Override
    public List<News> getPageNewsList(int pageNo, int pageSize) {
        ArrayList<News> newsList = new ArrayList<>(); // 准备一个数组存放查询的结果
        String sql = "select * from news_detail order by createDate desc limit ?,?";
        pageNo = (pageNo - 1) * pageSize ;
        Object[] params = { pageNo , pageSize };
        ResultSet rs = executeSQL(sql, params);
        try {
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String summary = rs.getString("summary");
                String content = rs.getString("content");
                String author = rs.getString("author");
                Date createDate = rs.getDate("createDate");

                News resNews = new News();
                resNews.setId(id);
                resNews.setTitle(title);
                resNews.setSummary(summary);
                resNews.setContent(content);
                resNews.setAuthor(author);
                resNews.setCreateDate(createDate);

                newsList.add(resNews); // 将查询到的结果添加到数组中

            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return newsList;
    }

    @Override
    public int getTotalCount() {
        int totalCount = 0;
        String sql = "select count(*) from news_detail";
        Object[] params = {};
        ResultSet rs = executeSQL(sql, params);
        try {
            while (rs.next() ) {
                totalCount=rs.getInt(1);
            }
        }catch (Exception e ) {
            e.printStackTrace();
        }
        return totalCount;
    }

    //通过新闻id获取新闻
    public News getNewsById(int id) {
        News news=null;
        try {
            //（3）获得Statement对象，执行SQL语句
            String sql = "select * from news_detail where id="+id;
            Object[] params={};
            ResultSet rs=this.executeSQL(sql, params);
            //（4）处理执行结果(ResultSet)，
            if(rs.next()){
                int categoryId=rs.getInt("categoryId");
                String title=rs.getString("title");
                String summary=rs.getString("summary");
                String content=rs.getString("content");
                String picPath=rs.getString("picPath");
                String author=rs.getString("author");
                Timestamp time=rs.getTimestamp("createDate");

                //封装成新闻信息对象
                news=new News();
                news.setId(id);
                news.setCategoryId(categoryId);
                news.setTitle(title);
                news.setSummary(summary);
                news.setContent(content);
                news.setPicPath(picPath);
                news.setAuthor(author);
                news.setCreateDate(time);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放资源
            this.closeAll();
        }
        return news;
    }

    @Test
    public void test() {
        List<News> pageNewsList = getPageNewsList(2, 3);
        for(News news : pageNewsList ) {
            System.out.println(news.toString());
        }
    }
}
