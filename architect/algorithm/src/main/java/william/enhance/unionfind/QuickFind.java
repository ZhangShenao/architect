package william.enhance.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 17:07
 * @Description:简单的并查集,find和isConnected的时间复杂度为O(1),union的时间复杂度为O(n)
 */
public class QuickFind implements UnionFind {
    //记录每个元素所在的集合id
    private int[] ids;

    //记录并查集中元素数量
    private int capacity;

    public QuickFind(int capacity) {
        this.capacity = capacity;
        this.ids = new int[capacity];

        //初始化,每个元素为一个单独的集合
        for (int i = 0; i < capacity; i++) {
            ids[i] = i;
        }
    }

    @Override
    public int find(int p) {
        return ids[p];
    }

    @Override
    public void union(int p, int q) {
        int pId = ids[p];
        int qId = ids[q];

        if (pId == qId) {
            return;
        }

        //O(n)
        for (int i = 0; i < capacity; i++) {
            if (ids[i] == pId) {
                ids[i] = qId;
            }
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return (ids[p] == ids[q]);
    }

    @Override
    public String name() {
        return "QuickFind";
    }
}
