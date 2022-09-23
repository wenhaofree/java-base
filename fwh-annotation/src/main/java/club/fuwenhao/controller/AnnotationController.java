package club.fuwenhao.controller;

import club.fuwenhao.config.LoginRequired;
import club.fuwenhao.config.MyAnnotation;
import club.fuwenhao.config.MyLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2020/5/20 10:54 上午
 */
@RestController
@RequestMapping("annotation")
public class AnnotationController {
    @MyAnnotation(address = "西城土著", age = 18)
    private String userName;

    @GetMapping("sourceA")
    public String sourceA(HttpServletRequest request) {
        return "访问资源sourceA";
    }

    /**
     * 自定义注解-拦截验证登录
     *
     * @param request
     * @return java.lang.String
     * @author fwh [2020/5/20 && 11:45 上午]
     */
    @LoginRequired
    @GetMapping("sourceB")
    public String sourceB(HttpServletRequest request) {
        return "访问资源sourceB";
    }

    /**
     * 自定义注解--日志拦截
     *
     * @param paramName
     * @return java.lang.String
     * @author fwh [2020/5/20 && 2:25 下午]
     */
    @MyLog
    @GetMapping("sourceC/{paramName}")
    public String sourceB(@PathVariable("paramName") String paramName) {
        System.out.println(paramName);
        return String.format("访问资源sourceC,参数名:%s", paramName);
    }

    /**
     * 反射测试
     *
     * @param
     * @return java.lang.String
     * @author fwh [2020/5/20 && 11:02 上午]
     */
    @GetMapping("reflectTest")
    public String reflectTest() {
        final Class annotationControllerClass = AnnotationController.class;
        for (Field field : annotationControllerClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(MyAnnotation.class)) {
                final MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
                final String name = field.getName();
                final String address = annotation.address();
                final int age = annotation.age();
                System.out.println(String.format("name：%s,address:%s,age:%s", name, address, age));
                return String.format("name：%s,address:%s,age:%s", name, address, age);
            }
        }
        return "没有找到字段";
    }
}
