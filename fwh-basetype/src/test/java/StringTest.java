import org.junit.Test;

/**
 * @program: fwh-parent
 * @description: string类型API
 * 原文链接：https://blog.csdn.net/qq_32265203/article/details/90028547
 * https://blog.csdn.net/I__Do__/article/details/100698579
 * @author: fwh
 * @date: 2021-06-01 14:11
 **/
public class StringTest {

    /**
     * 字符串获取：
     * <p>
     * public int length()：获取字符串当中含有的字符个数，拿到字符串长度。
     * public String concat(String str)：将当前字符串和参数字符串拼接成为返回值新的字符串。
     * public char charAt(int index)：获取指定索引位置的单个字符。（索引从0开始。）
     * public int indexOf(String str)：查找参数字符串在本字符串当中首次出现的索引位置，如果没有返回-1值。
     *
     * @return
     */
    @Test
    public void stringGet() {
        String testStr = "zhangsan";
        System.out.println("测试长度：" + testStr.length());
        System.out.println("测试拼接：" + testStr.concat("wo shi"));
        System.out.println("测试索引：" + testStr.charAt(0));
        System.out.println("测试首次索引位置：" + testStr.indexOf("a"));
    }

    /**
     * 字符串的截取方法：
     * <p>
     * public String substring(int index)：截取从参数位置一直到字符串末尾，返回新字符串。
     * public String substring(int begin, int end)：截取从begin开始，一直到end结束，中间的字符串。
     * 备注：[begin,end)，包含左边，不包含右边。
     */
    @Test
    public void stringSub() {
        String testStr = "zhangsan";
        System.out.println(testStr.substring(1));
        System.out.println(testStr.substring(0, 1));
    }

    /**
     * String当中与转换相关的常用方法有：
     * <p>
     * public char[] toCharArray()：将当前字符串拆分成为字符数组作为返回值。
     * public byte[] getBytes()：获得当前字符串底层的字节数组。
     * public String replace(CharSequence oldString, CharSequence newString)：
     * 将所有出现的老字符串替换成为新的字符串，返回替换之后的结果新字符串。
     * 备注：CharSequence意思就是说可以接受字符串类型。
     * 分割字符串的方法：
     * public String[] split(String regex)：按照参数的规则，将字符串切分成为若干部分。
     */
    @Test
    public void stringConversion() {
        String testStr = "zhangsan";
        System.out.println(testStr.toCharArray());
        System.out.println(testStr.getBytes());
        System.out.println(testStr.replace("z", "Z"));
        for (String value : testStr.split("a")) {
            System.out.println(value);
        }
    }

}
