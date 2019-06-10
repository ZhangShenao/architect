package william.ds.graph;

import org.junit.Test;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/10 17:08
 * @Description:图测试
 */
public class TestGraph {
    @Test
    public void testAdjMatrixGraph() {
        FileUtils in = new FileUtils("./tinyG.txt");
        int V = in.readInt();
        Graph graph = new AdjMatrixGraph(V);

        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边数量不能小于0");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            graph.addEdge(v, w);
        }
        System.out.println("Graph is: " + graph);
    }

    @Test
    public void testAdjListGraph() {
        FileUtils in = new FileUtils("./tinyG.txt");

        int V = in.readInt();
        Graph graph = new AdjListGraph(V);

        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边数量不能小于0");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            graph.addEdge(v, w);
        }

        System.out.println("Graph is: " + graph);
    }

    @Test
    public void testDFT() {
        FileUtils in = new FileUtils("./tinyG.txt");

        int V = in.readInt();
        Graph graph = new AdjListGraph(V);

        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边数量不能小于0");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            graph.addEdge(v, w);
        }

        System.err.println("Graph is: " + graph);

        DepthFirstTraverse dft = new DepthFirstTraverse(graph, 2);
        System.err.println();
        System.err.println("visited count: " + dft.count());
    }

    @Test
    public void testBFT() {
        FileUtils in = new FileUtils("./tinyG.txt");

        int V = in.readInt();
        Graph graph = new AdjListGraph(V);

        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边数量不能小于0");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            graph.addEdge(v, w);
        }

        System.err.println("Graph is: " + graph);

        BreadthFirstTraverse bft = new BreadthFirstTraverse(graph, 2);
        System.err.println();
        System.err.println("visited count: " + bft.count());
    }

    @Test
    public void testFindPath() {
        FileUtils in = new FileUtils("./tinyG.txt");

        int V = in.readInt();
        Graph graph = new AdjListGraph(V);

        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边数量不能小于0");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            graph.addEdge(v, w);
        }

        System.err.println("Graph is: " + graph);

        FindPath findPath = new FindPath(graph, 2);
        for (int i = 0;i < graph.V();i++){
            if (findPath.hasPath(i)){
                findPath.printPath(i);
                System.err.println();
            }
        }
    }

    @Test
    public void testConnectedComponent() {
        FileUtils in = new FileUtils("./tinyG.txt");

        int V = in.readInt();
        Graph graph = new AdjListGraph(V);

        int E = in.readInt();
        if (E < 0) {
            throw new IllegalArgumentException("边数量不能小于0");
        }

        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();

            graph.addEdge(v, w);
        }

        System.err.println("Graph is: " + graph);

        ConnectedComponent connectedComponent = new ConnectedComponent(graph);
        System.err.println("Connected Component Count: " + connectedComponent.count());
    }
}
