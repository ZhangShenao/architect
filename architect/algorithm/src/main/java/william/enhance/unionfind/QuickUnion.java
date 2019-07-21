package william.enhance.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 17:20
 * @Description:基于树结构的并查集
 */
public class QuickUnion implements UnionFind {
    //记录每个元素所指向的父元素,根元素指向自身
    private int[] parents;

    public QuickUnion(int capacity) {
        this.parents = new int[capacity];

        //初始化,每个元素的父元素都为自身
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
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

        parents[pRoot] = qRoot;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return (find(p) == find(q));
    }

    @Override
    public String name() {
        return "QuickUnion";
    }
}
