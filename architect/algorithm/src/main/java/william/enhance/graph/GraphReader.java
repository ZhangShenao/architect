package william.enhance.graph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 18:39
 * @Description:从文件中读取一个图
 */
public class GraphReader {
    private Scanner scanner;

    public GraphReader(Graph graph, String filename){

        readFile(filename);

        try {
            int V = scanner.nextInt();
            if (V < 0)
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            assert V == graph.V();

            int E = scanner.nextInt();
            if (E < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(v, w);
            }
        }
        catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
    }

    private void readFile(String filename){
        assert filename != null;
        try {
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }
            else
                throw new IllegalArgumentException(filename + " doesn't exist.");
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + filename, ioe);
        }
    }

    // 测试通过文件读取图的信息
    public static void main(String[] args) {

        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/graph/testG1.txt";
        SparseGraph g1 = new SparseGraph(13, false);
        GraphReader reader = new GraphReader(g1, filename);
        System.err.println("test G1 in Sparse Graph:");
        g1.print();

        System.err.println();

        DenseGraph g2 = new DenseGraph(13, false);
        reader = new GraphReader(g2 , filename );
        System.err.println("test G1 in Dense Graph:");
        g2.print();

        System.err.println();

        // 使用两种图的存储方式读取testG2.txt文件
        filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/graph/testG2.txt";
        SparseGraph g3 = new SparseGraph(6, false);
        reader = new GraphReader(g3, filename);
        System.err.println("test G2 in Sparse Graph:");
        g3.print();

        System.err.println();

        DenseGraph g4 = new DenseGraph(6, false);
        reader = new GraphReader(g4, filename);
        System.err.println("test G2 in Dense Graph:");
        g4.print();
    }
}
