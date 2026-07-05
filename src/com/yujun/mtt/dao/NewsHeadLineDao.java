package com.yujun.mtt.dao;

import com.yujun.mtt.pojo.HeadlineDetailVo;
import com.yujun.mtt.pojo.HeadlinePageVo;
import com.yujun.mtt.pojo.HeadlineQueryVo;
import com.yujun.mtt.pojo.NewsHeadline;

import java.util.List;

/**
 * ClassName: NewsHeadLineDao
 * Package: com.yujun.mtt.dao
 */
public interface NewsHeadLineDao {
    /**
     * 根据查询条件,查询满足条件的记录数
     * @param headLineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headLineQueryVo);

    /**
     * 根据查询条件,查询当前页数据
     * @param headLineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headLineQueryVo);

    /**
     * 多表查询新闻详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(Integer hid);

    /**
     * 新闻浏览量+1更新
     * @param hid
     * @return 受影响行数
     */
    int increasePageViews(Integer hid);
    /**
     * 头条存入数据库
     * @param newsHeadline 新闻实体对象
     * @return 数据库受影响行数
     */
    int addNewsHeadline(NewsHeadline newsHeadline);
    /**
     * 根据新闻hid主键查询完整新闻实体
     * @param hid 新闻主键id
     * @return 新闻完整实体，无数据返回null
     */
    NewsHeadline findHeadlineByHid(Integer hid);

    /**
     * 根据hid更新新闻全部字段
     * @param newsHeadline 封装修改后数据的新闻实体
     * @return 数据库受影响行数，1成功/0失败
     */
    int updateNewsHeadline(NewsHeadline newsHeadline);
    /**
     * 根据hid逻辑删除新闻
     * @param hid 新闻主键
     * @return 受影响行数，1成功/0失败
     */
    int removeByHid(Integer hid);
}
