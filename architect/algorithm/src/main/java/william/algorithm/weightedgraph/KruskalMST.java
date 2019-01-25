package william.algorithm.weightedgraph;

import william.algorithm.unionfind.UnionFind;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/28 10:16
 * @Description:使用Kruskal算法求最小生成树
 */
public class KruskalMST<Weight extends Number & Comparable> {
    private WeightedGraph<Weight> graph;    //图的原始数据
    private List<Edge<Weight>> mst;         //保存所有最小生成树的边
    private Number mstWeight;               //最小生成树的权值

    public KruskalMST(WeightedGraph<Weight> graph){
        this.graph = graph;
        mst = new ArrayList<>(graph.edgeNum());

        //把图的所有边保存到一个最小堆(优先队列中)
        PriorityQueue<Edge<Weight>> priorityQueue = new PriorityQueue<>(graph.edgeNum());
        for (int i = 0,len = graph.vertexNum();i < len;i++){
            Iterable<Edge<Weight>> adjs = graph.adj(i);
            for (Edge<Weight> edge: adjs){
                //对于无向图，避免加入重复的边
                if (edge.getOrigin() < edge.getDestination()){
                    priorityQueue.offer(edge);
                }
            }
        }

        //遍历所有边
        UnionFind unionFind = new UnionFind(graph.vertexNum());
        while ((!priorityQueue.isEmpty()) && mst.size() < graph.vertexNum() - 1){
            //每次拿出权值最小的边，如果没有成环，则是最小生成树的边
            Edge<Weight> edge = priorityQueue.poll();
            if (!unionFind.isConnected(edge.getOrigin(),edge.getDestination())){
                mst.add(edge);
                unionFind.union(edge.getOrigin(),edge.getDestination());
            }
        }

        //计算最小生成树的权值
        mstWeight = mst.get(0).getWeight();
        for( int i = 1 ; i < mst.size() ; i ++ ){
            mstWeight = mstWeight.doubleValue() + mst.get(i).getWeight().doubleValue();
        }
    }

    public List<Edge<Weight>> getMst() {
        return mst;
    }

    public Number getMstWeight() {
        return mstWeight;
    }
}
