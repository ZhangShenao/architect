package william.ds.mst;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 08:26
 * @Description:基于邻接表实现的带权图
 */
public class AdjListWeightedGraph implements WeightGraph {
    //顶点数,不可变
    private final int V;

    //边数
    private int E;

    //使用邻接表(链表数组)记录每个顶点的边
    private final List<Edge>[] adj;

    public AdjListWeightedGraph(int V) {
        //初始化
        if (V <= 0) {
            throw new IllegalArgumentException("vertex num must be positive!!");
        }
        this.V = V;
        this.E = 0;
        adj = (List<Edge>[]) new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
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
        int v = edge.either();
        int w = edge.another(v);
        checkVertexIdx(v);
        checkVertexIdx(w);
        adj[v].add(edge);
        adj[w].add(edge);
        ++E;
    }

    @Override
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    @Override
    public Iterable<Edge> edges() {
        List<Edge> edges = new LinkedList<>();
        for (int i = 0;i < V;i++){
            if (adj[i].size() > 0){
                edges.addAll(adj[i]);
            }
        }
        return edges;
    }

    private void checkVertexIdx(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex index must int [0," + V + ") !!");
        }
    }
}
