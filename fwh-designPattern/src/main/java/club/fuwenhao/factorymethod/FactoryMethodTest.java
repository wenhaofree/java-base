package club.fuwenhao.factorymethod;

/**
 * 抽象工厂方法
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        //替换-生成的产品就可以
//        Application application = new ConcreteProductB();
        Application application = new ConcreteProductA();
        Product product = application.getObject();
        product.method1();

    }
}

/**
 * 接口：公共方法
 */
interface Product {
    void method1();
}

/**
 * 产品A
 */
class ProductA implements Product {

    @Override
    public void method1() {
        System.out.println("ProductA.method1 executed.");
    }
}

/**
 * 产品B
 */
class ProductB implements Product {

    @Override
    public void method1() {
        System.out.println("ProductB.method1 executed.");
    }
}


/**
 * 简单工厂
 */
class SimpleFactory {
    public static Product createProduct(String type) {
        if ("A".equals(type)) {
            return new ProductA();
        }
        return null;
    }
}

/**
 * 变化 ， 共同点
 */
abstract class Application {

    // 工厂方法
    public abstract Product createProduct();

    public Product getObject() {

        Product product = createProduct();
        // ......
        return product;
    }
}

/**
 * 创建A
 */
class ConcreteProductA extends Application {

    @Override
    public Product createProduct() {
        ProductA productA = new ProductA();
        // ...
        return productA;
    }
}

/**
 * 创建B
 */
class ConcreteProductB extends Application {

    @Override
    public Product createProduct() {
        ProductB productB = new ProductB();
        return productB;
    }
}
