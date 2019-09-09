package william.ds.graph;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 17:39
 * @Description:图的深度优先遍历
 */
public class DepthFirstTraverse {
    //记录起始顶点
    private final int s;

    //记录已经被遍历过的顶点,避免重复访问
    private final boolean[] visited;

    //记录遍历到的顶点数量
    private int visitedCount;

    public DepthFirstTraverse(Graph g, int s) {
        //初始化
        this.s = s;
        this.visited = new boolean[g.V()];
        this.visitedCount = 0;

        //进行深度优先遍历
        dft(g, s);
    }

    public int count(){
        return visitedCount;
    }

    //对图g,从顶点s开始进行深度优先遍历
    private void dft(Graph g, int s) {
        //标记
        visited[s] = true;
        ++visitedCount;
        System.err.print(" " + s + " ");

        //获取所有与s相连的顶点,递归进行dfs
        for (int v : g.adj(s)) {
            if (!visited[v]) {
                dft(g, v);
            }
        }
    }


}
