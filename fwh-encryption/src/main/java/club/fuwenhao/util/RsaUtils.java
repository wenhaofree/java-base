package club.fuwenhao.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @description 数字签名工具类
 * @date 2021/1/30 4:05 下午
 */
public class RsaUtils {


    public static final String CHARSET = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 1024;
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    //十六进制字符
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 生成公钥和私钥
     *
     * @param
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @author fuwenhao
     * @date 2021/5/17 2:23 下午
     */
    public static Map<String, String> createKeys() {
        //为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator kpg;
        try {
            kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm-->[" + RSA_ALGORITHM + "]");
        }

        //初始化KeyPairGenerator对象,密钥长度
        kpg.initialize(KEY_SIZE);
        //生成密匙对
        KeyPair keyPair = kpg.generateKeyPair();
        //得到公钥
        Key publicKey = keyPair.getPublic();
        String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());
        //得到私钥
        Key privateKey = keyPair.getPrivate();
        String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
        Map<String, String> keyPairMap = new HashMap<String, String>();
        keyPairMap.put("publicKey", publicKeyStr);
        keyPairMap.put("privateKey", privateKeyStr);

        return keyPairMap;
    }

    /**
     * 通过X509编码的Key指令获得公钥对象
     *
     * @param publicKey
     * @return java.security.interfaces.RSAPublicKey
     * @author fuwenhao
     * @date 2021/5/17 2:24 下午
     */
    public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    /**
     * 通过PKCS#8编码的Key指令获得私钥对象
     *
     * @param privateKey
     * @return java.security.interfaces.RSAPrivateKey
     * @author fuwenhao
     * @date 2021/5/17 2:23 下午
     */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }

    /**
     * 私钥加密数据
     *
     * @param data
     * @param privateKey
     * @return java.lang.String
     * @author fuwenhao
     * @date 2021/5/17 2:24 下午
     */
    public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            return Base64.encodeBase64String(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
        } catch (Exception e) {
            throw new RuntimeException("加密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 公钥解密数据
     *
     * @param data
     * @param publicKey
     * @return java.lang.String
     * @author fuwenhao
     * @date 2021/5/17 2:24 下午
     */
    public static String publicDecrypt(String data, RSAPublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + data + "]时遇到异常", e);
        }
    }

    /**
     * 核心-加解密
     *
     * @param cipher
     * @param opmode
     * @param datas
     * @param keySize
     * @return byte[]
     * @author fuwenhao
     * @date 2021/5/17 2:32 下午
     */
    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] datas, int keySize) {
        int maxBlock = 0;
        if (opmode == Cipher.DECRYPT_MODE) {
            maxBlock = keySize / 8;
        } else {
            maxBlock = keySize / 8 - 11;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] buff;
        int i = 0;
        try {
            while (datas.length > offSet) {
                if (datas.length - offSet > maxBlock) {
                    buff = cipher.doFinal(datas, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(datas, offSet, datas.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        byte[] resultDatas = out.toByteArray();
        try {
            out.close();
        } catch (Exception e) {
            e.getMessage();
        }
        return resultDatas;
    }

    /**
     * 字符串十六进制转换
     *
     * @param s
     * @return java.lang.String
     * @author fuwenhao
     * @date 2021/5/17 2:29 下午
     */
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            str += Integer.toHexString(ch);
        }
        return str;
    }

    //***************************签名和验证*******************************  

    /**
     * 数据-签名
     *
     * @param data
     * @param privateKeyStr
     * @return java.lang.String
     * @author fuwenhao
     * @date 2021/5/17 3:25 下午
     */
    public static String sign(byte[] data, String privateKeyStr) throws Exception {
        PrivateKey priK = getPrivateKey(new String(hexToBytes(privateKeyStr)));
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(priK);
        sig.update(data);
        return bytesToHex(sig.sign());
    }

    /**
     * 数据-验签
     *
     * @param data
     * @param sign
     * @param publicKeyStr
     * @return boolean
     * @author fuwenhao
     * @date 2021/5/17 3:25 下午
     */
    public static boolean verify(byte[] data, String sign, String publicKeyStr) throws Exception {
        PublicKey pubK = getPublicKey(new String(hexToBytes(publicKeyStr)));
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(hexToBytes(sign));
    }

    /**
     * 将16进制字符串转换为byte[]
     *
     * @param str
     * @return
     */
    public static byte[] hexToBytes(String str) {
        if (str == null || str.trim().equals("")) {
            return new byte[0];
        }
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }
        return bytes;
    }

    /**
     * 将byte[]转换为16进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        //一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        // 使用除与取余进行转换
        for (byte b : bytes) {
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }
            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }
        return new String(buf);
    }

    /**
     * 思路汇总：
     * 加解密：
     * 1. 生成公钥私钥：
     * 2. 私钥加密明文内容得到密文
     * 3. 公钥解密密文内容得到明文
     * 签名：
     * 1. 私钥和签名内容加密得到签名
     * 2. 公钥和签名内容加密的内容对比；true为签名一致
     *
     * @param args
     * @return void
     * @author fuwenhao
     * @date 2021/5/17 3:39 下午
     */
    public static void main(String[] args) throws Exception {
        Map<String, String> keyMap = createKeys();
        String publicKey = keyMap.get("publicKey");
        String privateKey = keyMap.get("privateKey");
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);

        privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO75mn5Ek1CNcpLH3435F1Za4bb0cO87sjHtVXi3KEx47QjGBdm1DcjXHpsNoQJK/QmQJQ7xGIwMcYP/vxY6OlQaGr9BOKSBMly0/BX9Ln8vqyN/tgnX0lhiOqwRrQn0I0mg7XZS7QijhlLEKs7ToYM8nqHyZ9TNqk+aFRV2vsBdAgMBAAECgYEAyxoCLrxzg2uj65HJsuz346G74fVgIkLXEM3H7Z+ktVG1CtbZ1AynmYES9u6fX8aBxeXnSQqpNSXCOFBchDSaqolNEBdSdMyd2O9qoNJrndCZSXYfCv7aD4CH2bGJIec/vrPftgv4D5wBwok1RPP8m/YmjmSoa020XXjnNTkc/qECQQD59omiHMOQ5tb+xf2AQgYBjmLif82fiiwjcdVx8a3jq3AU8rINnHjiGPj3Q8s2cNtyPkmh4fOYmMDOmWG/g+pFAkEA9L8hJzYvtiDDD0p803B2ygWYyDq2Gb/i/UdgyVnElnvbm4yib30CEf22e0fTiYxW3hY3Twjg/Z9THz1xNU4rOQJBAIVa9kq6tctUnFcIIhqPHs0tvavBK152RN4UGR/VJERWblF+uWdxlzxovkqMrJm61Bi7CmWHOoKq3BJc2j1mj1ECQF/i8c43AIJOW52GBVmhWijUAIOR3P5Bm2C3skewj8CVTfinJIyhYUpDKFcsUMg232GrOogQZnV3Ek3O+Kd1tKECQH/rinkJUymrbvQpGvFdKEjKVIClN3VCe0E5N1pfhwBpnFIfdfnbFT2x/qpIL0eArVj7pq3NyVz6bvAqxAeBiWI=";
        publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDu+Zp+RJNQjXKSx9+N+RdWWuG29HDvO7Ix7VV4tyhMeO0IxgXZtQ3I1x6bDaECSv0JkCUO8RiMDHGD/78WOjpUGhq/QTikgTJctPwV/S5/L6sjf7YJ19JYYjqsEa0J9CNJoO12Uu0Io4ZSxCrO06GDPJ6h8mfUzapPmhUVdr7AXQIDAQAB";

        System.out.println("私钥加密——公钥解密");
        String str = "{\"domainId\":\"ssotest\",\"externalUserId\":\"A0001\",\"timestamp\":1521616977}";
        System.out.println("\r明文：\r\n" + str);
        String encodedData = privateEncrypt(str, getPrivateKey(privateKey));
        System.out.println("密文：\r\n" + toHexString(encodedData));
        String decodedData = publicDecrypt(encodedData, getPublicKey(publicKey));
        System.out.println("解密后文字: \r\n" + decodedData);


        System.out.println("签名--验签");
        String signData = "被签名的内容sssssssassssssssssssssssssssssssssssssssss";
        System.out.println("\n原文:" + signData);
        String signature = sign(signData.getBytes(), bytesToHex(privateKey.getBytes()));
        System.out.println("签名为：" + signature);
        boolean status = verify(signData.getBytes(), signature, bytesToHex(publicKey.getBytes()));
        System.out.println("验证情况：" + status);
    }
}
