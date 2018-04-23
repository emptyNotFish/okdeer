package utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Lenovo on 2018/1/4.
 */
public class RSAUtils {
    /*public static String[] generateKeyPair() throws Exception {
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (Exception e) {

        }
        kpg.initialize(1024, new SecureRandom());
        KeyPair kp = kpg.genKeyPair();
        PrivateKey pvtKey = kp.getPrivate();
        PublicKey publicKey = kp.getPublic();
        String[] result = new String[2];
        byte[] bytePrivate = pvtKey.getEncoded();
        result[0] = Base64.encodeBase64String(bytePrivate);
        System.out.println("私钥:" + result[0]);
        byte[] bytePublic = publicKey.getEncoded();
        result[1] = Base64.encodeBase64String(bytePublic);
        System.out.println("公钥:" + result[1]);
        return result;
    }*/

    /**
     * 公钥加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥加密过程
     *
     * @param privateKey    私钥
     * @param plainTextData 明文数据
     * @return
     * @throws Exception 加密过程中的异常信息
     */
    public static byte[] encrypt(RSAPrivateKey privateKey, byte[] plainTextData) throws Exception {
        if (privateKey == null) {
            throw new Exception("加密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(plainTextData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 私钥解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            // cipher= Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    /**
     * 公钥解密过程
     *
     * @param publicKey  公钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public static byte[] decrypt(RSAPublicKey publicKey, byte[] cipherData)
            throws Exception {
        if (publicKey == null) {
            throw new Exception("解密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            // 使用默认RSA
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(cipherData);
            return output;
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }

    public static RSAPublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return (RSAPublicKey) publicKey;
    }

    public static RSAPrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return (RSAPrivateKey) privateKey;
    }

    public static void main(String[] arg0) {
        try {
            String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJNTF/3MHeG7YcEPAbmmA9PscKLOfnmDnTWSFGzPyxbyfm+OjwPhgQaHthhvlW6Qd7r2AU6blttHJjVzEy80L0vKGbJpQjJ2mephC0wmMvrEhmkHQTKx3y6/myaUg6sDKnZtcYmIXfKulJz8nR7g3ndRy5xaCMOmhcxsisSwPG15AgMBAAECgYBATrIXP8T/ZqrJ+cH4BoZ/OCMGffppElFKNASkZqOw1ZLpISI+QG3wqsaKrhJdW8RXoNB7PqFAADOmex+xqCbIVdj8fKoXHUSjZoR8+BarC5b4hcI+r+cz8+IgN/9aoHbmXfwBvWk72IMCJsm8tToi6FG/0U2geWxkGzvidI0xAQJBAMM3YhObW3KNBmz/6PoMl3VmL6UqFtWWTzD/yB/D0FsR3vg2pmMX8P2c3LaiClvR20b8w2UCXPmFv6fgMx6kstkCQQDBMkce0SAP6Oj44wYQOUuw1FGlXGVWlvZZWf8kAfi+i0XT1YIVIUTtJCY9DQJc4RTCHExzXGtkMYhGWCJwGauhAkEAo92Fcjfil1FRp4VZJywSpvVT4SxnZnp5lwPeFOvlpcxmgaw+Fsgt/nUBwDxcvZ52livuSIenS7y9mElWH87YYQJAQA1LRwxOdPfjRaD/AaH9VkMOTqSzdGHYiwcrb+S3c3T5UaIX3qmJEJZLlzIY9nIpT7HSJu0dLbq+U5Qk1sWsAQJBAJeld0Wt1Wjw2vB3lgfwy/N7C+0jb7tlSkGhgIPT7t6FO9WNYFzUgSRF0DGMHPVTNxW7SeYgJ1vIWy7+5cUt5i0=";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTUxf9zB3hu2HBDwG5pgPT7HCizn55g501khRsz8sW8n5vjo8D4YEGh7YYb5VukHe69gFOm5bbRyY1cxMvNC9LyhmyaUIydpnqYQtMJjL6xIZpB0Eysd8uv5smlIOrAyp2bXGJiF3yrpSc/J0e4N53UcucWgjDpoXMbIrEsDxteQIDAQAB";

            String module = getPublicKey(publicKey).getModulus().toString(16);
            String empoent = getPublicKey(publicKey).getPublicExponent().toString(16);
            System.out.println("m:" + module);
            System.out.println("e:" + empoent);

            String result = "232cdc6e3734977487c5962c2e5fd5ff7c12c829b0dcc8001e4d42bb574cb1ab976dd4e1c6e28d701ae2b096643e5c54c31f5fe709f4362588bb9bdbfaf155c39e3ec4416ab18715e2b6d114e5f8519feb08c3351466d05bc80c980275d4bb29fa956a5cfdb4d7f985be6c062e15789a93a2e9d02b99844df10fabea84dd76e9";
            System.out.println(result.length());
            byte[] en_result = HexToBytes.HexString2Bytes(result);
            byte[] de_result = RSAUtils.decrypt(RSAUtils.getPrivateKey(privateKey), en_result);
            StringBuilder sb = new StringBuilder();
            sb.append(new String(de_result));
            String str = sb.reverse().toString();

            String e = Escape.unescape(str);
            System.out.println("解密后：" + e);

            String name="root";
            String pwd = "123456";
            // 利用公钥进行加密
            byte[] pubName = HexToBytes.HexString2Bytes(name);
            System.out.println(pubName);
            byte[] nameArray = RSAUtils.encrypt(RSAUtils.getPublicKey(publicKey),
                    new StringBuilder(name).reverse().toString().getBytes());
            System.out.println(HexToBytes.Bytes2HexString(nameArray).length());
            System.out.println(HexToBytes.Bytes2HexString(RSAUtils.encrypt(RSAUtils.getPublicKey(publicKey),
                    new StringBuilder(pwd).reverse().toString().getBytes())));

            byte[] de_name = RSAUtils.decrypt(RSAUtils.getPrivateKey(privateKey), nameArray);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(new String(de_name));
            System.out.println("sb:" + sb2.toString());
            String str2 = sb2.reverse().toString();

            String e2 = Escape.unescape(str2);

            String nowDate = DateUtil.getDateStr(DateUtil.strToDate(DateUtil.NowStr()), "yyyy-MM-dd");
            Md5 md5 = new Md5();
            String code = md5.getMD5ofStr(nowDate);
            String key = StringUtils.subStringAdd(code, 16, "0");
            String sds = StringUtils.trim(AESUtils.decryptData("1234567890123456", key, key));
            System.out.println("用户名解密后：" + sds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
