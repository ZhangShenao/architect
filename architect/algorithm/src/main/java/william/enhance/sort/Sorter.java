package william.enhance.sort;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/17 18:02
 * @Description:
 */
public interface Sorter<T extends Comparable<T>> {
    void sort(T[] arr);

    String name();
}
