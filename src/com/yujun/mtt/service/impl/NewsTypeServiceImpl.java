package com.yujun.mtt.service.impl;

import com.yujun.mtt.dao.NewsTypeDao;
import com.yujun.mtt.dao.impl.NewsTypeDaoImpl;
import com.yujun.mtt.pojo.NewsType;
import com.yujun.mtt.service.NewsTypeService;

import java.util.List;

/**
 * ClassName: NewsTypeServiceImpl
 * Package: com.yujun.mtt.service.impl
 */
public class NewsTypeServiceImpl implements NewsTypeService {
    private NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}