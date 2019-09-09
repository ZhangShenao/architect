package william.ds.list;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/31 10:00
 * @Description:线性表
 */
public interface List<E> {
    /**
     * 获取线性表中元素数量
     */
    int getSize();

    //判断线性表中是否为空

    /**
     * 判断线性表是否为空
     */
    boolean isEmpty();

    /**
     * 判断线性表中是否包含指定元素
     */
    boolean contains(E e);

    /**
     * 获取线性表中指定元素的索引
     * @return 如果不存在则返回-1
     */
    int indexOf(E e);

    /**
     * 获取线性表中指定索引处的元素
     */
    E get(int index);

    /**
     * 设置指定索引处元素的值
     */
    void set(int index, E e);

    /**
     * 在线性表指定索引处添加元素
     */
    void add(int index, E e);

    /**
     * 删除指定索引处的元素
     */
    E remove(int index);

    /**
     * 在线性表末尾添加元素
     */
    void addLast(E e);

    /**
     * 在线性表头部添加元素
     */
    void addFirst(E e);

    /**
     * 删除线性表头部的元素
     */
    E removeFirst();

    /**
     * 删除线性表尾部的元素
     */
    E removeLast();
}
