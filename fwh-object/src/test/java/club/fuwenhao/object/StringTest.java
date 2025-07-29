package club.fuwenhao.object;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/**
 * String 相关方法测试
 * 汇集String所有相关问题和使用场景
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2024/07/29
 */
public class StringTest {

    /**
     * String基本操作测试
     * 测试字符串的创建、拼接、比较等基本操作
     */
    @Test
    public void testStringBasicOperations() {
        // 字符串创建
        String str1 = "Hello";
        String str2 = new String("Hello");
        String str3 = "Hello";

        System.out.println("str1 == str2: " + (str1 == str2)); // false
        System.out.println("str1 == str3: " + (str1 == str3)); // true (字符串常量池)
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true

        // 字符串拼接
        String concatenated = str1 + " World";
        System.out.println("拼接结果: " + concatenated);

        // StringBuilder vs StringBuffer
        StringBuilder sb = new StringBuilder();
        sb.append("Hello").append(" ").append("World");
        System.out.println("StringBuilder结果: " + sb.toString());
    }

    /**
     * 字符串常用方法测试
     */
    @Test
    public void testStringCommonMethods() {
        String str = "  Hello World Java Programming  ";

        // 长度和清理
        System.out.println("原字符串长度: " + str.length());
        System.out.println("trim后: '" + str.trim() + "'");
        System.out.println("trim后长度: " + str.trim().length());

        // 大小写转换
        System.out.println("大写: " + str.toUpperCase());
        System.out.println("小写: " + str.toLowerCase());

        // 查找和替换
        System.out.println("包含'Java': " + str.contains("Java"));
        System.out.println("indexOf 'World': " + str.indexOf("World"));
        System.out.println("替换空格为'-': " + str.replace(" ", "-"));

        // 截取
        System.out.println("截取(7,12): '" + str.substring(7, 12) + "'");

        // 分割
        String[] parts = str.trim().split(" ");
        System.out.println("分割结果: " + Arrays.toString(parts));
    }

    /**
     * 使用Apache Commons Lang工具类测试
     */
    @Test
    public void testStringUtils() {
        String str = "  Hello World  ";
        String emptyStr = "";
        String nullStr = null;
        String blankStr = "   ";

        // 空值判断
        System.out.println("isEmpty测试:");
        System.out.println("empty string: " + StringUtils.isEmpty(emptyStr)); // true
        System.out.println("null string: " + StringUtils.isEmpty(nullStr)); // true
        System.out.println("blank string: " + StringUtils.isEmpty(blankStr)); // false

        System.out.println("\nisBlank测试:");
        System.out.println("empty string: " + StringUtils.isBlank(emptyStr)); // true
        System.out.println("null string: " + StringUtils.isBlank(nullStr)); // true
        System.out.println("blank string: " + StringUtils.isBlank(blankStr)); // true

        // 去除空格
        System.out.println("\n去除空格:");
        System.out.println("trim: '" + StringUtils.trim(str) + "'");
        System.out.println("strip: '" + StringUtils.strip(str) + "'");

        // 默认值
        System.out.println("\n默认值:");
        System.out.println("defaultString(null): '" + StringUtils.defaultString(nullStr) + "'");
        System.out.println("defaultString(null, 'default'): '" + StringUtils.defaultString(nullStr, "default") + "'");

        // 截取
        System.out.println("\n安全截取:");
        System.out.println("substring(str, 2, 7): '" + StringUtils.substring(str, 2, 7) + "'");
        System.out.println("left(str, 5): '" + StringUtils.left(str, 5) + "'");
        System.out.println("right(str, 5): '" + StringUtils.right(str, 5) + "'");
    }

    /**
     * 字符串连接方法对比测试
     */
    @Test
    public void testStringJoinMethods() {
        List<String> words = Arrays.asList("Java", "is", "awesome");

        // 使用 String.join (Java 8+)
        String joined1 = String.join(" ", words);
        System.out.println("String.join: " + joined1);

        // 使用 StringJoiner
        StringJoiner joiner = new StringJoiner(" ", "[", "]");
        words.forEach(joiner::add);
        System.out.println("StringJoiner: " + joiner.toString());

        // 使用 Apache Commons Lang
        String joined2 = StringUtils.join(words, " ");
        System.out.println("StringUtils.join: " + joined2);

        // 使用 StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(words.get(i));
        }
        System.out.println("StringBuilder: " + sb.toString());
    }

    /**
     * 字符串性能测试
     * 测试不同字符串操作的性能差异
     */
    @Test
    public void testStringPerformance() {
        int iterations = 10000;

        // String 拼接性能测试
        long startTime = System.currentTimeMillis();
        String result1 = "";
        for (int i = 0; i < iterations; i++) {
            result1 += "a";
        }
        long stringTime = System.currentTimeMillis() - startTime;

        // StringBuilder 性能测试
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        String result2 = sb.toString();
        long stringBuilderTime = System.currentTimeMillis() - startTime;

        System.out.println("String 拼接耗时: " + stringTime + "ms");
        System.out.println("StringBuilder 耗时: " + stringBuilderTime + "ms");
        System.out.println("性能提升: " + (stringTime / (double) stringBuilderTime) + "倍");
    }

    /**
     * 字符串编码和解码测试
     */
    @Test
    public void testStringEncoding() {
        String original = "Hello 中文 🌟";
        
        try {
            // 不同编码测试
            byte[] utf8Bytes = original.getBytes("UTF-8");
            byte[] gbkBytes = original.getBytes("GBK");
            
            System.out.println("原始字符串: " + original);
            System.out.println("UTF-8字节长度: " + utf8Bytes.length);
            System.out.println("GBK字节长度: " + gbkBytes.length);
            
            // 从字节还原
            String fromUtf8 = new String(utf8Bytes, "UTF-8");
            String fromGbk = new String(gbkBytes, "GBK");
            
            System.out.println("从UTF-8还原: " + fromUtf8);
            System.out.println("从GBK还原: " + fromGbk);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 正则表达式相关测试
     */
    @Test
    public void testStringRegex() {
        String text = "联系电话：138-1234-5678，邮箱：test@example.com";
        
        // 手机号匹配
        String phonePattern = "\\d{3}-\\d{4}-\\d{4}";
        if (text.matches(".*" + phonePattern + ".*")) {
            System.out.println("包含手机号");
        }
        
        // 邮箱匹配
        String emailPattern = "\\w+@\\w+\\.\\w+";
        if (text.matches(".*" + emailPattern + ".*")) {
            System.out.println("包含邮箱");
        }
        
        // 替换测试
        String replaced = text.replaceAll("\\d{3}-\\d{4}-\\d{4}", "[手机号]");
        System.out.println("替换后: " + replaced);
    }
}
