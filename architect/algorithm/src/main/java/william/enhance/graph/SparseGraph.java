package william.enhance.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 18:26
 * @Description:稀疏图——使用邻接表表示
 */
public class SparseGraph implements Graph {
    //使用邻接表记录图的边
    private List<Integer>[] adj;

    //记录图的顶点数
    private int V;

    //记录图的边数
    private int E;

    //是否为有向图
    private boolean directed;

    public SparseGraph(int V, boolean directed) {
        this.V = V;
        this.directed = directed;
        adj = new List[V];

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
    public void addEdge(int v, int w) {
        adj[v].add(w);
        if (!directed) {
            adj[w].add(v);
        }
        ++E;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        List<Integer> adjs = adj[v];
        for (int i : adjs) {
            if (i == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void print() {
        for (int i = 0; i < V; i++) {
            System.err.print("vertex " + i + ":\t");
            for (int j = 0; j < adj[i].size(); j++)
                System.err.print(adj[i].get(j) + "\t");
            System.err.println();
        }
    }

    @Override
    public List<Integer> adjs(int v) {
        return adj[v];
    }
}
