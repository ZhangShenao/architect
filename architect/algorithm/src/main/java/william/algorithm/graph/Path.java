package william.algorithm.graph;

import org.springframework.util.Assert;
import java.util.Stack;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/24 15:18
 * @Description:无权图的一条路径
 */
public class Path {
    private Graph graph;
    private int[] from;             //记录当前顶点路径上的前一个顶点
    private int start;              //路径的起点
    private boolean[] visited;      //记录每个顶点是否被访问过

    public Path(Graph graph, int start) {
        Assert.isTrue(start >= 0 && start < graph.vertexNum(),"Illegal Vertex Index");

        this.graph = graph;
        this.start = start;

        visited = new boolean[graph.vertexNum()];
        from = new int[graph.vertexNum()];
        for (int i = 0; i < graph.vertexNum(); i++){
            from[i] = -1;
            visited[i] = false;
        }

        dfs(start);
    }


    public boolean hasPath(int terminate){
        Assert.isTrue(terminate >= 0 && terminate < graph.vertexNum(),"Illegal Vertex Index");
        return visited[terminate];
    }

    public void showPath(int terminate){
        Assert.isTrue(terminate >= 0 && terminate < graph.vertexNum(),"Illegal Vertex Index");
        if (!(hasPath(terminate))){
            return;
        }
        Stack<Integer> pathNode = new Stack<>();
        pathNode.push(terminate);
        while (from[terminate] != -1){
            pathNode.push(from[terminate]);
            terminate = from[terminate];
        }

        while (!pathNode.isEmpty()){
            System.err.print(pathNode.pop());
            if (!pathNode.isEmpty()){
                System.err.print("->");
            }
        }
        System.err.println();
    }

    private void dfs(int vertex){
        visited[vertex] = true;
        Iterable<Integer> adj = graph.adj(vertex);
        if (adj == null){
            return;
        }
        adj.forEach(v -> {
            if (!visited[v]){
                from[v] = vertex;
                dfs(v);
            }
        });
    }
}
