package william.enhance.mst;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 22:31
 * @Description:稀疏带权图——使用邻接表实现
 */
public class SparseWeightedGraph<Weight extends Number & Comparable<Weight>> implements WeightedGraph {
    private int V;
    private int E;
    private boolean directed;
    private List<Edge<Weight>>[] adj;

    public SparseWeightedGraph(int V, boolean directed) {
        //初始化
        this.V = V;
        this.directed = directed;
        this.adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public void addEdge(Edge edge) {
        adj[edge.v()].add(new Edge<>(edge));
        if (edge.v() != edge.w() && !directed) {
            adj[edge.w()].add(new Edge(edge.w(), edge.v(), edge.weight()));
        }
        ++E;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        for (Edge<Weight> edge : adj[v]) {
            if (edge.other(v) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        for (int i = 0; i < V; i++) {
            System.err.print("vertex " + i + ":\t");
            for (int j = 0; j < adj[i].size(); j++) {
                Edge<Weight> e = adj[i].get(j);
                System.err.print("( to:" + e.other(i) + ",wt:" + e.weight() + ")\t");
            }
            System.err.println();
        }
    }

    @Override
    public List<Edge<Weight>> adj(int v) {
        return adj[v];
    }
}
