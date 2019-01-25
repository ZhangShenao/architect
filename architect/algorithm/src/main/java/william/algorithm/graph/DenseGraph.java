package william.algorithm.graph;

import org.springframework.util.Assert;
import java.util.ArrayList;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 10:31
 * @Description:稠密图，使用邻接矩阵表示
 */
public class DenseGraph implements Graph{
    private int vertexNum;
    private int edgeNum;
    private boolean directed;
    private boolean[][] graph;

    public DenseGraph(int vertexNum,boolean directed){
        this.vertexNum = vertexNum;
        this.directed = directed;
        this.edgeNum = 0;

        //初始化时没有任何边
        graph = new boolean[vertexNum][vertexNum];
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
    public void addEdge(int v, int w) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        //不支持平行边，支持一条自环边
        if (graph[v][w]){
            return;
        }
        graph[v][w] = true;
        if (!directed){
            graph[w][v] = true;
        }
        edgeNum++;

    }

    @Override
    public boolean hasEdge(int v, int w) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        return graph[v][w];
    }

    @Override
    public void show() {
        for( int i = 0 ; i < vertexNum ; i ++ ){
            for( int j = 0 ; j < vertexNum ; j ++ ){
                System.out.print(graph[i][j]+"\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        ArrayList<Integer> adjs = new ArrayList<>(graph[v].length);
        for (int i = 0,len = graph[v].length;i < len;i++){
            if (graph[v][i]){
                adjs.add(i);
            }
        }
        return adjs;
    }
}
