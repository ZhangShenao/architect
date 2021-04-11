package william.algo.array;

/**
 * @Author zhangshenao
 * @Date 2021-04-11
 * @Description 测试GenericArray
 */
public class TestGenericArray {
    public static void main(String[] args) {
        GenericArray<Integer> arr = new GenericArray<>();

        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }

        System.out.println("初始化：" + arr);

        arr.addFirst(100);
        arr.addFirst(99);
        arr.addLast(-199);
        arr.addLast(-100);
        arr.add(5, 756);
        arr.add(7, 876);

        System.out.println("插入元素后：" + arr);

        arr.set(0, 1);
        arr.set(8, 90);

        System.out.println("修改元素后：" + arr);

        arr.remove(9);
        arr.remove(8);

        System.out.println("删除元素后：" + arr);
    }
}
