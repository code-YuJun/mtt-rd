package com.yujun.mtt.controller;

import com.yujun.mtt.common.Result;
import com.yujun.mtt.pojo.HeadlineDetailVo;
import com.yujun.mtt.pojo.HeadlineQueryVo;
import com.yujun.mtt.pojo.NewsType;
import com.yujun.mtt.service.NewsHeadLineService;
import com.yujun.mtt.service.NewsTypeService;
import com.yujun.mtt.service.impl.NewsHeadLineServiceImpl;
import com.yujun.mtt.service.impl.NewsTypeServiceImpl;
import com.yujun.mtt.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: PortalController
 * Package: com.yujun.mtt.controller
 */

@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private NewsTypeService newsTypeService = new NewsTypeServiceImpl();
    private NewsHeadLineService headlineService = new NewsHeadLineServiceImpl();
    /**
     * 查询所有新闻类型
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList = newsTypeService.findAll();
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }

    /**
     * 分页带条件查询新闻
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 读取前端请求体JSON，封装为查询VO对象
        HeadlineQueryVo headLineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        // 调用业务层执行分页条件查询，获取分页数据与总条数
        Map<String,Object> pageInfo = headlineService.findPage(headLineQueryVo);
        // 封装一层Map，统一返回key
        Map<String,Object> pageInfoMap=new HashMap<>();
        pageInfoMap.put("pageInfo",pageInfo);
        // 封装统一响应并返回JSON
        WebUtil.writeJson(resp, Result.ok(pageInfoMap));
    }

    /**
     * 查询单个新闻详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取要查询的详情新闻id
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        // 查询新闻详情vo
        HeadlineDetailVo headlineDetailVo = headlineService.findHeadlineDetail(hid);
        // 封装data内容
        Map<String ,Object> data =new HashMap<>();
        data.put("headline",headlineDetailVo);
        // 响应JSON
        WebUtil.writeJson(resp, Result.ok(data));
    }
}