package william.ds.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 17:50
 * @Description:图的广度优先遍历
 */
public class BreadthFirstTraverse {
    //记录起始顶点
    private final int s;

    //记录已经被遍历过的顶点,避免重复访问
    private final boolean[] visited;

    //记录遍历到的顶点数量
    private int visitedCount;

    public BreadthFirstTraverse(Graph g, int s) {
        //初始化
        this.s = s;
        this.visited = new boolean[g.V()];
        this.visitedCount = 0;

        //进行广度优先遍历
        bft(g,s);
    }

    //对图g,从顶点s开始进行广度优先遍历
    private void bft(Graph g, int s){
        //借助队列
        Queue<Integer> queue = new LinkedList<>();

        //首先将s入队
        queue.offer(s);

        //遍历队列
        while (!queue.isEmpty()){
            //访问顶点v
            int v = queue.poll();
            if (!visited[v]){
                visited[v] = true;
                ++visitedCount;
                System.err.print(" " + v + " ");
            }

            //将与顶点v相连的所有顶点入队
            for (int w : g.adj(v)){
                if (!visited[w]){
                    queue.offer(w);
                }
            }
        }
    }

    public int count() {
        return visitedCount;
    }
}
