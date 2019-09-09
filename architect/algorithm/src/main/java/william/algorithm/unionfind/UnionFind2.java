package william.algorithm.unionfind;

import org.springframework.util.Assert;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/20 10:32
 * @Description:使用rank和路径压缩优化并查集
 */
public class UnionFind2 {
    private int[] parent;       //记录每个节点的父节点
    private int[] rank;         //记录每个节点的层数
    private int size;

    public UnionFind2(int capacity){
        parent = new int[capacity];
        rank = new int[capacity];
        size = capacity;

        //初始情况下,每个节点的父节点都是自身，且节点层数为1
        for (int i = 0;i < capacity;i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    //查找p所在的根节点
    public int find(int p){
        Assert.isTrue((p >= 0 && p < size),"待查询的元素非法");
        if (find(p) != p){
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    //判断p,q两个元素是否处于同一个集合
    public boolean isConnected(int p,int q){
        return (find(p) == find(q));
    }

    //将p,q两个节点所在的集合,将层数较小的集合指向层数较大的
    public void union(int p,int q){
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }

        int pRank = rank[p];
        int qRank = rank[q];
        if (pRank < qRank){
            parent[pRoot] = qRoot;
        }
        else if (qRank < pRank){
            parent[qRoot] = pRoot;
        }
        else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
