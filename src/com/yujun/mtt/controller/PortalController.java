package com.yujun.mtt.controller;

import com.yujun.mtt.common.Result;
import com.yujun.mtt.pojo.NewsType;
import com.yujun.mtt.service.NewsTypeService;
import com.yujun.mtt.service.impl.NewsTypeServiceImpl;
import com.yujun.mtt.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * ClassName: PortalController
 * Package: com.yujun.mtt.controller
 */

@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private NewsTypeService typeService =  new NewsTypeServiceImpl();
    // 查询所有的新闻类型
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NewsType> newsTypeList = typeService.findAll();
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }
}