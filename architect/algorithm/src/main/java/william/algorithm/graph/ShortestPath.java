package william.algorithm.graph;

import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/25 10:12
 * @Description:
 */
public class ShortestPath {
    private Graph graph;
    private int start;              //起始点

    private boolean[] visited;      //记录每个顶点是否被访问过
    private int[] length;           //记录每个顶点到起始点的距离
    private int[] from;             //记录每个顶点的路径上的上一个顶点

    public ShortestPath(Graph graph, int start) {
        this.graph = graph;
        this.start = start;

        //初始化数据
        visited = new boolean[graph.vertexNum()];
        length = new int[graph.vertexNum()];
        from = new int[graph.vertexNum()];
        for (int i = 0;i < graph.vertexNum();i++){
            visited[i] = false;
            length[i] = -1;
            from[i] = -1;
        }

        //进行一次广度优先遍历
        Queue<Integer> pathNodes = new LinkedList<>();
        pathNodes.offer(start);
        length[start] = 0;
        visited[start] = true;
        while (pathNodes.peek() != null){
            Integer vertex = pathNodes.poll();
            if (!visited[vertex]){
                visited[vertex] = true;
            }
            Iterable<Integer> adj = graph.adj(vertex);
            for (int adjVertex : adj){
                if (!visited[adjVertex]){
                    pathNodes.offer(adjVertex);
                    visited[adjVertex] = true;
                    from[adjVertex] = vertex;
                    length[adjVertex] = length[vertex] + 1;
                }
            }
        }
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

    public int length(int terminate){
        Assert.isTrue(terminate >= 0 && terminate < graph.vertexNum(),"Illegal Vertex Index");
        return length[terminate];
    }


}
