package william.enhance.graph;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 21:38
 * @Description:测试图的深度优先遍历
 */
public class TestDFS {
    //连通分量测试
    @Test
    public void testConnectedComponent() {
        // TestG1.txt
        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/graph/testG1.txt";
        SparseGraph g = new SparseGraph(13, false);
        new GraphReader(g, filename);
        ConnectedComponent component = new ConnectedComponent(g);
        System.err.println("TestG1.txt, Component Count: " + component.componentCount());
        System.err.println();

        //TestG2.txt
        filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/graph/testG2.txt";
        g = new SparseGraph(6, false);
        new GraphReader(g, filename);
        component = new ConnectedComponent(g);
        System.err.println("TestG2.txt, Component Count: " + component.componentCount());
    }

    //路径测试
    @Test
    public void testPath() {
        //TestG.txt
        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/graph/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        new GraphReader(g, filename);
        g.print();
        System.err.println();
        Path path = new Path(g,0);
        System.err.println("Path from 0 -> 6: " + path.findPath(6));
    }
}
