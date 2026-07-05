package com.yujun.mtt.service.impl;

import com.yujun.mtt.dao.NewsHeadLineDao;
import com.yujun.mtt.dao.impl.NewsHeadLineDaoImpl;
import com.yujun.mtt.pojo.HeadlineDetailVo;
import com.yujun.mtt.pojo.HeadlinePageVo;
import com.yujun.mtt.pojo.HeadlineQueryVo;
import com.yujun.mtt.pojo.NewsHeadline;
import com.yujun.mtt.service.NewsHeadLineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: NewsHeadLineServiceImpl
 * Package: com.yujun.mtt.service.impl
 */
public class NewsHeadLineServiceImpl implements NewsHeadLineService {
    private NewsHeadLineDao newsHeadLineDao = new NewsHeadLineDaoImpl();
    @Override
    public Map<String, Object> findPage(HeadlineQueryVo headLineQueryVo) {
        // 准备一个map,用于装分页的五项数据
        Map<String, Object> pageInfo = new HashMap<>();
        // 分页查询本页数据
        List<HeadlinePageVo> pageData = newsHeadLineDao.findPageList(headLineQueryVo);
        // 分页查询满足记录的总数据量
        int totalSize = newsHeadLineDao.findPageCount(headLineQueryVo);
        // 页大小
        int pageSize = headLineQueryVo.getPageSize();
        // 总页码数
        int totalPage=totalSize%pageSize == 0 ? totalSize/pageSize : totalSize/pageSize+1;
        // 当前页码数
        int pageNum= headLineQueryVo.getPageNum();

        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);
        return pageInfo;
    }
    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        // 修改新闻信息浏览量+1
        newsHeadLineDao.increasePageViews(hid);
        // 查询新闻详情
        return newsHeadLineDao.findHeadlineDetail(hid);
    }
    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadLineDao.addNewsHeadline(newsHeadline);
    }
    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        return newsHeadLineDao.findHeadlineByHid(hid);
    }
    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        // 直接转发调用DAO层更新方法，返回数据库受影响行数
        return newsHeadLineDao.updateNewsHeadline(newsHeadline);
    }
    @Override
    public int removeByHid(Integer hid) {
        return newsHeadLineDao.removeByHid(hid);
    }
}