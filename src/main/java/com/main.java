package com;


import org.json.JSONObject;
import utils.HttpUtil;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2018/4/2.
 */
public class main {

    Logger logger = Logger.getLogger(main.class.toString());
    public static void main(String[] args){
        //请求地址
        String url="http://intapi.253.com/send/json";

        //API账号，50位以内。必填
        String account="I12345678";

        //API账号对应密钥，联系客服获取。必填
        String password="******";

        //短信内容。长度不能超过536个字符。必填
        String msg="【253】您的验证码是：2530";

        //手机号码，格式(区号+手机号码)，例如：8615800000000，其中86为中国的区号，区号前不使用00开头,15800000000为接收短信的真实手机号码。5-20位。必填
        String mobile="8618721605772";

        //组装请求参数
        JSONObject map=new JSONObject();
        map.put("account", account);
        map.put("password", password);
        map.put("msg", msg);
        map.put("mobile", mobile);

        String params=map.toString();

       // logger.info("请求参数为:" + params);
        try {
            String result= HttpUtil.post(url, params);

         //   logger.info("返回参数为:" + result);



           // logger.info("状态码:" + code + ",状态码说明:" + error + ",消息id:" + msgid);
        } catch (Exception e) {
            // TODO: handle exception
           // logger.error("请求异常：" + e);
        }
    }

}
