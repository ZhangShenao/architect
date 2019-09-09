package william.enhance.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 18:15
 * @Description:稠密图——使用邻接矩阵表示
 */
public class DenseGraph implements Graph {
    //使用邻接矩阵记录图的边
    private boolean[][] adj;

    //顶点数
    private int V;

    //边数
    private int E;

    //是否为有向图
    boolean directed;

    public DenseGraph(int V, boolean directed) {
        this.V = V;
        this.directed = directed;
        this.adj = new boolean[V][V];
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
        if (hasEdge(v, w)) {
            return;
        }
        adj[v][w] = true;
        if (!directed) {
            adj[w][v] = true;
        }
        ++E;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return adj[v][w];
    }

    @Override
    public void print() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                System.err.print(adj[i][j] + "\t");
            System.err.println();
        }
    }

    @Override
    public List<Integer> adjs(int v) {
        List<Integer> adjs = new ArrayList<>();
        for (int i = 0; i < adj[v].length; i++) {
            if (i != v && adj[v][i]) {
                adjs.add(i);
            }
        }
        return adjs;
    }
}
