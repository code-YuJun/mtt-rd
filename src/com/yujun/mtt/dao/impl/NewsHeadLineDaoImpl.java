package com.yujun.mtt.dao.impl;

import com.yujun.mtt.dao.BaseDao;
import com.yujun.mtt.dao.NewsHeadLineDao;
import com.yujun.mtt.pojo.HeadlineDetailVo;
import com.yujun.mtt.pojo.HeadlinePageVo;
import com.yujun.mtt.pojo.HeadlineQueryVo;
import com.yujun.mtt.pojo.NewsHeadline;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassName: NewsHeadLineDaoImpl
 * Package: com.yujun.mtt.dao.impl
 */
public class NewsHeadLineDaoImpl extends BaseDao implements NewsHeadLineDao {
    @Override
    public int findPageCount(HeadlineQueryVo headLineQueryVo) {
        // 拼接动态 SQL，拼接参数
        List<Object> args =new LinkedList<>();
        String sql="select count(1) from news_headline where is_deleted=0";
        StringBuilder sqlBuffer =new StringBuilder(sql);
        String keyWords = headLineQueryVo.getKeyWords();
        //判断并动态拼接关键词条件
        if (null != keyWords && keyWords.length()>0){
            sqlBuffer.append(" and title like ? ");
            args.add("%"+keyWords+"%");
        }
        // 判断并动态拼接新闻类型条件
        Integer type = headLineQueryVo.getType();
        if(null != type && type != 0){
            sqlBuffer.append(" and type = ? ");
            args.add(type);
        }
        // 参数转数组
        Object[] argsArr = args.toArray();
        System.out.println(sqlBuffer.toString());
        Long totalSize = baseQueryObject(Long.class, sqlBuffer.toString(), argsArr);
        // 返回总记录数
        return totalSize.intValue();
    }

    @Override
    public List<HeadlinePageVo> findPageList(HeadlineQueryVo headLineQueryVo) {
        // 拼接动态 SQL，拼接参数
        List<Object> args = new LinkedList<>();
        String sql = "select hid,title,type,page_views pageViews,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,publisher from news_headline where is_deleted=0 ";
        StringBuilder sqlBuffer = new StringBuilder(sql);

        String keyWords = headLineQueryVo.getKeyWords();
        if (null != keyWords && keyWords.length()>0){
            sqlBuffer.append("and title like ? ");
            args.add("%"+keyWords+"%");
        }

        Integer type = headLineQueryVo.getType();
        if(null != type && type != 0){
            sqlBuffer.append("and type = ? ");
            args.add(type);
        }

        // 排序规则：先按发布小时升序，再按浏览量降序
        sqlBuffer.append("order by pastHours , page_views desc ");
        // 分页 LIMIT 偏移量、页大小
        sqlBuffer.append("limit ?,?");
        int offset = (headLineQueryVo.getPageNum()-1)*headLineQueryVo.getPageSize();
        args.add(offset);
        args.add(headLineQueryVo.getPageSize());

        // 参数转数组
        Object[] argsArr = args.toArray();
        System.out.println(sqlBuffer.toString());
        List<HeadlinePageVo> pageData = baseQuery(HeadlinePageVo.class, sqlBuffer.toString(), argsArr);
        return pageData;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        String sql = "select hid,title,article,type, tname typeName ,page_views pageViews,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,publisher,nick_name author from news_headline h left join news_type t on h.type = t.tid left join news_user u on h.publisher = u.uid where hid = ?";
        List<HeadlineDetailVo> headlineDetailVoList = baseQuery(HeadlineDetailVo.class, sql, hid);
        if(null != headlineDetailVoList && headlineDetailVoList.size()>0)
            return headlineDetailVoList.get(0);
        return null;
    }

    @Override
    public int increasePageViews(Integer hid) {
        String sql = "update news_headline set page_views = page_views +1 where hid =?";
        return baseUpdate(sql,hid);
    }
    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = "insert into news_headline values(DEFAULT,?,?,?,?,0,NOW(),NOW(),0)";
        return baseUpdate(
                sql,
                newsHeadline.getTitle(),
                newsHeadline.getArticle(),
                newsHeadline.getType(),
                newsHeadline.getPublisher()
        );
    }
    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        String sql = "select hid,title,article,type,publisher,page_views pageViews from news_headline where hid =?";
        List<NewsHeadline> newsHeadlineList = baseQuery(NewsHeadline.class, sql, hid);
        if(null != newsHeadlineList && newsHeadlineList.size()>0)
            return newsHeadlineList.get(0);
        return null;
    }
    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        String sql = "update news_headline set title = ?, article= ?, type = ?, update_time = NOW() where hid = ? ";
        return baseUpdate(
                sql,
                newsHeadline.getTitle(),
                newsHeadline.getArticle(),
                newsHeadline.getType(),
                newsHeadline.getHid()
        );
    }
    @Override
    public int removeByHid(Integer hid) {
        String sql = "update news_headline set is_deleted =1 , update_time =NOW() where hid = ? ";
        return baseUpdate(sql, hid);
    }
}
