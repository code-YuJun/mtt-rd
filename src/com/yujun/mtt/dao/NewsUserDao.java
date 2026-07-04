package com.yujun.mtt.dao;

import com.yujun.mtt.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户名查询用户信息
     * @param username 要查询的用户名
     * @return 找到返回NewsUser对象,找不到返回null
     */
    NewsUser findByUserName(String username);

    /**
     * 根据用户id连接数据库查询用户信息
     * @param uid 要查询的用户id
     * @return 找到返回NewsUser对象,找不到返回null
     */
    NewsUser findByUid(Integer uid);
    /**
     * 将用户信息存入数据库
     * @param newsUser
     * @return
     */
    int insertNewsUser(NewsUser newsUser);
}