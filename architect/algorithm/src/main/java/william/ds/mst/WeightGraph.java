package william.ds.mst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 08:21
 * @Description:加权图ADT
 */
public interface WeightGraph {
    /**
     * 获取图的顶点数
     */
    int V();

    /**
     * 获取图的边数
     */
    int E();

    /**
     * 向图中添加一条边
     */
    void addEdge(Edge edge);

    /**
     * 获取顶点v的所有边
     */
    Iterable<Edge> adj(int v);

    /**
     * 获取图的所有边
     */
    Iterable<Edge> edges();
}
