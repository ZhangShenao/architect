package william.enhance.heap;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/19 16:21
 * @Description:最大堆 ADT
 */
public interface MaxHeap<T extends Comparable<T>> {
    int size();

    boolean isEmpty();

    T extractMax();

    void insert(T t);

}
