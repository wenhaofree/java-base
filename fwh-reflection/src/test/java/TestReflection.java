import club.fuwenhao.bean.People;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/10 9:43 下午
 */
public class TestReflection {
    /**
     * Java反射机制主要提供了以下功能：
     * 在运行时构造一个类的对象；判断一个类所具有的成员变量和方法；调用一个对象的方法；生成动态代理。
     * 反射最大的应用就是框架。
     * Java反射的主要功能：
     * 确定一个对象的类取出类的modifiers,数据成员,方法,构造器,和超类找出某个接口里定义的常量和方法说明
     * 创建一个类实例,这个实例在运行时刻才有名字(运行时间才生成的对象)
     * 取得和设定对象数据成员的值,如果数据成员名是运行时刻确定的也能做到
     * 在运行时刻调用动态对象的方法
     * 创建数组,数组大小和类型在运行时刻才确定,也能更改数组成员的值
     * <p>
     * 反射的应用很多，很多框架都有用到：
     * spring的ioc/di也是反射...javaBean和jsp之间调用也是反射...struts的FormBean和页面之间...也是通过反射调用...JDBC的classForName()也是反射...hibernate的find(Classclazz)也是反射.
     * <p>
     * 参考网址：https://www.iteye.com/blog/uule-1423512
     *
     * @param
     * @return void
     * @author fwh [2021/1/10 && 10:27 下午]
     */
    @org.junit.Test
    public void testReflection() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //实现方式：三种
        System.out.println("------------反射三种实现方式-----------------------");
        People people1 = new People();
        final Class<? extends People> aClass1 = people1.getClass();
        final Class<People> clz = People.class;
        final Class<?> aClass = Class.forName("club.fuwenhao.bean.People");
        if (aClass1 == clz) {
            System.out.println("aClass1==clz");
        }
        if (clz == aClass) {
            System.out.println("clz==aClass");
        }

        Class mclass = Class.forName("club.fuwenhao.bean.UserInfo");
        System.out.println("------------输出类名-----------------------");
        System.out.println(mclass.getName());
        System.out.println(mclass.getSimpleName()); //基础类的简称

        //获取属性(字段)
        System.out.println("------------输出所有属性--------------------");
        Field[] fileds = mclass.getDeclaredFields();  //得到所有的字段，包括公共，保护，默认（包）和私有变量，但不包括继承的字段。
        //Field[] fileds = mclass.getFields();      //得到所有的公共字段。
        for (Field field : fileds) {
            int fieldFangWen = field.getModifiers(); //属性访问权限修饰符
            Class fieldType = field.getType();     //属性类型
            String fieldName = field.getName();    //属性名称
            System.out.println(Modifier.toString(fieldFangWen) + " " + fieldType.getSimpleName() + " " + fieldName);
        }

        //获取方法
        System.out.println("------------输出所有方法--------------------");
        Method[] methods = mclass.getDeclaredMethods();
        //Method[] medthods = mclass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();      //方法名称
            int methodFangWen = method.getModifiers(); //访问修饰符
            Class methodRetrunType = method.getReturnType();//返回类型
            Class[] methodParameter = method.getParameterTypes();//方法的参数列表
            System.out.print(Modifier.toString(methodFangWen) + " " + methodRetrunType.getSimpleName() + " " + methodName + "(");
            for (int k = 0; k < methodParameter.length; k++) {
                String parameterName = methodParameter[k].getSimpleName();
                if (k != methodParameter.length - 1) {
                    System.out.print(parameterName + " arg" + k + ",");
                } else
                    System.out.print(parameterName + " arg" + k);
            }
            System.out.println(");");

        }

        //获取构造
        System.out.println("------------输出所有构造器--------------------");
        Constructor[] constructors = mclass.getConstructors();
        for (Constructor constructor : constructors) {
            String constructorName = constructor.getName();
            Class[] constructorParameter = constructor.getParameterTypes();
            System.out.print(mclass.getSimpleName() + " " + constructorName.substring(constructorName.lastIndexOf(".") + 1, constructorName.length()) + "(");
            for (int h = 0; h < constructorParameter.length; h++) {
                String parameterName = constructorParameter[h].getSimpleName();
                if (h != constructorParameter.length - 1)
                    System.out.print(parameterName + " arg" + h + ",");
                else
                    System.out.print(parameterName + " arg" + h);
            }
            System.out.println(");");

        }

        //如何执行指定的方法
        System.out.println("------------反射执行方法--------------------");
        String name = "getMyInfo";
        Class[] parameterTypes = new Class[3];
        parameterTypes[0] = String.class;
        parameterTypes[1] = String.class;
        parameterTypes[2] = Integer.class;

        Method me = mclass.getDeclaredMethod(name, parameterTypes);
        Object obj = mclass.newInstance();
        Object[] arg = new Object[3];
        arg[0] = "范晶晶";
        arg[1] = "AAA风景区";
        arg[2] = new Integer(18);
        me.invoke(obj, arg);
    }
}
