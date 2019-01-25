package william.algorithm.weightedgraph;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 10:31
 * @Description:有权稠密图，使用邻接矩阵表示
 */
public class WeightedDenseGraph<Weight extends Number & Comparable> implements WeightedGraph{
    private int vertexNum;
    private int edgeNum;
    private boolean directed;
    private Edge[][] graph;

    public WeightedDenseGraph(int vertexNum, boolean directed){
        this.vertexNum = vertexNum;
        this.directed = directed;
        this.edgeNum = 0;

        //初始化时没有任何边
        graph = new Edge[vertexNum][vertexNum];
        for(int i = 0;i < vertexNum;i++){
            for (int j = 0;j < vertexNum;j++){
                graph[i][j] = null;
            }
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

        //如果该边已经存在,则使用新的权值覆盖旧的
        if (hasEdge(origin,destination)){
            edgeNum--;
        }

        graph[origin][destination] = new Edge(edge);
        if (!directed){
            graph[destination][origin] = new Edge(edge.getDestination(),edge.getOrigin(),edge.getWeight());
        }
        edgeNum++;

    }

    @Override
    public boolean hasEdge(int v, int w) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(w >= 0 && w < vertexNum,"Illegal Vertex Index");
        return (graph[v][w] != null);
    }

    @Override
    public void show() {
        for( int i = 0 ; i < vertexNum ; i ++ ){
            for( int j = 0 ; j < vertexNum ; j ++ ){
                if( graph[i][j] != null ){
                    System.err.print(graph[i][j].getWeight()+"\t");
                }
                else{
                    System.err.print("NULL\t");
                }
            }
            System.err.println();
        }
    }

    @Override
    public Iterable<Edge<Weight>> adj(int v) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        List<Edge<Weight>> adjs = new ArrayList<>(graph[v].length);
        for (int i = 0, len = graph[v].length; i < len; i++){
            adjs.add(new Edge(graph[v][i]));
        }
        return adjs;
    }

}
