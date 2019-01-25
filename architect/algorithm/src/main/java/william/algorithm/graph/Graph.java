package william.algorithm.graph;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 10:10
 * @Description:图的接口
 */
public interface Graph {
    /**
     * 顶点的数量
     */
    int vertexNum();

    /**
     * 边的数量
     */
    int edgeNum();

    /**
     * 添加v-w的一条边
     */
    void addEdge(int v, int w);

    /**
     * 判断是否存在v-w的边
     */
    boolean hasEdge( int v , int w );

    /**
     * 显示整个图
     */
    void show();

    /**
     * 获取顶点v的所有邻边
     */
    Iterable<Integer> adj(int v);

}
