package william.algorithm.weightedgraph;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 10:14
 * @Description:有权稀疏图，使用邻接表表示
 */
public class WeightedSparseGraph<Weight extends Number & Comparable> implements WeightedGraph {
    private int vertexNum;
    private int edgeNum;
    private boolean directed;
    private List<Edge<Weight>>[] graph;

    public WeightedSparseGraph(int vertexNum, boolean directed){
        this.vertexNum = vertexNum;
        this.directed = directed;
        this.edgeNum = 0;
        graph = (List<Edge<Weight>>[])new List[vertexNum];

        //初始化数据
        for (int i = 0;i < vertexNum;i++){
            graph[i] = new ArrayList<>(vertexNum);
        }
    }


    @Override
    public int vertexNum() {
        return vertexNum;
    }

    @Override
    public int edgeNum() {
        return edgeNum;
    }

    @Override
    public void addEdge(Edge edge) {
        int origin = edge.getOrigin();
        int destination = edge.getDestination();
        Assert.isTrue(origin >= 0 && origin < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(destination >= 0 && destination < vertexNum,"Illegal Vertex Index");

        graph[origin].add(new Edge(edge));
        if (!directed && origin != destination){
            graph[destination].add(new Edge(destination,origin,edge.getWeight()));
        }
        edgeNum++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");

        List<Edge<Weight>> edges = graph[v];
        if (CollectionUtils.isEmpty(edges)){
            return false;
        }
        return edges.stream().anyMatch(edge -> edge.other(v) == w);
    }

    @Override
    public void show() {
        for( int i = 0 ; i < vertexNum ; i ++ ){
            System.err.print("vertex " + i + ":\t");
            for( int j = 0 ; j < graph[i].size() ; j ++ ){
                Edge e = graph[i].get(j);
                System.err.print( "( to:" + e.other(i) + ",wt:" + e.getWeight() + ")\t");
            }
            System.err.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        return graph[v];
    }
}
