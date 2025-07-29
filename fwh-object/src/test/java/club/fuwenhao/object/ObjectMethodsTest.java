package club.fuwenhao.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.Test;

import java.util.Objects;

/**
 * Object 相关方法测试
 * 测试 Object 类的基础方法：equals、hashCode、toString、clone 等
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2024/07/29
 */
public class ObjectMethodsTest {

    /**
     * 测试 Object 基础方法
     */
    @Test
    public void testObjectBasicMethods() {
        // 创建对象
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = obj1;

        // equals 测试
        System.out.println("=== equals 测试 ===");
        System.out.println("obj1.equals(obj2): " + obj1.equals(obj2)); // false
        System.out.println("obj1.equals(obj3): " + obj1.equals(obj3)); // true
        System.out.println("obj1 == obj3: " + (obj1 == obj3)); // true

        // hashCode 测试
        System.out.println("\n=== hashCode 测试 ===");
        System.out.println("obj1.hashCode(): " + obj1.hashCode());
        System.out.println("obj2.hashCode(): " + obj2.hashCode());
        System.out.println("obj3.hashCode(): " + obj3.hashCode());

        // toString 测试
        System.out.println("\n=== toString 测试 ===");
        System.out.println("obj1.toString(): " + obj1.toString());
        System.out.println("obj2.toString(): " + obj2.toString());

        // getClass 测试
        System.out.println("\n=== getClass 测试 ===");
        System.out.println("obj1.getClass(): " + obj1.getClass());
        System.out.println("obj1.getClass().getName(): " + obj1.getClass().getName());
    }

    /**
     * 测试 Objects 工具类方法
     */
    @Test
    public void testObjectsUtilityMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = null;
        String str4 = null;

        System.out.println("=== Objects.equals 测试 ===");
        System.out.println("Objects.equals(str1, str2): " + Objects.equals(str1, str2)); // true
        System.out.println("Objects.equals(str1, str3): " + Objects.equals(str1, str3)); // false
        System.out.println("Objects.equals(str3, str4): " + Objects.equals(str3, str4)); // true

        System.out.println("\n=== Objects.hashCode 测试 ===");
        System.out.println("Objects.hashCode(str1): " + Objects.hashCode(str1));
        System.out.println("Objects.hashCode(str3): " + Objects.hashCode(str3)); // 0

        System.out.println("\n=== Objects.toString 测试 ===");
        System.out.println("Objects.toString(str1): " + Objects.toString(str1));
        System.out.println("Objects.toString(str3): " + Objects.toString(str3)); // "null"
        System.out.println("Objects.toString(str3, 'default'): " + Objects.toString(str3, "default"));

        System.out.println("\n=== Objects.isNull / nonNull 测试 ===");
        System.out.println("Objects.isNull(str1): " + Objects.isNull(str1)); // false
        System.out.println("Objects.isNull(str3): " + Objects.isNull(str3)); // true
        System.out.println("Objects.nonNull(str1): " + Objects.nonNull(str1)); // true
        System.out.println("Objects.nonNull(str3): " + Objects.nonNull(str3)); // false

        System.out.println("\n=== Objects.requireNonNull 测试 ===");
        try {
            Objects.requireNonNull(str1, "str1 不能为空");
            System.out.println("str1 非空验证通过");
        } catch (NullPointerException e) {
            System.out.println("str1 验证失败: " + e.getMessage());
        }

        try {
            Objects.requireNonNull(str3, "str3 不能为空");
        } catch (NullPointerException e) {
            System.out.println("str3 验证失败: " + e.getMessage());
        }
    }

    /**
     * 测试自定义类的 equals 和 hashCode
     */
    @Test
    public void testCustomObjectEqualsAndHashCode() {
        System.out.println("=== 自定义类测试 ===");

        // 使用 Lombok 注解的类
        Person person1 = new Person("张三", 25);
        Person person2 = new Person("张三", 25);
        Person person3 = new Person("李四", 30);

        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        System.out.println("person3: " + person3);

        System.out.println("\nperson1.equals(person2): " + person1.equals(person2)); // true
        System.out.println("person1.equals(person3): " + person1.equals(person3)); // false
        System.out.println("person1 == person2: " + (person1 == person2)); // false

        System.out.println("\nperson1.hashCode(): " + person1.hashCode());
        System.out.println("person2.hashCode(): " + person2.hashCode());
        System.out.println("person3.hashCode(): " + person3.hashCode());

        // 手动实现的类
        Student student1 = new Student("小明", 20);
        Student student2 = new Student("小明", 20);
        Student student3 = new Student("小红", 18);

        System.out.println("\n=== 手动实现的类测试 ===");
        System.out.println("student1: " + student1);
        System.out.println("student2: " + student2);
        System.out.println("student3: " + student3);

        System.out.println("\nstudent1.equals(student2): " + student1.equals(student2)); // true
        System.out.println("student1.equals(student3): " + student1.equals(student3)); // false

        System.out.println("\nstudent1.hashCode(): " + student1.hashCode());
        System.out.println("student2.hashCode(): " + student2.hashCode());
        System.out.println("student3.hashCode(): " + student3.hashCode());
    }

    /**
     * 测试对象克隆
     */
    @Test
    public void testObjectClone() {
        System.out.println("=== 对象克隆测试 ===");

        CloneableStudent original = new CloneableStudent("王五", 22);
        System.out.println("原始对象: " + original);

        try {
            CloneableStudent cloned = (CloneableStudent) original.clone();
            System.out.println("克隆对象: " + cloned);

            System.out.println("original == cloned: " + (original == cloned)); // false
            System.out.println("original.equals(cloned): " + original.equals(cloned)); // true

            // 修改克隆对象
            cloned.setName("赵六");
            System.out.println("\n修改后:");
            System.out.println("原始对象: " + original);
            System.out.println("克隆对象: " + cloned);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 Lombok 的 Person 类
     */
    @Data
    @AllArgsConstructor
    public static class Person {
        private String name;
        private int age;
    }

    /**
     * 手动实现 equals 和 hashCode 的 Student 类
     */
    public static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Student student = (Student) obj;
            return age == student.age && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', age=" + age + '}';
        }

        // getter 和 setter
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }

    /**
     * 实现 Cloneable 的学生类
     */
    public static class CloneableStudent implements Cloneable {
        private String name;
        private int age;

        public CloneableStudent(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CloneableStudent that = (CloneableStudent) obj;
            return age == that.age && Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "CloneableStudent{name='" + name + "', age=" + age + '}';
        }

        // getter 和 setter
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}
