package william.enhance.mst;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 22:51
 * @Description:基于Prime算法的最小生成树
 */
public class PrimeMST<Weight extends Number & Comparable<Weight>> implements MST {
    //带权图
    private WeightedGraph<Weight> graph;

    //记录每个顶点是否被访问过
    private boolean[] visited;

    //记录最小生成树的权值
    private Number weight;

    //记录最小生成树的所有边
    private List<Edge<Weight>> mst;

    //使用一个最小堆保存所有边
    private PriorityQueue<Edge<Weight>> pq;

    public PrimeMST(WeightedGraph<Weight> graph) {
        //初始化
        this.graph = graph;
        this.weight = 0D;
        this.visited = new boolean[graph.V()];
        this.mst = new ArrayList<>();
        this.pq = new PriorityQueue<>();
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
        }

        //执行Prime算法
        prime();
    }

    private void prime() {
        //首先访问第一个顶点
        visit(0);

        //遍历最小堆,每次取出最小的横切边,加入到最小生成树中
        //结束条件:堆为空或最小生成树已经存在V-1条边
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            //如果当前最小边为横切边,则加入最小生成树中
            Edge<Weight> e = pq.poll();
            if (visited[e.v()] != visited[e.w()]) {
                mst.add(e);
                weight = weight.doubleValue() + e.weight().doubleValue();

                //接着访问新加入的节点
                if (!visited[e.v()]) {
                    visit(e.v());
                } else {
                    visit(e.w());
                }
            }
        }
    }

    //访问一个顶点,并将新产生的横切边放入最小堆中
    private void visit(int v) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;

        //将该顶点新产生的横切边放入最小堆中
        for (Edge<Weight> e : graph.adj(v)) {
            if (!visited[e.other(v)]) {
                pq.offer(e);
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

    public static void main(String[] args) {

        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/mst/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.err.println("Prim MST:");
        MST<Double> mst = new PrimeMST<>(g);
        List<Edge<Double>> edges = mst.edges();
        for (int i = 0; i < edges.size(); i++) {
            System.err.println(edges.get(i));
        }
        System.err.println("The MST weight is: " + mst.weight());
        System.err.println();
    }
}
