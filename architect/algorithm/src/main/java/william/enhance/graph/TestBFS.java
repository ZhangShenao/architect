package william.enhance.graph;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 22:18
 * @Description:广度优先遍历测试
 */
public class TestBFS {
    //测试最短路径
    @Test
    public void testShortestPath() {
        //TestG.txt
        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/graph/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        new GraphReader(g, filename);
        g.print();
        System.err.println();
        Path path = new Path(g,0);
        System.err.println("DFS: Path from 0 -> 6: " + path.findPath(6));
        ShortestPath shortestPath = new ShortestPath(g,0);
        System.err.println("BFS: Path from 0 -> 6: " + shortestPath.findPath(6));
    }
}
