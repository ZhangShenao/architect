package william.enhance.mst;


import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 21:56
 * @Description:稠密带权图——使用邻接矩阵实现
 */
public class DenseWeightedGraph<Weight extends Comparable<Weight>> implements WeightedGraph {
    private int V;
    private int E;
    private boolean directed;
    private Edge<Weight>[][] adj;

    public DenseWeightedGraph(int V, boolean directed) {
        //初始化
        this.V = V;
        this.directed = directed;
        adj = new Edge[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                adj[i][j] = null;
            }
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
        if (hasEdge(edge.v(), edge.w())) {
            return;
        }
        adj[edge.v()][edge.w()] = new Edge<Weight>(edge);
        if (edge.v() != edge.w() && !directed) {
            adj[edge.w()][edge.v()] = new Edge(edge.w(), edge.v(), edge.weight());
        }
        ++E;

    }

    @Override
    public boolean hasEdge(int v, int w) {
        return (adj[v][w] != null);
    }

    @Override
    public void print() {

    }

    @Override
    public List<Edge<Weight>> adj(int v) {
        List<Edge<Weight>> adjs = new LinkedList<>();
        for (Edge<Weight> e : adj[v]) {
            if (e != null) {
                adjs.add(e);
            }
        }
        return adjs;
    }
}
