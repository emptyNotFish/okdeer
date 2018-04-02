package utils;

/**
 * Created by Lenovo on 2017/12/5.
 */

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESUtils {


    /**
     * aes 加密
     *
     * @param data
     * @return
     */
    public static String encryptData(String data, String code, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(code.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new String(Base64.encodeBase64(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * aes 解密
     *
     * @param data 密文
     * @return
     */
    public static String decryptData(String data, String code, String iv) {
        try {
            byte[] encrypted1 = Base64.decodeBase64(data.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(code.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成AES加密的密钥 (KEY)
     *
     * @return
     */
    public static String generateEncryptKey() {
        String encryptKey = (new Md5()).getMD5ofStr(DateUtil.getDateStr(DateUtil.strToDate(DateUtil.NowStr()), "yyyy-MM-dd"));
        if (!StringUtils.isEmpty(encryptKey)) {
            encryptKey = StringUtils.subStringAdd(encryptKey, 16, "0");
        }

        return encryptKey;
    }

    /**
     * 加密数据库敏感信息
     *
     * @param username
     * @param password
     */
    public static void getDataSourceUP(String username, String password) {
        Md5 md5 = new Md5();
        String code = md5.getMD5ofStr("mes-cloud-hrssc");
        String key = StringUtils.subStringAdd(code, 16, "0");

        String uname = AESUtils.encryptData(username, key, key);
        String pass = AESUtils.encryptData(password, key, key);
        System.out.println("username:" + uname);
        System.out.println("password:" + pass);

        String uname_str = AESUtils.decryptData(uname, key, key);
        String pass_str = AESUtils.decryptData(pass, key, key);
        System.out.println("uname_str:" + uname_str);
        System.out.println("pass_str:" + pass_str);
    }


    /**
     * 获取登录加密字符串
     *
     * @param number
     */
    public static void getUpass(String number) {
        String nowDate = DateUtil.getDateStr(DateUtil.strToDate(DateUtil.NowStr()), "yyyy-MM-dd");
        Md5 md5 = new Md5();
        String code = md5.getMD5ofStr(nowDate);
        String key = StringUtils.subStringAdd(code, 16, "0");

        String enStr = AESUtils.encryptData(number, key, key);
        System.out.println("key: " + key);
        System.out.println("Encrypt: " + enStr);
        String decStr = AESUtils.decryptData(enStr, key, key);
        System.out.println("decStr:" + decStr);
    }

    public static void main(String[] args) {
        Md5 md5 = new Md5();
        System.out.println(md5.getMD5ofStr("admin"));
        getUpass("admin");
        //getUpass("P147455");
    }

}
