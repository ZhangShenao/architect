package william.algorithm.weightedgraph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/27 10:22
 * @Description:使用Lazy-Prim算法实现有权图的最小生成树
 */
public class LazyPrimMST<Weight extends Number & Comparable>{
    private WeightedGraph<Weight> graph;
    private PriorityQueue<Edge<Weight>> priorityQueue;      //辅助数据结构——优先队列(最小堆)
    private List<Edge<Weight>> mst;                         //保存最小生成树的边
    private boolean[] marked;                               //标记数组, 在算法运行过程中标记节点i是否被访问
    private Number mstWeight;                               //最小生成树的权值

    public LazyPrimMST(WeightedGraph<Weight> graph){
        //初始化数据
        this.graph = graph;
        mst = new ArrayList<>(graph.edgeNum());
        priorityQueue = new PriorityQueue<>(graph.edgeNum());
        marked = new boolean[graph.vertexNum()];
        for (int i = 0;i < graph.vertexNum();i++){
            marked[i] = false;
        }

        //先访问第0个顶点
        visit(0);

        while (!priorityQueue.isEmpty()){
            //取出当前权值最小的边
            Edge<Weight> minEdge = priorityQueue.poll();
            //如果该边不是横切边，则直接丢弃
            int origin = minEdge.getOrigin();
            int destination = minEdge.getDestination();
            if (marked[origin] == marked[destination]){
                continue;
            }

            //如果是横切边，则该权值最小的边一定属于最小生成树
            mst.add(minEdge);

            //访问该最小边的下一个未被访问的顶点
            int nextNode = (marked[origin] ? destination : origin);
            visit(nextNode);

            //计算最小生成树的权值
            mstWeight = mst.get(0).getWeight();
            for( int i = 1 ; i < mst.size() ; i ++ ){
                mstWeight = mstWeight.doubleValue() + mst.get(i).getWeight().doubleValue();
            }
        }
    }

    //访问图的第v个顶点
    private void visit(int v){
        if(marked[v]){
            return;
        }
        marked[v] = true;

        //将和节点v相连接的所有未访问的边放入最小堆中
        Iterable<Edge<Weight>> edges = graph.adj(v);
        for (Edge<Weight> edge : edges){
            if (!marked[edge.other(v)]){
                priorityQueue.offer(edge);
            }
        }

    }

    //获取最小生成树的所有变
    public List<Edge<Weight>> getMst() {
        return mst;
    }

    //获取最小生成树的权值
    public Number getMstWeight() {
        return mstWeight;
    }
}
