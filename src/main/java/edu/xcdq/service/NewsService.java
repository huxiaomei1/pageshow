package edu.xcdq.service;

import edu.xcdq.bean.News;

import java.util.List;

public interface NewsService {

    public List<News> getPageNewsList(int pageNo , int pageSize );

    public int getTotalCount();

    public News getNewsById(int id);

}
