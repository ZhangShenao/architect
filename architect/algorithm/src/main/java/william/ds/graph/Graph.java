package william.ds.graph;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 16:52
 * @Description:图ADT
 */
public interface Graph {
    /**
     * 获取图顶点的数量
     */
    int V();

    /**
     * 获取图边的数量
     */
    int E();

    /**
     * 获取与顶点v相连的所有边
     */
    Iterable<Integer> adj(int v);

    /**
     * 增加一条连接顶点v和w的边
     */
    void addEdge(int v, int w);
}
