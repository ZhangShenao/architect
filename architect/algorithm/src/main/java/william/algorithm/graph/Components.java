package william.algorithm.graph;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/23 10:26
 * @Description:图的联通分量
 */
public class Components {
    private Graph graph;
    private int componentsCount;    //联通分量的数量
    private boolean[] visited;      //记录图的每个顶点是否被访问过
    private int[] ids;              //记录图的每个顶点所属的联通分量的id

    public Components(Graph graph) {
        //初始化数据
        this.graph = graph;
        this.componentsCount = 0;
        visited = new boolean[graph.vertexNum()];
        this.ids = new int[graph.vertexNum()];

        for (int i = 0;i < graph.vertexNum();i++){
            visited[i] = false;
            ids[i] = -1;
        }

        //对图进行深度有点遍历，并记录联通分量的数量和ids数组
        for (int i = 0;i < graph.vertexNum();i++){
            if (!visited[i]){
                dfs(i);
                componentsCount++;
            }
        }
    }

    //对图的vertex顶点记性深度优先遍历
    private void dfs(int vertex){
        visited[vertex] = true;
        ids[vertex] = componentsCount;
        Iterable<Integer> adj = graph.adj(vertex);
        if (adj == null){
            return;
        }
        adj.forEach(v -> {
            if (!visited[v]){
                dfs(v);
            }
        });
    }

    public int getComponentsCount() {
        return componentsCount;
    }

    public boolean isConnected(int v,int w){
        return (ids[v] == ids[w]);
    }
}
