package com.yujun.mtt.dao;

import com.yujun.mtt.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsTypeDao
 * Package: com.yujun.mtt.dao
 */
public interface NewsTypeDao {
    List<NewsType> findAll();
}
