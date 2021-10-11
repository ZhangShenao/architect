package william.algo.array;

import java.util.Arrays;

import org.omg.CORBA.Object;

/**
 * @Author zhangshenao
 * @Date 2021-10-11
 * @Description 泛型数组
 */
public class GenericArray<T> {
    private static final int DEFAULT_CAPACITY = 10; //数组默认容量
    private static final int RESIZE_FACTOR = 2; //扩容因子

    private T[] arr;    //使用数组保存元素
    private int size;   //记录数组中元素数量

    //构造指定容量的泛型数组
    public GenericArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be posotive!!");
        }

        this.arr = (T[]) new Object[capacity];
        this.size = 0;
    }

    //构造默认容量的泛型数组
    public GenericArray() {
        this(DEFAULT_CAPACITY);
    }

    //获取数组容量
    public int capacity() {
        return arr.length;
    }

    //获取数组中元素的数量
    public int size() {
        return this.size;
    }

    //判断数组是否为空
    public boolean isEmpty() {
        return (size == 0);
    }

    //修改index位置的元素
    public void set(int index, T value) {
        checkIndex(index);
        arr[index] = value;
    }

    //获取index位置的元素
    public T get(int index) {
        checkIndex(index);
        return arr[index];
    }

    //判断数组中是否包含某元素
    public boolean contains(T value) {
        return Arrays.stream(arr).anyMatch(x -> x.equals(value));
    }

    //查找指定元素,返回元素所在的下标。如果未找到,则返回-1
    public int find(T value) {
        if (isEmpty()) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    //在指定下标处插入元素。时间复杂度O(n)
    public void add(int index, T value) {
        //校验下标
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal index!!");
        }

        //扩容处理
        if (size == arr.length) {
            resize(arr.length * RESIZE_FACTOR);
        }

        //移动元素
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }

        //插入元素
        arr[index] = value;

        //修改size
        ++size;
    }

    //在数组头部插入元素
    public void addFirst(T value) {
        add(0, value);
    }

    //在数组末尾插入元素
    public void addLast(T value) {
        add(size, value);
    }

    //删除指定下标处的元素并返回。时间复杂度O(n)
    public T remove(int index) {
        checkIndex(index);

        //这里暂不考虑缩容
        T value = arr[index];

        //移动元素
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }

        //修改size
        --size;

        //释放内存空间
        arr[size] = null;

        return value;
    }

    //删除数组头部元素
    public T removeFirst() {
        return remove(0);
    }

    //删除数组末尾元素
    public T removeLast() {
        return remove(size - 1);
    }

    //从数组中删除指定元素
    public void removeElement(T value) {
        int index = find(value);
        if (index >= 0) {
            remove(index);
        }
    }

    //打印数组
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d, capacity = %d \n", size, arr.length));
        builder.append('[');
        for (int i = 0; i < size; i++) {
            builder.append(arr[i]);
            if (i != size - 1) {
                builder.append(", ");
            }
        }
        builder.append(']');
        return builder.toString();
    }


    //对数组进行扩缩容操作
    private void resize(int capacity) {
        T[] tmp = (T[]) new java.lang.Object[capacity];
        for (int i = 0; i < size; i++) {
            tmp[i] = arr[i];
        }
        this.arr = tmp;
    }


    //校验下标是否合法
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Illegal index!!");
        }
    }


}
