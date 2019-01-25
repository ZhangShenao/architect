package william.algorithm.weightedgraph;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/26 10:25
 * @Description:有权图接口
 */
public interface WeightedGraph<Weight extends Number & Comparable> {
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
    void addEdge(Edge<Weight> edge);

    /**
     * 判断是否存在v-w的边
     */
    boolean hasEdge(int v, int w);

    /**
     * 显示整个图
     */
    void show();

    /**
     * 获取顶点v的所有邻边
     */
    Iterable<Edge<Weight>> adj(int v);
}
