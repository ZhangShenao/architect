package william.ds.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/5 12:48
 * @Description:并查集的基础实现
 */
public class QuickFind implements UnionFind {
    //使用数组记录每个元素所在的集合id
    private int[] ids;

    //记录当前并查集中连通分量的数量
    private int connectedComponentCount;

    //初始化,时间复杂度O(n)
    public QuickFind(int n) {
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }

        //初始连通分量个数=n
        connectedComponentCount = n;
    }


    //时间复杂度O(n)
    @Override
    public void union(int p, int q) {
        if (p == q){
            return;
        }
        checkIndex(p);
        checkIndex(q);

        int pId = ids[p];
        int qId = ids[q];
        if (pId == qId){
            return;
        }

        //将p所在的集合连接到q所在的集合上
        for (int i = 0;i < ids.length;i++){
            if (ids[i] == pId){
                ids[i] = qId;
            }
        }

        //连通分量数-1
        --connectedComponentCount;
    }

    @Override
    public boolean isConnected(int p, int q) {
        checkIndex(p);
        checkIndex(q);
        if (p == q){
            return true;
        }
        return (find(p) == find(q));
    }

    //时间复杂度O(1)
    @Override
    public int find(int p) {
        checkIndex(p);
        return ids[p];
    }

    @Override
    public int connectedComponentCount() {
        return connectedComponentCount;
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= ids.length) {
            throw new IllegalArgumentException("index out of bounds!!");
        }
    }
}
