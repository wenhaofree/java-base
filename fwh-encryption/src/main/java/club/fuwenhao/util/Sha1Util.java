package club.fuwenhao.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author fwh [2020/9/18 && 5:05 下午]
 */
public class Sha1Util {

    /**
     * 需要加密的内容
     * @param str
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuffer hexValue = new StringBuffer();
        MessageDigest sha = MessageDigest.getInstance("SHA");
        byte[] byteArray = str.getBytes("UTF-8");
        byte[] md5Bytes = sha.digest(byteArray);

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String data ="张胜男";
        System.out.println(encode(data));
    }

}
