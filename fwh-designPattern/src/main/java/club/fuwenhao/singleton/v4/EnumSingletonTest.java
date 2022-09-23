package club.fuwenhao.singleton.v4;

import java.io.*;

/**
 * 枚举形式单例
 *
 * @author 腾讯课堂-图灵学院  郭嘉
 * @Slogan 致敬大师，致敬未来的你
 */
public class EnumSingletonTest {
    public static void main(String[] args) throws IOException {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance1=EnumSingleton.INSTANCE;
        System.out.println("是否为同一实例："+(instance==instance1));
        //输出磁盘
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EnumSingleton"))) {
//            oos.writeObject(instance);
//            System.out.println("输出实例");
//        }
        //磁盘加载
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("EnumSingleton"))) {
//            EnumSingleton object = ((EnumSingleton) ois.readObject());
//            System.out.println("加载实例："+(object == instance));
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }


    }
}

enum EnumSingleton {
    INSTANCE;

    public void print() {
        System.out.println(hashCode());
    }

}
