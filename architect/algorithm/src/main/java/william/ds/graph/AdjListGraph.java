package william.ds.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 17:27
 * @Description:图的连接表实现
 */
public class AdjListGraph implements Graph {
    //顶点数,不可变
    private final int V;

    //边数
    private int E;

    //使用链表数组,记录与每个顶点相连的顶点
    private List<Integer>[] adj;

    public AdjListGraph(int v) {
        //初始化
        this.V = v;
        this.E = 0;
        this.adj = new List[v];

        for (int i = 0; i < v; i++) {
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
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public void addEdge(int v, int w) {
        if (adj[v].contains(w)) {
            return;
        }
        adj[v].add(w);
        adj[w].add(v);
        ++E;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj(v)) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }

        return s.toString();
    }

}
