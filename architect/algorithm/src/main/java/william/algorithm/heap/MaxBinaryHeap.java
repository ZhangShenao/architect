package william.algorithm.heap;

import org.springframework.util.Assert;
import william.algorithm.util.ArrayUtils;

import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/22 11:53
 * @Description:基于数组实现的最大二叉堆：所有的父节点都不小于其子节点 每次插入和获取元素的时间复杂度均为O(logn)
 * 数组元素从下标1开始存放,即数组可用范围为arr[1,capacity]
 * 对于索引i,其父节点索引parent(i) = i / 2,左子节点的索引leftChild(i) = i * 2,右子节点的索引rightChild(i) = i * 2 + 1,
 */
public class MaxBinaryHeap {
    private int[] data;     //保存堆中元素,下标从1开始
    private int size;       //堆中元素数量
    private int capacity;   //堆的容量

    public MaxBinaryHeap(int capacity) {
        this.capacity = capacity;
        data = new int[capacity + 1];
        this.size = 0;
    }

    /**
     * 通过数组构建一个堆,时间复杂度O(n)
     */
    public MaxBinaryHeap(int[] arr) {
        capacity = arr.length;
        data = new int[capacity + 1];
        size = capacity;
        for (int i = 0; i < capacity; i++) {
            data[i + 1] = arr[i];
        }

        //从堆的第一个非叶子节点开始,依次进行shiftDown操作,完成之后整个数组就满足了堆的性质
        for (int i = firstNonLeafIndex(); i >= 1; i--) {
            shiftDown(i);
        }

    }

    /**
     * 向堆中插入元素
     */
    public void insert(int e) {
        if (isFull()) {
            enlarge();
        }

        //将元素放到数组末尾
        data[++size] = e;

        shiftUp(size);
    }

    /**
     * 弹出堆中最大的元素
     */
    public int popMax() {
        Assert.isTrue(!isEmpty(), "堆为空!!");

        //返回堆的根元素
        int result = data[1];

        //将堆中的最后一个元素放在根的位置,并进行shiftDown操作
        data[1] = data[size--];

        shiftDown(1);

        return result;
    }

    /**
     * 将数组中下标为i的元素逐层上移,直到满足最大二叉堆的条件
     */
    private void shiftUp(int i) {
        while (i > 1 && data[i] > data[parentIndex(i)]) {
            ArrayUtils.swapArrayElement(data, i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    /**
     * 将数组中下标为i的元素逐层下移,直到满足最大堆的条件
     */
    private void shiftDown(int i) {
        //只要data[i]有子节点,则执行循环
        //如果data[i]的子节点中有比data[i]的值大的,则选取最大值作为父节点
        while (leftChildIndex(i) <= size) {
            int maxIndex = i;
            //先比较左子节点
            if (data[leftChildIndex(i)] > data[i]) {
                maxIndex = leftChildIndex(i);
            }

            //如果存在右子节点,则比较右子节点
            if (rightChildIndex(i) <= size && data[rightChildIndex(i)] > data[maxIndex]) {
                maxIndex = rightChildIndex(i);
            }
            if (maxIndex == i) {
                return;
            }
            ArrayUtils.swapArrayElement(data, maxIndex, i);
            i = maxIndex;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    private int parentIndex(int i) {
        return i / 2;
    }

    private int leftChildIndex(int i) {
        return i * 2;
    }

    private int rightChildIndex(int i) {
        return i * 2 + 1;
    }

    /**
     * 扩容数组,将容量变为原来的二倍
     */
    private void enlarge() {
        int[] tmp = Arrays.copyOf(data, capacity * 2);
        data = tmp;
        capacity *= 2;
        tmp = null;
    }

    public void showAsTree() {

        if (size() >= 100) {
            System.out.println("This print function can only work for less than 100 integer");
            return;
        }

        System.out.println("The max heap size is: " + size());
        System.out.println("Data in the max heap: ");
        for (int i = 1; i <= size(); i++) {
            // 我们的print函数要求堆中的所有整数在[0, 100)的范围内
            assert (Integer) data[i] >= 0 && (Integer) data[i] < 100;
            System.out.print(data[i] + " ");
        }
        System.out.println();
        System.out.println();

        int n = size();
        int maxLevel = 0;
        int numberPerLevel = 1;
        while (n > 0) {
            maxLevel += 1;
            n -= numberPerLevel;
            numberPerLevel *= 2;
        }

        int maxLevelNumber = (int) Math.pow(2, maxLevel - 1);
        int curTreeMaxLevelNumber = maxLevelNumber;
        int index = 1;
        for (int level = 0; level < maxLevel; level++) {

            String line1 = new String(new char[maxLevelNumber * 3 - 1]).replace('\0', ' ');

            int curLevelNumber = Math.min(size - (int) Math.pow(2, level) + 1, (int) Math.pow(2, level));
            boolean isLeft = true;
            for (int indexCurLevel = 0; indexCurLevel < curLevelNumber; index++, indexCurLevel++) {
                line1 = putNumberInLine((Integer) data[index], line1, indexCurLevel, curTreeMaxLevelNumber * 3 - 1, isLeft);
                isLeft = !isLeft;
            }
            System.out.println(line1);

            if (level == maxLevel - 1)
                break;

            String line2 = new String(new char[maxLevelNumber * 3 - 1]).replace('\0', ' ');
            for (int indexCurLevel = 0; indexCurLevel < curLevelNumber; indexCurLevel++)
                line2 = putBranchInLine(line2, indexCurLevel, curTreeMaxLevelNumber * 3 - 1);
            System.out.println(line2);

            curTreeMaxLevelNumber /= 2;
        }
    }

    private String putNumberInLine(Integer num, String line, int indexCurLevel, int curTreeWidth, boolean isLeft) {

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int offset = indexCurLevel * (curTreeWidth + 1) + subTreeWidth;
        assert offset + 1 < line.length();
        if (num >= 10)
            line = line.substring(0, offset + 0) + num.toString()
                    + line.substring(offset + 2);
        else {
            if (isLeft)
                line = line.substring(0, offset + 0) + num.toString()
                        + line.substring(offset + 1);
            else
                line = line.substring(0, offset + 1) + num.toString()
                        + line.substring(offset + 2);
        }
        return line;
    }

    private String putBranchInLine(String line, int indexCurLevel, int curTreeWidth) {

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int subSubTreeWidth = (subTreeWidth - 1) / 2;
        int offsetLeft = indexCurLevel * (curTreeWidth + 1) + subSubTreeWidth;
        assert offsetLeft + 1 < line.length();
        int offsetRight = indexCurLevel * (curTreeWidth + 1) + subTreeWidth + 1 + subSubTreeWidth;
        assert offsetRight < line.length();

        line = line.substring(0, offsetLeft + 1) + "/" + line.substring(offsetLeft + 2);
        line = line.substring(0, offsetRight) + "\\" + line.substring(offsetRight + 1);

        return line;
    }

    /**
     * 第一个非叶子节点元素的下标
     */
    private int firstNonLeafIndex() {
        return size / 2;
    }

    public static void main(String[] args) {
        int capacity = 20;
        int[] array = ArrayUtils.generateRandomArray(capacity, 1, 200);
        ArrayUtils.printArray(array);
        MaxBinaryHeap heap = new MaxBinaryHeap(array);
        for (int i = capacity - 1; i >= 0; i--) {
            array[i] = heap.popMax();
        }
        ArrayUtils.printArray(array);
    }

}
