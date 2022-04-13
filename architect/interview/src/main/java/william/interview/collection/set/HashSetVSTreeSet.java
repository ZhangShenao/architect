package william.interview.collection.set;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * HashSet和TreeSet对比
 * HashSet采用哈希表实现,TreeSet采用红黑树实现
 * HashSet无序,TreeSet基于比较排序
 * HashSet插入和查找的时间复杂度为O(1) TreeSet为O(logn)
 * HashSet允许存放null,而TreeSet则不行
 */
public class HashSetVSTreeSet {
    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<>();
        hs.add(3);
        hs.add(7);
        hs.add(2);
        hs.add(81);
        System.out.println("HashSet: " + hs.stream().map(Object::toString).collect(Collectors.joining(",")));

        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(3);
        ts.add(7);
        ts.add(2);
        ts.add(81);
        System.out.println("TreeSet: " + ts.stream().map(Object::toString).collect(Collectors.joining(",")));

        //TreeSet继承了NavigableSet,具有可浏览的行为
        System.out.println("first: " + ts.first());
        System.out.println("floor: " + ts.floor(4));
        System.out.println("last: " + ts.last());
        System.out.println("ceiling: " + ts.ceiling(4));

        hs.add(null);   //HashSet允许存放null
//        ts.add(null);
    }
}
