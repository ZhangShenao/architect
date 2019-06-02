package william.ds.stack;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 09:46
 * @Description:栈
 */
public interface Stack<E> {
    /**
     * 判断栈是否为空
     */
    boolean isEmpty();

    /**
     * 获取栈中元素的数量
     */
    int size();

    /**
     * 入栈
     */
    void push(E e);

    /**
     * 出栈
     */
    E pop();

    /**
     * 获取栈顶元素(不出栈)
     */
    E peek();
}
