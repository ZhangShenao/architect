package william.enhance.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 17:28
 * @Description:基于size优化的并查集 每次执行union操作时, 将size较小的树并到size较大的树上
 */
public class SizeOptimizedUnionFind implements UnionFind {
    //记录每个元素所指向的父元素,根元素指向自身
    private int[] parents;

    //记录每个元素下节点的数量
    private int[] size;

    public SizeOptimizedUnionFind(int capacity) {
        this.parents = new int[capacity];
        this.size = new int[capacity];

        //初始化,每个元素的父元素都为自身,每个元素的size都为1
        for (int i = 0; i < capacity; i++) {
            parents[i] = i;
            size[i] = 1;
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

        //将size较小的树挂到size较大的树下
        if (size[pRoot] < size[qRoot]) {
            parents[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parents[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return (find(p) == find(q));
    }

    @Override
    public String name() {
        return "SizeOptimizedUnionFind";
    }
}
