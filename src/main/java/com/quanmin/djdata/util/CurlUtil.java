package com.quanmin.djdata.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * @Author: ate
 * @Description: Curl调用工具类
 * @CreateDate: 2019-11-08 18:59
 * @ClassName: com.quanmin.djdata.util.CurlUtil
 */
@Component
public class CurlUtil {

    /** 系统类型 */
    private static String osName = System.getProperty("os.name").toLowerCase();

    private static Logger logger = LoggerFactory.getLogger(CurlUtil.class);

    /**
     * @author: dameizi
     * @dateTime: 2019-03-28 23:57
     * @description: GET请求调用接口 返回json
     * @param: [param, url]
     * @return: org.json.JSONObject
     */
    public static JSONObject readGETJson(String header1, String param, String url) throws Exception{
        long start = System.currentTimeMillis();
        ProcessBuilder pb;
        if (StringUtils.isEmpty(header1)) {
            // 没有头部信息请求
            pb = new ProcessBuilder(getCmdsGETJson(param,url));
        } else {
            // 加头部信息请求
            pb = new ProcessBuilder(getCmdsGETJsonHeader(header1,param,url));
        }
        pb.redirectErrorStream(true);
        BufferedInputStream bufferedRead =null;
        return getJsonObject(pb, bufferedRead);
    }

    /**
     * @author: dameizi
     * @dateTime: 2019-03-28 23:58
     * @description: 解析调用结果返回
     * @param: [pb, bufferedRead]
     * @return: org.json.JSONObject
     */
    private static JSONObject getJsonObject(ProcessBuilder pb, BufferedInputStream bufferedRead) throws IOException, InterruptedException, JSONException {
        Process p;
        String result =null;
        try {
            p = pb.start();
            bufferedRead = new BufferedInputStream(p.getInputStream());
            result = IOUtils.toString(bufferedRead, "utf-8");
            p.waitFor();
            p.destroy();
            logger.info("获取的结果:{}", result);
            if(null != result && !("".equals(result))) {
                return JSONObject.parseObject(result);
            }else{
                return null;
            }
        }catch (JSONException e){
            logger.error("错误的报文:{}", result);
            throw e;
        }finally {
            if (null != bufferedRead) {
                bufferedRead.close();
            }
        }
    }

    /**
     * @author: dameizi
     * @dateTime: 2019-03-28 23:59
     * @description: GET请求
     * @param: [param, url]
     * @return: java.lang.String[]
     */
    public static String[] getCmdsGETJson(String param,String url){
        String cmd = "curl -s -L {0} {1}"; // 只包含结果捷信
        //String cmd = "curl -X GET {0} {1}"; // 包含全部信息
        String cmdTemp = MessageFormat.format(cmd,param,url);
        logger.info("getCmdsGetJson cmdTemp ==> " + cmdTemp);
        return getOsCmd(cmdTemp);
    }

    public static String[] getCmdsGETJsonHeader(String header1,String param,String url){
        String cmd = "curl -s -L -H {0} {1} {2}";
        String cmdTemp = MessageFormat.format(cmd,header1,param,url);
        logger.info("getCmdsGetJson cmdTemp ==> " + cmdTemp);
        return getOsCmd(cmdTemp);
    }

    /***
     * CURL -s -d 形式请求
     * @param param
     * @param url
     * @return
     */
    public static String[] getCmdsGET(String param,String url){
        String cmd = "curl -s -d {0} {1}";
        param ="\""+param+"\"";
        url = "\""+url+"\"";
        String cmdTemp = MessageFormat.format(cmd,param,url);
        logger.info("getCmdsGET cmdTemp ==> " + cmdTemp);
        return getOsCmd(cmdTemp);
    }

    /**
     * @author: ate
     * @description: 根据操作系统获取命令前缀
     * @date: 2019/11/11 18:48
     * @param: [cmdTemp]
     * @return: java.lang.String[]
     */
    public static String[] getOsCmd(String cmdTemp) {
        if(osName.contains("windows")){
            String[] cmds = {"cmd", "/c", cmdTemp};
            return cmds;
        }else{
            return partitionCommandLine(cmdTemp);
        }
    }

    public static String[] partitionCommandLine(final String command) {
        final ArrayList<String> commands = new ArrayList<String>();
        int index = 0;
        StringBuffer buffer = new StringBuffer(command.length());
        boolean isApos = false;
        boolean isQuote = false;
        while (index < command.length()) {
            final char c = command.charAt(index);
            switch (c) {
                case ' ':
                    if (!isQuote && !isApos) {
                        final String arg = buffer.toString();
                        buffer = new StringBuffer(command.length() - index);
                        if (arg.length() > 0) {
                            commands.add(arg);
                        }
                    } else {
                        buffer.append(c);
                    }
                    break;
                case '\'':
                    if (!isQuote) {
                        isApos = !isApos;
                    } else {
                        buffer.append(c);
                    }
                    break;
                case '"':
                    if (!isApos) {
                        isQuote = !isQuote;
                    } else {
                        buffer.append(c);
                    }
                    break;
                default:
                    buffer.append(c);
            }
            index++;
        }
        if (buffer.length() > 0) {
            final String arg = buffer.toString();
            commands.add(arg);
        }
        return commands.toArray(new String[commands.size()]);
    }

}
