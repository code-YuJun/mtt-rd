package com.yujun.mtt.service;

import com.yujun.mtt.pojo.NewsType;

import java.util.List;

/**
 * ClassName: NewsTypeService
 * Package: com.yujun.mtt.service
 */
public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return 多个头条类型 List<NewsType> 集合形式返回
     */
    List<NewsType> findAll();
}