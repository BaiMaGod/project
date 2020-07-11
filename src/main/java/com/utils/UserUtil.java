package com.utils;


public class UserUtil {



    /**
     * 将user 信息存入session
     * @param value
     */
    public static void saveUserInfo(Object value){
        HttpUtil.addSession("loginUser",value);

    }

    /**
     * 将user 信息 从 session 中取出
     */
    public static Object getUserInfo(){
        return HttpUtil.getSession("loginUser");
    }

}
