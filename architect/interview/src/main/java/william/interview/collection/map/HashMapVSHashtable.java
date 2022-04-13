package william.interview.collection.map;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * HashMap和Hashtable
 * Hashtable是线程安全的Map,而HashMap不是
 * Hashtable继承了Dictionary,因此不能保存null key；而HashMap可以保存null key
 */
public class HashMapVSHashtable {
    public static void main(String[] args) {
        Hashtable<String, String> ht = new Hashtable<>();
//        ht.put(null,null);    //Hashtable继承了Dictionary,因此不能保存null key

        HashMap<String, String> hm = new HashMap<>();
        hm.put(null,null);
        hm.put(null,null);
    }
}
