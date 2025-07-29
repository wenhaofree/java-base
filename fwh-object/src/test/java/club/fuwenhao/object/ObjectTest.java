package club.fuwenhao.object;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * 测试基础对象的调用方法。
 */
public class ObjectTest {

    @Test
    public void testStringMethods() {
        String str = " Hello, World! ";

        // 测试 StringUtils 的方法
        String trimmed = StringUtils.trim(str);
        Assert.assertEquals("Hello, World!", trimmed);

        // 测试空字符串
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertTrue(StringUtils.isBlank(" "));
        Assert.assertFalse(StringUtils.isBlank("Hello"));
    }

    @Test
    public void testObjectMethods() {
        Object obj1 = new Object();
        Object obj2 = obj1;
        Object obj3 = new Object();

        // 测试 Objects.equals 方法
        Assert.assertTrue(Objects.equals(obj1, obj2));
        Assert.assertFalse(Objects.equals(obj1, obj3));

        // 测试 toString
        String result = Objects.toString(obj1, "default");
        Assert.assertNotEquals("default", result);
    }
}

