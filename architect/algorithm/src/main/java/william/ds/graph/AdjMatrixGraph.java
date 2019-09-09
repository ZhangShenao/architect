package william.ds.graph;

import java.util.Iterator;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 16:55
 * @Description:图的连接矩阵实现
 */
public class AdjMatrixGraph implements Graph {
    //顶点数,不可变
    private final int V;

    //边数
    private int E;

    //使用二维矩阵记录各顶点之间的连接状态
    private final boolean[][] adj;

    public AdjMatrixGraph(int v) {
        this.V = v;
        adj = new boolean[v][v];
        this.E = 0;
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
        return new AdjIterator(v);
    }

    @Override
    public void addEdge(int v, int w) {
        if (adj[v][w]) {
            return;
        }
        adj[v][w] = true;
        adj[w][v] = true;
        ++E;
    }

    //顶点的边的迭代器
    private class AdjIterator implements Iterable<Integer>, Iterator<Integer> {
        //记录需要迭代的顶点
        private final int v;

        //记录当前遍历到的索引
        private int w;

        public AdjIterator(int v) {
            this.v = v;
            this.w = 0;
        }

        @Override
        public Iterator<Integer> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            //定位到第一个索引
            while (w < V) {
                if (adj[v][w]) {
                    return true;
                }
                ++w;
            }

            return false;
        }

        @Override
        public Integer next() {
            return w++;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append("\n");
        for (int i = 0; i < V; i++) {
            s.append(i).append(": ");
            for (int w : adj(i)) {
                s.append(w).append(" ");
            }

            s.append("\n");
        }

        return s.toString();
    }
}
