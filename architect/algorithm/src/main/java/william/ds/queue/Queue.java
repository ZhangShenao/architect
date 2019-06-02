package william.ds.queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/2 10:12
 * @Description:队列
 */
public interface Queue<E> {
    /**
     * 获取队列中元素的数量
     */
    int size();

    /**
     * 判断队列是否为空
     */
    boolean isEmpty();

    /**
     * 入队
     */
    void offer(E e);

    /**
     * 出队
     */
    E poll();

    /**
     * 获取队头元素(不出队)
     */
    E peek();
}
