package william.enhance.mst;

import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 23:27
 * @Description:最小生成树测试
 */
public class TestMST {
    public static void main(String[] args) {

        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/mst/testG1.txt";
        int V = 8;

        SparseWeightedGraph<Double> g = new SparseWeightedGraph<>(V, false);
        new ReadWeightedGraph(g, filename);

        // Test Prim MST
        System.err.println("Prim MST:");
        MST<Double> mst = new PrimeMST<>(g);
        List<Edge<Double>> edges = mst.edges();
        for (int i = 0; i < edges.size(); i++) {
            System.err.println(edges.get(i));
        }
        System.err.println("The MST weight is: " + mst.weight());
        System.err.println();

        // Test Kruskal MST
        System.err.println("Kruskal MST:");
        mst = new KruskalMST<>(g);
        edges = mst.edges();
        for (int i = 0; i < edges.size(); i++) {
            System.err.println(edges.get(i));
        }
        System.err.println("The MST weight is: " + mst.weight());
    }
}
