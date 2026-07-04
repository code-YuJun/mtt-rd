package com.yujun.mtt.service;

import com.yujun.mtt.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户名,获得查询用户的方法
     * @param username 要查询的用户名
     * @return 如果找到返回NewsUser对象,找不到返回null
     */
    NewsUser findByUserName(String username);

    /**
     * 根据用户id查询用户信息
     * @param uid 要查询的用户id
     * @return 找到返回NewsUser对象,找不到返回null
     */
    NewsUser findByUid(Integer uid);

    /**
     * 注册用户信息,注册成功返回大于0的整数,失败返回0
     * @param newsUser
     * @return
     */
    int registUser(NewsUser newsUser);
}
