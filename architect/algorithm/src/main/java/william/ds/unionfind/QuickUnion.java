package william.ds.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/5 13:03
 * @Description:并查集的高级实现 基于树的结构表示同一个集合的元素
 * 使用数组保存每个元素在树中的父节点
 * 树的根节点的父节点为自己
 * 进行union操作时,只需将两个元素所在的树的根节点连接在一起即可
 * 连接时将高度较矮的树连接到较高的树上,避免树高度过高导致查询效率降低
 * 在find操作时,对树进行路径压缩,每个元素直接连接到根节点上,最大程度上降低树的高度
 */
public class QuickUnion implements UnionFind {
    //使用数组保存每个元素的父节点
    private int[] parents;

    //记录当前并查集中连通分量的数量
    private int connectedComponentCount;

    //使用数组记录每个节点所在的树的高度
    private int[] heights;

    //初始化,时间复杂度O(n)
    public QuickUnion(int n) {
        //初始情况下,每个元素的父节点都是自身
        parents = new int[n];
        heights = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;

            //初始高度=1
            heights[i] = 1;
        }

        //初始连通分量个数=n
        connectedComponentCount = n;
    }

    @Override
    public void union(int p, int q) {
        if (p == q) {
            return;
        }
        checkIndex(p);
        checkIndex(q);

        //找到p和q各自的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        //将高度低的树连接到高的树上
        int pHeight = heights[pRoot];
        int qHeight = heights[qRoot];
        if (pHeight < qHeight) {
            parents[pRoot] = qRoot;
        } else if (qHeight < pHeight) {
            parents[qRoot] = parents[pRoot];
        } else {
            //如果高度相同,则被连接到的树的高度+1
            parents[pRoot] = qRoot;
            heights[qHeight] = qHeight + 1;
        }

        //连通分量数-1
        --connectedComponentCount;
    }

    @Override
    public boolean isConnected(int p, int q) {
        checkIndex(p);
        checkIndex(q);
        if (p == q) {
            return true;
        }
        return (find(p) == find(q));
    }

    //时间复杂度O(logn)
    @Override
    public int find(int p) {
        //找到元素p所在树的根节点
        int root = p;
        while (parents[root] != root) {
            root = parents[root];
        }

        //将p所在的集合的所有元素直接作为根节点的子节点
        while (p != root) {
            int tmp = parents[p];
            parents[p] = root;
            p = tmp;
        }

        return root;
    }

    @Override
    public int connectedComponentCount() {
        return connectedComponentCount;
    }

    private void checkIndex(int idx) {
        if (idx < 0 || idx >= parents.length) {
            throw new IllegalArgumentException("index out of bounds!!");
        }
    }
}
