package william.enhance.mst;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.Locale;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class ReadWeightedGraph {

    private Scanner scanner;

    // 由于文件格式的限制，我们的文件读取类只能读取权值为Double类型的图
    public ReadWeightedGraph(WeightedGraph<Double> graph, String filename){

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
                Double weight = scanner.nextDouble();
                assert v >= 0 && v < V;
                assert w >= 0 && w < V;
                graph.addEdge(new Edge<Double>(v, w, weight));
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


    public static void main(String[] args) {
        String filename = "/Users/william/Desktop/GitResp/architect/architect/algorithm/src/main/java/william/enhance/mst/testG1.txt";
        int V = 8;
        WeightedGraph<Double> g = new DenseWeightedGraph<Double>(V, false);
        new ReadWeightedGraph(g, filename);
        System.err.println("DenseWeightedGraph: ");
        g.print();
        g = new SparseWeightedGraph<>(V,false);
        new ReadWeightedGraph(g, filename);
        System.err.println("SparseWeightedGraph: ");
        g.print();
    }

}
