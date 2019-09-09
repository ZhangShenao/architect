package william.ds.list;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 10:34
 * @Description:
 */
public class TestList {
    @Test
    public void testList(){
        List<Integer> list = new LinkedList<>();
        for (int i = 0;i < 10;i++){
            list.addLast(i);
        }
        System.err.println("After Add: " + list);

        for (int i = 0;i < list.getSize();i += 2){
            list.remove(i);
        }

        System.err.println("After Remove: " + list);

        for (int i = 0;i < 3;i ++){
            list.removeLast();
        }

        System.err.println("After Remove Last: " + list);

        for (int i = 0;i < 2;i ++){
            list.removeFirst();
        }

        System.err.println("After Remove First: " + list);
    }
}
