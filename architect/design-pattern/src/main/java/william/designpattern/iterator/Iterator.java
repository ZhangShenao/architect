package william.designpattern.iterator;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 迭代器接口
 */
public interface Iterator<E> {
    E next();

    boolean hasNext();
}
