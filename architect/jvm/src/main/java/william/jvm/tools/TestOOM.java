package william.jvm.tools;

import java.util.ArrayList;
import java.util.List;

import william.jvm.allocation.User;

/**
 * @Author zhangshenao
 * @Date 2020-07-05
 * @Description 程序OOM后, 自动dump堆内存信息
 * <p>
 * ﻿-Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -XX:HeapDumpPath=/Users/zhangshenao/Desktop/test.dump
 */
public class TestOOM {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        while (true) {
            users.add(new User());
        }
    }
}
