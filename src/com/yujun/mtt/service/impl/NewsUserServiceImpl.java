package com.yujun.mtt.service.impl;

import com.yujun.mtt.dao.NewsUserDao;
import com.yujun.mtt.dao.impl.NewsUserDaoImpl;
import com.yujun.mtt.pojo.NewsUser;
import com.yujun.mtt.service.NewsUserService;
import com.yujun.mtt.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
    private NewsUserDao newsUserDao = new NewsUserDaoImpl();

    @Override
    public NewsUser findByUserName(String username) {
        return newsUserDao.findByUserName(username);
    }

    @Override
    public NewsUser findByUid(Integer uid) {
        return newsUserDao.findByUid(uid);
    }

    @Override
    public int registUser(NewsUser newsUser) {
        // 密码明文转密文
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        // 存入数据库
        return newsUserDao.insertNewsUser(newsUser);
    }
}