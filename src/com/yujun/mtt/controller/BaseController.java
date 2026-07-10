package com.yujun.mtt.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 根据浏览器请求的 URL，自动找到对应的方法并执行。
 * 子类不需要写 service 方法
 */
public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 告诉浏览器返回 JSON、指定编码为 UTF-8
        resp.setContentType("application/json;charset=UTF-8");
        // 获取请求路径，没有域名没有端口
        String requestURI = req.getRequestURI();
        // 按 / 分割，获取最后一个元素，就是方法名
        String[] split = requestURI.split("/");
        String methodName = split[split.length-1];
        // 通过反射获取要执行的方法
        Class clazz = this.getClass(); // 获取当前对象的 Class，如果 public class UserController extends BaseController{}, 则 clazz 为 UserController.class
        try {
            // 如果 method 是 login，就是去 UserController 中找 login(HttpServletRequest,HttpServletResponse)
            Method method = clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            // 设置方法可以访问，因为默认是 private
            method.setAccessible(true);
            // 通过反射执行代码
            method.invoke(this,req,resp); // 相当于 login(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
/**
 * 整个执行过程如下：
 * 浏览器
 *    │
 *    ▼
 * Tomcat
 *    │
 *    ▼
 * BaseController.service()
 *    │
 *    ▼
 * req.getRequestURI()
 *    │
 *    ▼
 * "/user/login"
 *    │
 *    ▼
 * split("/")
 *    │
 *    ▼
 * ["","user","login"]
 *    │
 *    ▼
 * methodName="login"
 *    │
 *    ▼
 * 反射获取 login 方法
 *    │
 *    ▼
 * method.invoke(this, req, resp)
 *    │
 *    ▼
 * UserController.login(req, resp)
 */