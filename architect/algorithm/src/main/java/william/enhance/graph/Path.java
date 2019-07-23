package william.enhance.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 21:26
 * @Description:基于深度优先遍历,查找从源顶点到目标顶点的一条路径
 */
public class Path {
    //图
    private Graph graph;

    //源顶点
    private int source;

    //记录每个顶点是否被访问过
    private boolean[] visited;

    //记录路径上每个顶点的上一个顶点
    private int[] from;

    public Path(Graph graph, int source) {
        //初始化
        this.graph = graph;
        this.source = source;
        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        //对源顶点进行深度优先遍历
        dfs(source);
    }

    //对顶点v进行深度优先遍历
    private void dfs(int v) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;

        //获取与顶点v所有相连的顶点,依次进行深度优先遍历
        List<Integer> adjs = graph.adjs(v);
        for (int w : adjs) {
            if (!visited[w]) {
                //记录路径上的上一个顶点
                from[w] = v;
                dfs(w);
            }
        }
    }

    //判断是否有到顶点dst的一条路径
    public boolean hasPath(int dst) {
        return visited[dst];
    }

    //查找到顶点dst的路径
    public List<Integer> findPath(int dst) {
        if (!hasPath(dst)) {
            return Collections.emptyList();
        }
        LinkedList<Integer> path = new LinkedList<>();
        int v = dst;
        while (v != -1) {
            path.addFirst(v);
            v = from[v];
        }

        return path;
    }
}
