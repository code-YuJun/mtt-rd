package com.yujun.mtt.util;

import com.yujun.mtt.common.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.io.IOException;

/**
 * Web 工具类，负责 HTTP 请求中的 JSON 和 Java 对象之间的相互转换。
 * 请求（Request）：把前端发送的 JSON 转成 Java 对象。
 * 响应（Response）：把 Java 对象转成 JSON 返回给前端。
 */
public class WebUtil {
    // JSON 翻译器，负责 JSON 和 Java 对象之间的转换
    private static ObjectMapper objectMapper;

    // 初始化objectMapper
    // 程序第一次加载 WebUtil 时：创建一个 ObjectMapper。
    static{
        objectMapper = new ObjectMapper();
        // 设置JSON和Object转换时的时间日期格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request, Class<T> clazz){
        T t =null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            StringBuffer buffer =new StringBuffer();
            String line =null;
            while((line = reader.readLine())!= null){
                buffer.append(line);
            }
            t= objectMapper.readValue(buffer.toString(),clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    // 将Result对象转换成JSON串并放入响应对象
    public static void writeJson(HttpServletResponse response, Result result){
        response.setContentType("application/json;charset=UTF-8");
        try {
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}