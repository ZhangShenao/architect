package william.ds.mst;

import java.util.*;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 20:48
 * @Description:基于Prime算法的最小生成树
 */
public class PrimeMST implements MST {
    //记录最小生成树的所有边
    private List<Edge> edges;

    //记录最小生成树的总权值
    private double weight;

    //记录已经访问过的顶点
    private List<Integer> visitedVertex;

    //借助小顶堆,保存所有横切边
    private PriorityQueue<Edge> heap;


    public PrimeMST(WeightGraph graph) {
        //初始化
        edges = new LinkedList<>();
        weight = 0D;
        visitedVertex = new LinkedList<>();
        heap = new PriorityQueue<>(Comparator.reverseOrder());

        //从一个顶点开始,使用Prime算法
        prime(graph, 0);

    }

    //Prime算法,时间复杂度：O(E*logE)
    private void prime(WeightGraph graph, int s) {
        //首先访问起点
        visitVertex(graph, s);

        //从堆中获取权值最小的横切边,即为最小生成树的边
        while (!heap.isEmpty() && visitedVertex.size() < graph.V() && edges.size() < graph.E() - 1) {
            //获取最小边,并判断最小边是否为横切边
            Edge e = heap.poll();
            int v = e.either();
            int w = e.another(v);

            boolean visitedV = visitedVertex.contains(v);
            boolean visitedW = visitedVertex.contains(w);
            if (visitedV && visitedW) {
                continue;
            }
            if (!visitedV && !visitedW) {
                continue;
            }

            //将边加入最小生成树中
            edges.add(e);

            //更新权值
            weight += e.weight();

            //尝试访问该横切边的两个顶点
            visitVertex(graph, v);
            visitVertex(graph, w);

        }
    }

    //访问一个顶点v,并将其所有的横切边保存到堆中
    private void visitVertex(WeightGraph graph, int v) {
        if (visitedVertex.contains(v)) {
            return;
        }
        visitedVertex.add(v);
        for (Edge e : graph.adj(v)) {
            if (!visitedVertex.contains(e.another(v))) {
                heap.offer(e);
            }
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
