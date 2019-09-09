package william.enhance.graph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 22:02
 * @Description:基于广度优先遍历,查找无权图的最短路径
 */
public class ShortestPath {
    //图
    private Graph graph;

    //源顶点
    private int source;

    //记录每个顶点是否被访问过
    private boolean[] visited;

    //记录每个顶点到源顶点之间的距离
    private int[] distances;

    //记录路径上每个顶点的上一个顶点
    private int[] from;

    public ShortestPath(Graph graph, int source) {
        //初始化
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.V()];
        this.distances = new int[graph.V()];
        this.from = new int[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
            distances[i] = -1;
            from[i] = -1;
        }

        //定义源顶点的距离
        distances[source] = 0;

        //进行广度优先遍历
        bfs();
    }

    private void bfs() {
        //借助一个队列
        Queue<Integer> queue = new LinkedList<>();

        //首先将源顶点入队
        queue.offer(source);
        visited[source] = true;

        //遍历队列
        while (!queue.isEmpty()) {
            //获取队首顶点
            int v = queue.poll();

            //获取与该顶点相连接的所有顶点,依次访问并入队
            List<Integer> adjs = graph.adjs(v);
            for (int w : adjs) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.offer(w);

                    //更新该顶点到源顶点的距离
                    distances[w] = distances[v] + 1;

                    //更新上一个顶点
                    from[w] = v;
                }
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

    //获取顶点dst到源顶点的距离
    public int distance(int dst){
        return distances[dst];
    }
}
