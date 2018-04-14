package utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Administrator on 2018/3/31.
 */
public class RandomUtil {
    private static final int DEF_COUNT = 20;

    // 短信验证码长度 6
    private static final int VERIFICATION_CODE_COUNT = 6;

    private RandomUtil() {
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    public static String generateVerificationCode() {
        return RandomStringUtils.randomNumeric(VERIFICATION_CODE_COUNT);
    }

    public static void main(String[] args){
        System.out.println(generateVerificationCode());
    }

}
