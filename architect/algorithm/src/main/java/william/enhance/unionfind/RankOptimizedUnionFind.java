package william.enhance.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 17:36
 * @Description:基于rank优化的并查集 每次执行union操作时, 将层数较小的树并到层数较大的树上
 */
public class RankOptimizedUnionFind implements UnionFind {
    //记录每个元素所指向的父元素,根元素指向自身
    private int[] parents;

    //记录每个元素所在树的层数
    private int[] rank;

    public RankOptimizedUnionFind(int capacity) {
        this.parents = new int[capacity];
        this.rank = new int[capacity];

        //初始化,每个元素的父元素都为自身,每个元素的size都为1
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int find(int p) {
        //找到元素p所在的树的根节点
        while (p != parents[p]) {
            p = parents[p];
        }
        return p;
    }

    @Override
    public void union(int p, int q) {
        //找到两元素所在的树的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        //将rank较小的树挂到rank较大的树下
        if (rank[pRoot] < rank[qRoot]) {
            parents[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            parents[qRoot] = pRoot;
        }

        //当两棵树rank相等时,需要将rank+1
        else {
            parents[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return (find(p) == find(q));
    }

    @Override
    public String name() {
        return "RankOptimizedUnionFind";
    }
}
