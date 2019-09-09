package william.ds.mst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 08:12
 * @Description:带权图的边
 */
public class Edge implements Comparable<Edge> {
    //边的两个顶点
    private final int v;
    private final int w;

    //边的权值
    private final double weight;

    public Edge(int v, int w, double weight) {
        if (v < 0) {
            throw new IllegalArgumentException("vertex index must be nonnegative !!");
        }
        if (w < 0) {
            throw new IllegalArgumentException("vertex index must be nonnegative !!");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("weight must be a positive !!");
        }
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    //获取边的权值
    public double weight() {
        return weight;
    }

    //获取边的一个顶点
    public int either() {
        return v;
    }

    //获取边的另外一个顶点
    public int another(int vertex) {
        if (vertex == v) {
            return w;
        }
        if (vertex == w) {
            return v;
        }
        throw new IllegalArgumentException("illegal vertex index !!");
    }


    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%d <-> %d %.2f\n", v, w, weight);
    }
}
