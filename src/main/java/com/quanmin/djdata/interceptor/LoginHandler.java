package com.quanmin.djdata.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.quanmin.djdata.exception.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: ate
 * @Description: 登录处理
 * @CreateDate: 2019-11-15 10:55
 * @ClassName: com.quanmin.djdata.interceptor.LoginHandler
 */
@Component
public class LoginHandler implements HandlerInterceptor {

    public static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}

    /**
     * @author: ate
     * @description: 输出resut结果集
     * @date: 2019/11/15 10:57
     * @param: [response, result]
     * @return: void
     */
    private void returnResult(HttpServletResponse response, ResultInfo result){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        try{
            // 对象转为json字符串，再转为json
            String toJSONString = JSONObject.toJSONString(result);
            JSONObject jsonObject = JSONObject.parseObject(toJSONString);
            out = response.getWriter();
            response.getWriter().print(jsonObject);
        }
        catch (Exception e){
            logger.error("Result:{}",e);
        }finally {
            out.close();
        }
    }

}
