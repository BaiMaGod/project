package com.utils;


import com.alibaba.fastjson.JSONObject;
import com.common.result.Result;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HttpUtil {

    /**
     * http返回json
     * @param response
     * @param result
     */
    public static void returnJson(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            String jsonObject = JSONObject.toJSONString(result);
            writer.print(jsonObject);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null)
                writer.close();
        }
    }



    /**
     * 添加内容到session
     * @param key
     * @param value
     */
    public static void addSession(String key,Object value){
        getHttpSession().setAttribute(key,value);
    }

    /**
     * 从session中获取内容
     * @param key
     */
    public static Object getSession(String key){
        return getHttpSession().getAttribute(key);
    }

    /**
     * 获取当前请求的 HttpSession
     * @return
     */
    public static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    /**
     * 获取当前请求的 HttpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前请求的 url
     * @return
     */
    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    /**
     * 获取当前请求的 Origin
     * @return
     */
    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }
}
