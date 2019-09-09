package william.ds.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/5 12:45
 * @Description:并查集ADT
 */
public interface UnionFind {
    /**
     * 将p和q两个元素所在的集合连接起来
     */
    void union(int p,int q);

    /**
     * 判断p和q两个元素是否相连
     */
    boolean isConnected(int p,int q);

    /**
     * 查找元素p所在的集合id
     */
    int find(int p);

    /**
     * 获取并查集中连通分量的数量
     */
    int connectedComponentCount();
}
