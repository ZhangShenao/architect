package william.enhance.graph;

import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 21:29
 * @Description:基于图的深度优先遍历,计算图中的连通分量
 */
public class ConnectedComponent {
    //图
    private Graph graph;

    //记录每个顶点是否被访问过
    private boolean[] visited;

    //记录图中连通分量的数量
    private int componentCount;

    public ConnectedComponent(Graph graph) {
        //初始化
        this.graph = graph;
        visited = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            visited[i] = false;
        }
        this.componentCount = 0;

        //依次从每个顶点开始,进行深度优先遍历
        for (int i = 0; i < graph.V(); i++) {
            if (!visited[i]) {
                //如果当前顶点没有被遍历过,说明增加了一个连通分量
                ++componentCount;
                dfs(i);
            }
        }
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
                dfs(w);
            }
        }
    }

    public int componentCount() {
        return componentCount;
    }
}
