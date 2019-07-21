package william.enhance.unionfind;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 17:05
 * @Description:并查集ADT
 */
public interface UnionFind {
    /**
     * 查找一个元素所在的集合
     */
    int find(int p);

    /**
     * 将元素p和q所在的集合连接起来
     */
    void union(int p, int q);

    /**
     * 判断元素p和q是否相连
     */
    boolean isConnected(int p, int q);

    String name();
}
