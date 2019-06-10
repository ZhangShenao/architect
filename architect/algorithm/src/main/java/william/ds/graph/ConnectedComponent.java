package william.ds.graph;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 19:29
 * @Description:处理图的连通分量
 */
public class ConnectedComponent {
    //记录已经被遍历过的顶点,避免重复访问
    private final boolean[] visited;

    //记录每个顶点所在的连通分量的id
    private final int[] ids;

    //记录每个连通分量中所包含的顶点数量
    private final int[] size;

    //记录图的连通分量的数量
    private int count;


    public ConnectedComponent(Graph g) {
        //初始化
        this.visited = new boolean[g.V()];
        this.ids = new int[g.V()];
        this.size = new int[g.V()];

        //对每个顶点进行深度优先遍历
        for (int v = 0;v < g.V();v++){
            if (!visited[v]){
                dft(g,v);

                //连通分量数+1
                ++count;
            }

        }
    }

    //对图g,从顶点s开始进行深度优先遍历,当前连通分量id=count
    private void dft(Graph g, int s) {
        //标记
        visited[s] = true;

        //记录顶点s所在的连通分量
        ids[s] = count;

        //记录当前连通分量中顶点数量
        ++size[count];

        //获取所有与s相连的顶点,递归进行dfs
        for (int v : g.adj(s)) {
            if (!visited[v]) {
                dft(g, v);
            }
        }
    }

    //获取图的连通分量的数量
    public int count(){
        return count;
    }

    public boolean isConnected(int w,int v){
        return (ids[w] == ids[v]);
    }

}
