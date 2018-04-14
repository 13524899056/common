package utils;


import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/3/31.
 */
public class StrUtil {
    static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'L', 'K', 'J', 'H', 'G', 'F', 'D', 'S', 'A', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'P'};
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final Random rand = new Random();
    static final String REGEX_EMAIL = "^[a-z0-9A-Z]+([-|_|\\.]?[a-z0-9A-Z]+)?@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    static final String REGEX_PHONE = "^(13[0-9]|14[5|7]|15[^4,\\D]|18[0-9]|17[0|3|6|7|8])[0-9]{8}$";
    static final String REGEX_FULLPHONE = "^\\+[0-9]{2,4}(13|14|15|18|17)[0-9]{9}$";
    static final String REGEX_ACCOUNT = "^[a-zA-Z]\\w{5,17}$";
    static final String REGEX_PWD = "^[a-z0-9]{32}$";
    static final String REGEX_M5 = "^[a-z0-9]{32}$";
    static final String REGEX_TOKEN = "^[a-z0-9]{32}$";
    static final String REGEX_SQL = "[\"|\'|\\-|\\|\\\\|\\+|\\?|\\*|#|<|>|=|%|!|\\^|\\$]";
    static final String REGEX_URL = "^\\w*$";
    static final String REGEX_HTTP_URL = "^http(s)?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$";

    public StrUtil() {
    }

    public static boolean validEmail(String email) {
        if(org.apache.commons.lang3.StringUtils.isBlank(email)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[a-z0-9A-Z]+([-|_|\\.]?[a-z0-9A-Z]+)?@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public static boolean validPhone(String phoneNum) {
        if(StringUtils.isBlank(phoneNum)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^(13[0-9]|14[5|7]|15[^4,\\D]|18[0-9]|17[0|3|6|7|8])[0-9]{8}$");
            Matcher matcher = pattern.matcher(phoneNum);
            return matcher.matches();
        }
    }

    public static boolean validFullPhone(String phoneNum) {
        if(StringUtils.isBlank(phoneNum)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^\\+[0-9]{2,4}(13|14|15|18|17)[0-9]{9}$");
            Matcher matcher = pattern.matcher(phoneNum);
            return matcher.matches();
        }
    }

    public static boolean validToken(String token) {
        return StringUtils.isBlank(token)?false:Pattern.matches("^[a-z0-9]{32}$", token);
    }

    public static boolean validAccount(String username) {
        return StringUtils.isBlank(username)?false:Pattern.matches("^[a-zA-Z]\\w{5,17}$", username);
    }

    public static boolean validPwd(String password) {
        return StringUtils.isBlank(password)?false:Pattern.matches("^[a-z0-9]{32}$", password);
    }

    public static boolean validM5Str(String m5Str) {
        return StringUtils.isBlank(m5Str)?false:Pattern.matches("^[a-z0-9]{32}$", m5Str);
    }

    public static boolean validHttpUrl(String url) {
        return StringUtils.isBlank(url)?false:Pattern.matches("^http(s)?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$", url);
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    public static String randomString(int length) {
        StringBuffer sb = new StringBuffer();

        for(int loop = 0; loop < length; ++loop) {
            sb.append(hexDigits[rand.nextInt(hexDigits.length)]);
        }

        return sb.toString();
    }

    public static String randomNumber(int length) {
        StringBuffer sb = new StringBuffer();

        for(int loop = 0; loop < length; ++loop) {
            sb.append(digits[rand.nextInt(digits.length)]);
        }

        return sb.toString();
    }

    public static int str2int(String s) {
        return s != null && !s.equals("")?Integer.parseInt(s):0;
    }

    public static String noHtml(String s) {
        return StringUtils.isBlank(s)?"":s.replaceAll("<[.[^<]]*>", "");
    }

    public static String transHtml(String s) {
        return StringUtils.isBlank(s)?"":s.replace("<", "&lt;").replace(">", "&gt;");
    }

    public static String transSql(String s) {
        return StringUtils.isBlank(s)?"":s.replaceAll("[\"|\'|\\-|\\|\\\\|\\+|\\?|\\*|#|<|>|=|%|!|\\^|\\$]", "");
    }

    public static boolean areNotEmpty(String[] values) {
        boolean result = true;
        if(values != null && values.length != 0) {
            String[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                result &= !StringUtils.isEmpty(value);
            }
        } else {
            result = false;
        }

        return result;
    }

    public static boolean isNumeric(Object obj) {
        if(obj == null) {
            return false;
        } else {
            char[] chars = obj.toString().toCharArray();
            int length = chars.length;
            if(length < 1) {
                return false;
            } else {
                boolean i = false;
                if(length > 1 && chars[0] == 45) {
                    ;
                }

                for(int var4 = 1; var4 < length; ++var4) {
                    if(!Character.isDigit(chars[var4])) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public static void main(String[] args) {
//        String regex = "^[0-9]{1,2}[.0-9]*$";
//        String str = "15.131.101";
//        System.out.println(str.matches(regex));
//        System.out.println("http://s3-us-west-1.amazonaws.com/sensepublic/GameRes/actorimage/Lady_Dee.jpg".matches("^http(s)?://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$"));

        String phone = "13524890516";
        System.out.println(validFullPhone("+86"+phone));
        System.out.println(validPhone(phone));
    }
}
