package william.ds.stack;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 09:59
 * @Description:
 */
public class TestStack {
    @Test
    public void testStack(){
        Stack<Integer> stack = new LinkedStack<>();
        for (int i = 0;i < 100;i++){
            stack.push((i + 1));
        }

        while (!stack.isEmpty()){
            System.err.println("data: " + stack.pop() + ", size: " + stack.size());
        }
    }
}
