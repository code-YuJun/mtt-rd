package com.yujun.mtt.dao.impl;

import com.yujun.mtt.dao.BaseDao;
import com.yujun.mtt.dao.NewsTypeDao;
import com.yujun.mtt.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsTypeDaoImpl
 * Package: com.yujun.mtt.dao.impl
 */
public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {
    @Override
    public List<NewsType> findAll() {
        return baseQuery(NewsType.class,"select tid,tname from news_type");
    }
}