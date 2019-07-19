package william.enhance.heap;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/19 16:21
 * @Description:最大堆接口
 */
public interface MaxHeap<T extends Comparable<T>> {
    int size();

    boolean isEmpty();

    T extractMax();

    void insert(T t);

}
