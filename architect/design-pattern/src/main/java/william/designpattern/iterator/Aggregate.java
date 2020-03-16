package william.designpattern.iterator;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 集合接口
 */
public interface Aggregate<E> {
    void add(E e);

    void remove(E e);

    Iterator<E> iterator();
}
