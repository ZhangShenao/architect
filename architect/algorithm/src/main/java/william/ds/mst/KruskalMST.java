package william.ds.mst;

import william.ds.unionfind.QuickUnion;
import william.ds.unionfind.UnionFind;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 21:30
 * @Description:基于Kruskal算法的最小生成树
 */
public class KruskalMST implements MST {
    //记录最小生成树的所有边
    private List<Edge> edges;

    //记录最小生成树的总权值
    private double weight;

    //使用并查集,记录各个顶点的连通性
    private UnionFind uf;

    //借助小顶堆,保存所有横切边
    private PriorityQueue<Edge> heap;

    public KruskalMST(WeightGraph graph) {
        //初始化
        edges = new LinkedList<>();
        weight = 0D;
        uf = new QuickUnion(graph.V());
        heap = new PriorityQueue<>(Comparator.reverseOrder());

        //进行kruskal算法
        kruskal(graph);
    }

    //kruskal算法,时间复杂度O(E*logE)
    private void kruskal(WeightGraph g) {
        //首先将所有的边放入堆中
        for (Edge e : g.edges()) {
            heap.offer(e);
        }

        //遍历堆
        while (!heap.isEmpty() && edges.size() < g.E() - 1) {
            //每次拿出权值最小的边
            Edge e = heap.poll();

            //判断该边的两个顶点是否已经连通
            int v = e.either();
            int w = e.another(v);

            //如果已经连通,则放弃该边
            if (uf.isConnected(v, w)) {
                continue;
            }

            //边的两个顶点未连通,则该边一定是最小生成树的边
            edges.add(e);
            weight += e.weight();

            //连通两个顶点
            uf.union(v, w);
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return edges;
    }

    @Override
    public double weight() {
        return weight;
    }
}
