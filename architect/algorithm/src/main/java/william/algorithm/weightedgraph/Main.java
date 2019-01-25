package william.algorithm.weightedgraph;

import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 11:26
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/william/Desktop/projects/architect/algorithm/src/main/java/william/algorithm/weightedgraph/testG1.txt";
        int V = 8;

        WeightedSparseGraph<Double> g = new WeightedSparseGraph<>(V, false);
        ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

        // Test Lazy Prim MST
        System.err.println("Test Lazy Prim MST:");
        KruskalMST<Double> kruskalMST = new KruskalMST<Double>(g);
        List<Edge<Double>> mst = kruskalMST.getMst();
        for( int i = 0 ; i < mst.size() ; i ++ ){
            System.err.println(mst.get(i));
        }
        System.err.println("The MST weight is: " + kruskalMST.getMstWeight());

        System.err.println();
    }
}
