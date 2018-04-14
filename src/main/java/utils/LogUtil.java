package utils;

import sun.rmi.runtime.Log;

/**
 * Created by Administrator on 2018/3/31.
 */
public class LogUtil {
    private static Log errorLogger = LogFactory.getLog("[ERR]");
    private static Log warnLogger = LogFactory.getLog("[WARN]");
    private static Log infoLogger = LogFactory.getLog("[INFO]");
    private static Log pushLogger = LogFactory.getLog("[PUSH]");
    private static Log smsLogger = LogFactory.getLog("[SMS]");
    private static Log emailLogger = LogFactory.getLog("[EMAIL]");
    private static Log chatLogger = LogFactory.getLog("[CHAT]");
    private static Log orderLogger = LogFactory.getLog("[ORDER]");
    private static Log payLogger = LogFactory.getLog("[PAY]");
    private static Log fileLogger = LogFactory.getLog("[FILE]");
    private static Log statLogger = LogFactory.getLog("[STAT]");

    public LogUtils() {
    }

    public static void log4Info(String info) {
        infoLogger.info(info);
    }

    public static void log4Info(String info, Throwable t) {
        infoLogger.info(info, t);
    }

    public static void log4Warn(String info) {
        warnLogger.warn(info);
    }

    public static void log4Warn(String info, Throwable t) {
        warnLogger.warn(info, t);
    }

    public static void log4Error(String info) {
        errorLogger.error(info);
    }

    public static void log4Error(String info, Throwable t) {
        errorLogger.error(info, t);
    }

    public static void log4Error(Throwable t) {
        errorLogger.error(t.getMessage(), t);
    }

    public static void log4SMS(String url, String phone, String type, String response) {
        smsLogger.info("platform: " + url + ",target: " + phone + ",type:" + type + ",res: " + response);
    }

    public static void log4Email(String target, String subject, String res) {
        emailLogger.info("target: " + target + ",subject: " + subject + ",res: " + res);
    }

    public static void log4Chat(String info) {
        chatLogger.info(info);
    }

    public static void log4Order(String info) {
        orderLogger.info(info);
    }

    public static void log4Pay(String info) {
        payLogger.info(info);
    }

    public static void log4File(String path, String size, String uuid, String type, String clientId, String channelId) {
        fileLogger.info("client:" + clientId + ",channel:" + channelId + ",path: " + path + ",type: " + type + ",size:" + size + ",commit: " + uuid);
    }

    public static void log4File(String info) {
        fileLogger.info(info);
    }

    public static void log4Push(String info) {
        pushLogger.info(info);
    }

    public static void log4Stat(String info) {
        statLogger.info(info);
    }

    public static void rollingFile() {
        errorLogger.info("FORCE ROLLING FILE");
        infoLogger.info("FORCE ROLLING FILE");
        statLogger.info("FORCE ROLLING FILE");
        pushLogger.info("FORCE ROLLING FILE");
        smsLogger.info("FORCE ROLLING FILE");
        emailLogger.info("FORCE ROLLING FILE");
        chatLogger.info("FORCE ROLLING FILE");
        orderLogger.info("FORCE ROLLING FILE");
        payLogger.info("FORCE ROLLING FILE");
        fileLogger.info("FORCE ROLLING FILE");
    }
}
