package com.tuling.designpattern.abstractfactroy;

/**
 * 抽象工厂模式
 * fwh
 *
 * @Slogan 致敬大师，致敬未来的你
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        //切换组件-就可以实现相应的链接和命令

        IDBComponent idbComponent = new MysqlDbComponent(); // config.
//        IDBComponent idbComponent = new OralceDbComponent(); // config.

        IConnection connection = idbComponent.getConnection();
        connection.connection();

        ICommand command = idbComponent.getCommand();
        command.command();

    }
}
//  变化:        mysql , sqlserver , oracle ........
//  c/s(稳定):   connection, command, ......

/**
 * 链接接口
 */
interface IConnection {

    void connection();
}

/**
 * 命令接口
 */
interface ICommand {

    void command();
}

/**
 * 组件接口
 */
interface IDBComponent {

    IConnection getConnection();

    ICommand getCommand();
}

// ------------------

/**
 * mysql实现链接
 */
class MyConnection implements IConnection {

    @Override
    public void connection() {
        System.out.println("mysql: connect.");
    }
}

/**
 * mysql实现命令
 */

class MyCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("mysql: command.");
    }
}

/**
 * mysql实现组件
 */

class MysqlDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new MyConnection();
    }

    @Override
    public ICommand getCommand() {
        return new MyCommand();
    }
}
//--------oracle

/**
 * oracle实现链接
 */

class OracleConnection implements IConnection {


    @Override
    public void connection() {
        System.out.println("oracle:connect.");
    }
}

/**
 * oracle实现命令
 */

class OracleCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("oracle:command.;");
    }
}

/**
 * oracle实现组件
 */

class OralceDbComponent implements IDBComponent {

    @Override
    public IConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public ICommand getCommand() {
        return new OracleCommand();
    }
}















