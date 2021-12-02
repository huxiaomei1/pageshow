package edu.xcdq.dao;

import edu.xcdq.bean.News;

import java.util.List;

public interface NewsDao {
    public List<News> getPageNewsList(int pageNo , int pageSize );


    int getTotalCount();

    News getNewsById(int id);
}
