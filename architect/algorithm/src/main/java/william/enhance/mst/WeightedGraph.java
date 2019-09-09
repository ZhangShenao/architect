package william.enhance.mst;

import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 21:54
 * @Description:带权图 ADT
 */
public interface WeightedGraph<Weight extends Number & Comparable<Weight>> {
    int V();

    int E();

    void addEdge(Edge<Weight> edge);

    boolean hasEdge(int v, int w);

    void print();

    List<Edge<Weight>> adj(int v);
}
