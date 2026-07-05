package com.yujun.mtt.controller;

import com.yujun.mtt.common.Result;
import com.yujun.mtt.pojo.NewsHeadline;
import com.yujun.mtt.service.NewsHeadLineService;
import com.yujun.mtt.service.impl.NewsHeadLineServiceImpl;
import com.yujun.mtt.util.JwtHelper;
import com.yujun.mtt.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: NewsHeadlineController
 * Package: com.yujun.mtt.controller
 */
@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController {
    private NewsHeadLineService newsHeadlineService = new NewsHeadLineServiceImpl();
    /**
     * 发布新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取前端传来的新闻JSON，封装实体
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        // 从请求头获取token
        String token = req.getHeader("token");
        // 从token中解析登录用户id
        Long userId = JwtHelper.getUserId(token);
        // 设置发布人id
        newsHeadline.setPublisher(userId.intValue());
        // 调用业务层完成新增
        newsHeadlineService.addNewsHeadline(newsHeadline);
        // 返回成功响应
        WebUtil.writeJson(resp, Result.ok(null));
    }
    /**
     * 修改新闻回显
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端传递新闻主键hid
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        // 根据hid查询完整新闻实体
        NewsHeadline newsHeadline = newsHeadlineService.findHeadlineByHid(hid);
        // 封装返回data
        Map<String ,Object> data =new HashMap<>();
        data.put("headline",newsHeadline);
        // 返回统一JSON
        WebUtil.writeJson(resp, Result.ok(data));
    }
    /**
     * 更新新闻信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取前端提交的完整新闻JSON数据
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
        // 调用业务层执行更新
        newsHeadlineService.updateNewsHeadline(newsHeadline);
        // 返回成功响应
        WebUtil.writeJson(resp, Result.ok(null));
    }
    /**
     * 删除新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void removeByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数hid并转为整型
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        // 调用业务层执行删除（逻辑删除）
        newsHeadlineService.removeByHid(hid);
        // 返回统一成功响应
        WebUtil.writeJson(resp, Result.ok(null));
    }
}