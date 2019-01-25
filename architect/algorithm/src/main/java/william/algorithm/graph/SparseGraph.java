package william.algorithm.graph;

import org.springframework.util.Assert;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 10:14
 * @Description:稀疏图，使用邻接表表示
 */
public class SparseGraph implements Graph{
    private int vertexNum;
    private int edgeNum;
    private boolean directed;
    private List<Integer>[] graph;

    public SparseGraph(int vertexNum,boolean directed){
        this.vertexNum = vertexNum;
        this.directed = directed;
        this.edgeNum = 0;
        graph = (List<Integer>[])new List[vertexNum];

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
    public void addEdge(int v, int w) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");

        //支持平行边和一条自环边
        graph[v].add(w);
        if (!directed && v != w){
            graph[w].add(v);
        }
        edgeNum++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");

        return graph[v].contains(w);
    }

    @Override
    public void show() {
        for( int i = 0 ; i < vertexNum ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < graph[i].size() ; j ++ ){
                System.out.print(graph[i].get(j) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        Assert.isTrue(v >= 0 && v < vertexNum,"Illegal Vertex Index");
        return graph[v];
    }
}
