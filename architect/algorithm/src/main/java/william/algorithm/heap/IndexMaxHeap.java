package william.algorithm.heap;

import org.springframework.util.Assert;
import william.algorithm.util.ArrayUtils;

import java.util.Arrays;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/29 10:11
 * @Description:最大索引堆
 */
public class IndexMaxHeap {
    private int[] data;        //存放最大索引堆的数据[1,size],数据的索引维持用户指定的原顺序
    private int[] indexes;      //存放最大索引堆的索引，i表示元素在堆中的索引，indexes[i]表示元素在原数组中的位置
    private int capacity;
    private int size;

    public IndexMaxHeap(int capacity){
        this.capacity = capacity;
        this.size = 0;
        data = new int[capacity + 1];
        indexes = new int[capacity + 1];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    //向最大索引堆中插入一个新的元素, 新元素的索引为i, 元素为item
    //传入的i对用户而言,是从0索引的
    public void insert(int index,int item){
        //用户传入的索引从0开始，实际堆的索引从1开始
        index += 1;
        Assert.isTrue(size + 1 <= capacity,"Heap Already Full!");
        Assert.isTrue((index >= 1 && index <= capacity),"Illegal Index!");

        //插入数据保持用户指定的原顺序
        data[index] = item;

        //存放索引的位置
        indexes[++size] = index;

        shiftUp(size);
    }

    //获取最大元素
    public int extractMax(){
        int max = data[indexes[1]];
        ArrayUtils.swapArrayElement(indexes,1,indexes[size--]);
        shiftDown(1);
        return max;
    }

    //获取最大元素的索引
    public int extractMaxIndex(){
        int maxIndex = indexes[1] - 1;
        ArrayUtils.swapArrayElement(indexes,1,indexes[size--]);
        shiftDown(1);
        return maxIndex;
    }

    public int getItem(int index){
        index += 1;
        Assert.isTrue((index >= 1 && index <= capacity),"Illegal Index!");
        return data[index];
    }

    //将最大索引堆中索引为i的元素修改为newItem
    public void update(int index,int newValue){
        index += 1;
        Assert.isTrue((index >= 1 && index <= capacity),"Illegal Index!");
        data[index] = newValue;

        //调整元素位置
        for (int i = 1;i <= size;i++){
            // 找到indexes[i] = index, i表示data[index]元素在堆中的位置
            // 之后shiftUp(i), 再shiftDown(i)
            if (indexes[i] == index){
                shiftUp(i);
                shiftDown(i);
                return;
            }
        }
    }

    //对索引进行shiftUp
    private void shiftUp(int i) {
        while (i > 1 && data[indexes[i]] > data[indexes[i / 2]]){
            ArrayUtils.swapArrayElement(indexes,i,i / 2);
            i /= 2;
        }
    }

    private void shiftDown(int i) {
        while (i * 2 <= size){
            //Find Largest Child of Node i
            int max = i * 2;
            if (max + 1 <= size){
                int right = max + 1;
                if (data[indexes[right]] > data[indexes[max]]){
                    max = right;
                }
            }
            if (data[indexes[max]] <= data[indexes[i]]){
                break;
            }
            ArrayUtils.swapArrayElement(indexes,i,max);
            i = max;
        }
    }

    //测试索引堆中的索引数组index
    //注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
    public boolean testIndexes(){

        int[] copyIndexes = new int[size+1];

        for( int i = 0 ; i <= size ; i ++ )
            copyIndexes[i] = indexes[i];

        copyIndexes[0] = 0;
        Arrays.sort(copyIndexes);

        // 在对索引堆中的索引进行排序后, 应该正好是1...count这count个索引
        boolean res = true;
        for( int i = 1 ; i <= size ; i ++ )
            if( copyIndexes[i-1] + 1 != copyIndexes[i] ){
                res = false;
                break;
            }

        if( !res ){
            System.out.println("Error!");
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int N = 1000000;
        IndexMaxHeap indexMaxHeap = new IndexMaxHeap(N);
        for( int i = 0 ; i < N ; i ++ ){
            indexMaxHeap.insert( i , (int)(Math.random()*N) );
        }
        System.err.println(indexMaxHeap.testIndexes());
    }
}
