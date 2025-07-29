package club.fuwenhao.object;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 包装类和基本数据类型测试
 * 测试 Integer、Double、Boolean 等包装类的方法和特性
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2024/07/29
 */
public class WrapperTypesTest {

    /**
     * 测试 Integer 包装类
     */
    @Test
    public void testIntegerWrapper() {
        System.out.println("=== Integer 包装类测试 ===");

        // 创建 Integer 对象的不同方式
        Integer int1 = new Integer(100); // 已过时
        Integer int2 = Integer.valueOf(100);
        Integer int3 = 100; // 自动装箱

        System.out.println("int1: " + int1);
        System.out.println("int2: " + int2);
        System.out.println("int3: " + int3);

        // 比较
        System.out.println("\n=== 比较测试 ===");
        System.out.println("int1 == int2: " + (int1 == int2)); // false
        System.out.println("int2 == int3: " + (int2 == int3)); // true (缓存池)
        System.out.println("int1.equals(int2): " + int1.equals(int2)); // true

        // 缓存池测试 (-128 到 127)
        Integer a = 127;
        Integer b = 127;
        Integer c = 128;
        Integer d = 128;
        System.out.println("\n=== 缓存池测试 ===");
        System.out.println("127 == 127: " + (a == b)); // true
        System.out.println("128 == 128: " + (c == d)); // false

        // 类型转换
        System.out.println("\n=== 类型转换 ===");
        Integer num = 123;
        System.out.println("intValue(): " + num.intValue());
        System.out.println("doubleValue(): " + num.doubleValue());
        System.out.println("toString(): " + num.toString());

        // 静态方法
        System.out.println("\n=== 静态方法 ===");
        System.out.println("Integer.parseInt('456'): " + Integer.parseInt("456"));
        System.out.println("Integer.toBinaryString(10): " + Integer.toBinaryString(10));
        System.out.println("Integer.toHexString(255): " + Integer.toHexString(255));
        System.out.println("Integer.max(10, 20): " + Integer.max(10, 20));
        System.out.println("Integer.min(10, 20): " + Integer.min(10, 20));
    }

    /**
     * 测试 Double 包装类
     */
    @Test
    public void testDoubleWrapper() {
        System.out.println("=== Double 包装类测试 ===");

        Double d1 = 3.14;
        Double d2 = Double.valueOf(3.14);

        System.out.println("d1: " + d1);
        System.out.println("d2: " + d2);
        System.out.println("d1.equals(d2): " + d1.equals(d2));

        // 特殊值
        System.out.println("\n=== 特殊值测试 ===");
        Double infinity = Double.POSITIVE_INFINITY;
        Double negInfinity = Double.NEGATIVE_INFINITY;
        Double nan = Double.NaN;

        System.out.println("正无穷: " + infinity);
        System.out.println("负无穷: " + negInfinity);
        System.out.println("NaN: " + nan);
        System.out.println("isInfinite(infinity): " + Double.isInfinite(infinity));
        System.out.println("isNaN(nan): " + Double.isNaN(nan));

        // 比较方法
        System.out.println("\n=== 比较方法 ===");
        Double d3 = 3.14;
        Double d4 = 3.15;
        System.out.println("compare(3.14, 3.15): " + Double.compare(d3, d4));
        System.out.println("compareTo(3.15): " + d3.compareTo(d4));

        // 精度问题
        System.out.println("\n=== 精度问题 ===");
        double result = 0.1 + 0.2;
        System.out.println("0.1 + 0.2 = " + result);
        System.out.println("0.1 + 0.2 == 0.3: " + (result == 0.3));

        // 使用 BigDecimal 解决精度问题
        BigDecimal bd1 = new BigDecimal("0.1");
        BigDecimal bd2 = new BigDecimal("0.2");
        BigDecimal bd3 = new BigDecimal("0.3");
        BigDecimal sum = bd1.add(bd2);
        System.out.println("BigDecimal: 0.1 + 0.2 = " + sum);
        System.out.println("BigDecimal: 0.1 + 0.2 == 0.3: " + sum.equals(bd3));
    }

    /**
     * 测试 Boolean 包装类
     */
    @Test
    public void testBooleanWrapper() {
        System.out.println("=== Boolean 包装类测试 ===");

        Boolean b1 = true;
        Boolean b2 = Boolean.valueOf(true);
        Boolean b3 = Boolean.valueOf("true");
        Boolean b4 = Boolean.valueOf("false");

        System.out.println("b1: " + b1);
        System.out.println("b2: " + b2);
        System.out.println("b3: " + b3);
        System.out.println("b4: " + b4);

        System.out.println("\n=== 比较测试 ===");
        System.out.println("b1 == b2: " + (b1 == b2)); // true (缓存)
        System.out.println("b1.equals(b2): " + b1.equals(b2));

        // 字符串解析
        System.out.println("\n=== 字符串解析 ===");
        System.out.println("parseBoolean('true'): " + Boolean.parseBoolean("true"));
        System.out.println("parseBoolean('false'): " + Boolean.parseBoolean("false"));
        System.out.println("parseBoolean('True'): " + Boolean.parseBoolean("True"));
        System.out.println("parseBoolean('other'): " + Boolean.parseBoolean("other"));

        // 逻辑运算
        System.out.println("\n=== 逻辑运算 ===");
        System.out.println("logicalAnd(true, false): " + Boolean.logicalAnd(true, false));
        System.out.println("logicalOr(true, false): " + Boolean.logicalOr(true, false));
        System.out.println("logicalXor(true, false): " + Boolean.logicalXor(true, false));
    }

    /**
     * 测试 Character 包装类
     */
    @Test
    public void testCharacterWrapper() {
        System.out.println("=== Character 包装类测试 ===");

        Character ch1 = 'A';
        Character ch2 = Character.valueOf('A');

        System.out.println("ch1: " + ch1);
        System.out.println("ch2: " + ch2);
        System.out.println("ch1 == ch2: " + (ch1 == ch2)); // true (缓存)

        // 字符判断
        char c = 'a';
        System.out.println("\n=== 字符判断 ===");
        System.out.println("isLetter('a'): " + Character.isLetter(c));
        System.out.println("isDigit('a'): " + Character.isDigit(c));
        System.out.println("isUpperCase('a'): " + Character.isUpperCase(c));
        System.out.println("isLowerCase('a'): " + Character.isLowerCase(c));
        System.out.println("isWhitespace(' '): " + Character.isWhitespace(' '));

        // 字符转换
        System.out.println("\n=== 字符转换 ===");
        System.out.println("toUpperCase('a'): " + Character.toUpperCase('a'));
        System.out.println("toLowerCase('A'): " + Character.toLowerCase('A'));
        System.out.println("getNumericValue('5'): " + Character.getNumericValue('5'));

        // Unicode 相关
        System.out.println("\n=== Unicode 相关 ===");
        char chinese = '中';
        System.out.println("中文字符: " + chinese);
        System.out.println("Unicode值: " + (int) chinese);
        System.out.println("isLetter('中'): " + Character.isLetter(chinese));
    }

    /**
     * 测试自动装箱和拆箱
     */
    @Test
    public void testAutoBoxingUnboxing() {
        System.out.println("=== 自动装箱和拆箱测试 ===");

        // 自动装箱
        Integer boxed = 100; // int -> Integer
        Double boxedDouble = 3.14; // double -> Double

        System.out.println("自动装箱:");
        System.out.println("boxed: " + boxed);
        System.out.println("boxedDouble: " + boxedDouble);

        // 自动拆箱
        int unboxed = boxed; // Integer -> int
        double unboxedDouble = boxedDouble; // Double -> double

        System.out.println("\n自动拆箱:");
        System.out.println("unboxed: " + unboxed);
        System.out.println("unboxedDouble: " + unboxedDouble);

        // 在运算中的装箱拆箱
        Integer a = 10;
        Integer b = 20;
        Integer sum = a + b; // 拆箱 -> 运算 -> 装箱

        System.out.println("\n运算中的装箱拆箱:");
        System.out.println("a + b = " + sum);

        // 注意 NullPointerException
        System.out.println("\n=== 空指针异常风险 ===");
        Integer nullInteger = null;
        try {
            int result = nullInteger + 10; // NPE
        } catch (NullPointerException e) {
            System.out.println("空指针异常: " + e.getMessage());
        }
    }

    /**
     * 测试 BigInteger 和 BigDecimal
     */
    @Test
    public void testBigNumbers() {
        System.out.println("=== BigInteger 和 BigDecimal 测试 ===");

        // BigInteger 测试
        System.out.println("=== BigInteger ===");
        BigInteger big1 = new BigInteger("12345678901234567890");
        BigInteger big2 = new BigInteger("98765432109876543210");

        System.out.println("big1: " + big1);
        System.out.println("big2: " + big2);
        System.out.println("big1 + big2: " + big1.add(big2));
        System.out.println("big2 - big1: " + big2.subtract(big1));
        System.out.println("big1 * big2: " + big1.multiply(big2));

        // BigDecimal 测试
        System.out.println("\n=== BigDecimal ===");
        BigDecimal decimal1 = new BigDecimal("123.456");
        BigDecimal decimal2 = new BigDecimal("78.90");

        System.out.println("decimal1: " + decimal1);
        System.out.println("decimal2: " + decimal2);
        System.out.println("decimal1 + decimal2: " + decimal1.add(decimal2));
        System.out.println("decimal1 - decimal2: " + decimal1.subtract(decimal2));
        System.out.println("decimal1 * decimal2: " + decimal1.multiply(decimal2));

        // 除法需要指定精度
        BigDecimal result = decimal1.divide(decimal2, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("decimal1 / decimal2 (保留2位小数): " + result);

        // 比较
        System.out.println("\n=== BigDecimal 比较 ===");
        BigDecimal d1 = new BigDecimal("1.0");
        BigDecimal d2 = new BigDecimal("1.00");
        System.out.println("d1.equals(d2): " + d1.equals(d2)); // false (精度不同)
        System.out.println("d1.compareTo(d2): " + d1.compareTo(d2)); // 0 (值相等)
    }
}
