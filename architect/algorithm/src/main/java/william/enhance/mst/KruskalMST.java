package william.enhance.mst;

import william.enhance.unionfind.PathCompressedUnionFind;
import william.enhance.unionfind.UnionFind;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 23:13
 * @Description:基于kruskal算法的最小生成树
 */
public class KruskalMST<Weight extends Number & Comparable<Weight>> implements MST {
    //带权图
    private WeightedGraph<Weight> graph;

    //记录最小生成树的权值
    private Number weight;

    //记录最小生成树的所有边
    private List<Edge<Weight>> mst;

    //使用一个最小堆保存所有边
    private PriorityQueue<Edge<Weight>> pq;

    //使用一个并查集进行连通性校验
    private UnionFind uf;

    public KruskalMST(WeightedGraph<Weight> graph) {
        //初始化
        this.graph = graph;
        this.weight = 0D;
        this.mst = new ArrayList<>();
        this.pq = new PriorityQueue<>();
        uf = new PathCompressedUnionFind(graph.V());

        //执行Kruskal算法
        kruskal();
    }

    private void kruskal() {
        //将图所有边保存在最小堆中
        for (int i = 0; i < graph.V(); i++) {
            for (Edge<Weight> e : graph.adj(i)) {
                if (e.v() < e.w()) {
                    pq.offer(e);
                }
            }
        }

        //每次从堆中取出权值最小的边,判断是否可以加入最小生成树中。判断条件:加入后是否成环
        while (!pq.isEmpty() && edges().size() < graph.V() - 1) {
            Edge<Weight> e = pq.poll();
            if (!uf.isConnected(e.v(), e.w())) {
                uf.union(e.v(), e.w());
                edges().add(e);
                weight = weight.doubleValue() + e.weight().doubleValue();
            }
        }
    }

    @Override
    public Number weight() {
        return weight;
    }

    @Override
    public List<Edge<Weight>> edges() {
        return mst;
    }
}
