package william.ds.graph;

import java.util.Stack;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 18:17
 * @Description:基于深度优先遍历查询图的一条路径
 */
public class FindPath {
    //记录起始顶点
    private final int s;

    //记录已经被遍历过的顶点,避免重复访问
    private final boolean[] visited;

    //记录遍历到的每个顶点的路径上的前驱顶点
    private final Integer[] prev;

    public FindPath(Graph g, int s) {
        //初始化
        this.s = s;
        this.visited = new boolean[g.V()];
        this.prev = new Integer[g.V()];

        //进行深度优先遍历
        dft(g, s);
    }

    //对图g,从顶点s开始进行深度优先遍历
    private void dft(Graph g, int s) {
        //标记
        visited[s] = true;

        //获取所有与s相连的顶点,递归进行dfs
        for (int v : g.adj(s)) {
            if (!visited[v]) {
                //记录前驱节点
                prev[v] = s;

                dft(g, v);
            }
        }
    }

    //返回是否有道顶点t的一条路径
    public boolean hasPath(int t){
        return visited[t];
    }

    //打印到顶点t的一条路径
    public void printPath(int t){
        if (!hasPath(t)){
            return;
        }

        System.err.print("path from " + s + " to " + t + ": ");
        //使用栈记录路径上的顶点
        Stack<Integer> stack = new Stack<>();
        Integer v = t;
        while (v != null){
            stack.push(v);
            v = prev[v];
        }

        while (!stack.isEmpty()){
            System.err.print(stack.pop());
            if (!stack.isEmpty()){
                System.err.print(" --> ");
            }
        }
    }
}
