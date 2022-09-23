package club.fuwenhao.exception;

/**
 * 自定义异常
 *
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/18 2:03 下午
 */
public class MyException extends Exception {
    public MyException() {
    }

    public MyException(String msg) {
        super(msg);
//        System.out.println("自定义异常内容:"+msg);
    }// ...}

    /**
     * 哪一行哪个单元格报错
     *
     * @param rowNum
     * @param cellNum
     */
    public MyException(String rowNum, String cellNum) {
        super(rowNum + "行" + cellNum + "列");
    }

}
