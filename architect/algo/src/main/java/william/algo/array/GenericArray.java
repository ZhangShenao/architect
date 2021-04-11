package william.algo.array;

import java.util.Arrays;

/**
 * @Author zhangshenao
 * @Date 2021-04-11
 * @Description 泛型数组
 */
public class GenericArray<T> {
    private static final int DEFAULT_CAPACITY = 10; //数组默认长度

    private static final int RESIZE_FACTOR = 2; //数组扩容因子

    //使用数组保存元素
    private T[] values;

    //记录当前数组中元素数量
    private int size;

    /**
     * 创建指定容量的数组
     *
     * @param capacity 数组长度
     */
    public GenericArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("数组容量必须大于0!");
        }

        this.values = (T[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * 创建默认容量的数组
     */
    public GenericArray() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 获取数组容量
     */
    public int getCapacity() {
        return values.length;
    }

    /**
     * 获取数组中的元素数量
     */
    public int count() {
        return size;
    }

    /**
     * 判断数组是否为空
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * 设置指定下标处的元素值
     *
     * @param index 下标
     * @param value 元素值
     */
    public void set(int index, T value) {
        checkIndex(index);
        values[index] = value;
    }

    /**
     * 获取指定下标处的元素
     *
     * @param index 下标
     * @return 元素值
     */
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    /**
     * 判断数组中是否包含指定元素
     *
     * @param value 目标元素
     * @return 是否包含
     */
    public boolean contains(T value) {
        if (isEmpty()) {
            return false;
        }
        return Arrays.asList(values).contains(value);
    }

    /**
     * 查找指定元素的下标
     *
     * @param value 目标元素
     * @return 如果找到，则返回对应的下标，否则返回-1
     */
    public int find(T value) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在指定位置插入元素
     * 因为涉及到数组的扩容和元素的迁移，整体时间复杂度为O(n+m)
     *
     * @param index 插入位置
     * @param value 插入元素
     */
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("非法的数组下标: " + index);
        }

        //如果数组已满，则先进行扩容，扩容的时间复杂度为O(n)
        if (size == values.length) {
            resize(values.length * RESIZE_FACTOR);
        }

        //将index位置后的元素全部后移一位，移动的时间复杂度为O(m)
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }

        //在index位置插入新元素
        values[index] = value;

        //修改size记录
        ++size;
    }

    /**
     * 在数组头部插入元素
     *
     * @param value 待插入元素
     */
    public void addFirst(T value) {
        add(0, value);
    }

    /**
     * 在数组尾部插入元素
     *
     * @param value 待插入元素
     */
    public void addLast(T value) {
        add(size, value);
    }

    /**
     * 删除指定位置的元素
     * 因为涉及到数组元素的移动，时间复杂度为O(m)
     *
     * @param index 要删除的下标
     */
    public void remove(int index) {
        checkIndex(index);

        if (isEmpty()) {
            return;
        }

        //将从index位置开始的元素依次前移一位，时间复杂度为O(m)
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }

        //释放数组末尾的空间，并修改size记录
        values[size--] = null;

        //TODO 暂不考虑缩容
    }

    /**
     * 删除数组头部元素
     */
    public void removeFirst() {
        remove(0);
    }

    /**
     * 删除数组尾部元素
     */
    public void removeLast() {
        remove(size - 1);
    }

    /**
     * 删除指定元素
     *
     * @param value 待删除的元素
     */
    public void removeElement(T value) {
        int index = find(value);
        if (index >= 0) {
            remove(index);
        }
    }

    /**
     * 打印数组
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d %n", size, values.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(values[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }

    /**
     * 校验数组下标
     *
     * @param index 下标
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("非法的数组下标: " + index);
        }
    }

    /**
     * 数组扩容
     * 涉及到数组元素的拷贝，时间复杂度O(n)
     *
     * @param capacity 扩容后的容量
     */
    private void resize(int capacity) {
        //创建新的临时数组
        T[] tmp = (T[]) new Object[capacity];

        //数组拷贝
        for (int i = 0; i < size; i++) {
            tmp[i] = values[i];
        }

        //将赋值新数组
        values = tmp;
    }
}
