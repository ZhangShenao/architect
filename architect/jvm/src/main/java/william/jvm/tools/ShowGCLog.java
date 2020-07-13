package william.jvm.tools;

import java.util.ArrayList;
import java.util.List;

import william.jvm.allocation.User;

/**
 * @Author zhangshenao
 * @Date 2020-07-09
 * @Description 查看GC日志
 * ﻿CMS: -Xloggc:/Users/zhangshenao/Desktop/logs/gc-cms-%t.log -Xms50M -Xmx50M -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M
 * -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 * -XX:+PrintGCTimeStamps -XX:+PrintGCCause -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M
 * -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 *
 * G1: ﻿-Xloggc:/Users/zhangshenao/Desktop/logs/gc-g1-%t.log -Xms50M -Xmx50M -XX:MetaspaceSize=256M -XX:MaxMetaspaceSize=256M -XX:+PrintGCDetails
 * -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCCause -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+UseG1GC
 */
public class ShowGCLog {
    public static void main(String[] args) throws InterruptedException {
        List<User> users = new ArrayList<>();

        while (true){
            users.add(new User());

            Thread.sleep(50L);
        }
    }
}
