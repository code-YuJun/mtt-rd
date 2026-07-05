package com.yujun.mtt.service;

import com.yujun.mtt.pojo.HeadlineDetailVo;
import com.yujun.mtt.pojo.HeadlineQueryVo;
import com.yujun.mtt.pojo.NewsHeadline;

import java.util.Map;

/**
 * ClassName: NewsHeadLineService
 * Package: com.yujun.mtt.service
 */
public interface NewsHeadLineService {
    /**
     * 分页查询头条新闻方法
     * @param headLineQueryVo
     * @return
     */
    Map<String, Object> findPage(HeadlineQueryVo headLineQueryVo);
    /**
     * 根据头条id,显示头条详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(Integer hid);
    /**
     * 新增头条
     * @param newsHeadline
     * @return 数据库受影响行数
     */
    int addNewsHeadline(NewsHeadline newsHeadline);
    /**
     * 根据新闻id,查询单个新闻
     * @param hid 新闻主键
     * @return 完整新闻实体
     */
    NewsHeadline findHeadlineByHid(Integer hid);
    /**
     * 更新新闻信息
     * @param newsHeadline 待更新新闻实体
     * @return 数据库受影响行数
     */
    int updateNewsHeadline(NewsHeadline newsHeadline);
    /**
     * 根据hid逻辑删除新闻
     * @param hid 新闻主键
     * @return 数据库受影响行数，1删除成功/0失败
     */
    int removeByHid(Integer hid);
}
