package william.algorithm.graph;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 11:26
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        String filename = "/Users/william/Desktop/projects/architect/algorithm/src/main/java/william/algorithm/graph/testG.txt";
        SparseGraph g = new SparseGraph(7, false);
        ReadGraph readGraph = new ReadGraph(g, filename);
//        g.show();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs = new Path(g,0);
        System.err.print("DFS : ");
        dfs.showPath(6);

        ShortestPath bfs = new ShortestPath(g,0);
        System.err.print("BFS : ");
        bfs.showPath(6);

        System.err.println();

        filename = "/Users/william/Desktop/projects/architect/algorithm/src/main/java/william/algorithm/graph/testG1.txt";
        SparseGraph g2 = new SparseGraph(13, false);
        ReadGraph readGraph2 = new ReadGraph(g2, filename);
//        g2.show();

        // 比较使用深度优先遍历和广度优先遍历获得路径的不同
        // 广度优先遍历获得的是无权图的最短路径
        Path dfs2 = new Path(g2,0);
        System.err.print("DFS : ");
        dfs2.showPath(3);

        ShortestPath bfs2 = new ShortestPath(g,0);
        System.err.print("BFS : ");
        bfs2.showPath(3);
    }
}
