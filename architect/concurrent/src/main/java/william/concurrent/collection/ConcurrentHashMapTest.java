package william.concurrent.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/2/22 11:18
 * @Description:
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");

        //调用put方法,key相同时会覆盖,并返回之前被覆盖的value
        System.err.println("调用put,被覆盖的value: " + map.put("k1","vv1"));

        //调用putIfAbsent,如果不存在key,则放入map,并返回null;否则返回之前的value,并不会进行覆盖
        System.err.println("调用putIfAbsent,返回: " + map.putIfAbsent("k1","vvv1"));

        for (Map.Entry<String,String> entry : map.entrySet()){
            System.err.println(entry.getKey() + "-->" + entry.getValue());
        }
    }
}
