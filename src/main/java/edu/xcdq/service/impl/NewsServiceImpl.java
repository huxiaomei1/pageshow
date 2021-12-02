package edu.xcdq.service.impl;

import edu.xcdq.bean.News;
import edu.xcdq.dao.NewsDao;
import edu.xcdq.dao.impl.NewsDaoImpl;
import edu.xcdq.service.NewsService;

import java.util.List;

public class NewsServiceImpl implements NewsService {
    private NewsDao newsDao = new NewsDaoImpl();

    @Override
    public List<News> getPageNewsList(int pageNo, int pageSize) {

        return newsDao.getPageNewsList(pageNo ,pageSize) ;
    }

    @Override
    public int getTotalCount() {
        return newsDao.getTotalCount();
    }

    @Override
    public News getNewsById(int id) {
        // TODO Auto-generated method stub
        return newsDao.getNewsById(id);
    }
}
