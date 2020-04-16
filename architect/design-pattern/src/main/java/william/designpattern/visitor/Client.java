package william.designpattern.visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description 访问者模式: 封装一些作用于某种数据结构中的各元素的操作,它可以在不改变数据结构的前提下,定义作用于这些元素的新操作
 * 核心: 将具体的数据结构与作用在其上的操作解耦
 */
public class Client {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            String key = "" + i % 100;
            String value = "" + i;
            map.computeIfAbsent(key, s -> {
                map.put(key, value);
                return value;
            });
        }

        System.err.println(map.size());

    }
}
