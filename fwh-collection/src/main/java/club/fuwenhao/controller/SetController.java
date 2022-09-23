package club.fuwenhao.controller;

import club.fuwenhao.bean.UserInfo;

import java.util.Comparator;

/**
 * @author fwh
 * @email fuwenhao594@163.com
 * @date 2021/1/12 10:31 下午
 */
public class SetController implements Comparator<UserInfo> {
    @Override
    public int compare(UserInfo o1, UserInfo o2) {
        // 排序规则描述如下
        // 按照姓名的长度排序,长度短的排在前面,长度长的排在后面
        // 如果姓名的长度相同，按字典顺序比较String
        // 如果姓名完全相同,按年龄排序,年龄小的排在前面,年龄大的排在后面
        int orderByNameLength = o1.getName().length() - o2.getName().length();
        int orderByName = orderByNameLength == 0 ? o1.getName().compareTo(o2.getName()) : orderByNameLength;
        int orderByAge = orderByName == 0 ? o1.getAge() - o2.getAge() : orderByName;
        return orderByAge;
    }

}
