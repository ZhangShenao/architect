package william.enhance.mst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 21:42
 * @Description:带权图的边
 */
public class Edge<Weight extends Number & Comparable<Weight>> implements Comparable<Edge<Weight>> {
    //两端顶点
    private int v;
    private int w;

    //边的权值
    private Weight weight;

    public Edge(int v, int w, Weight weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public Edge(Edge<Weight> e) {
        this.v = e.v;
        this.w = e.w;
        this.weight = e.weight;
    }

    public Edge() {
    }

    public int v() {
        return v;
    }

    public int w() {
        return w;
    }

    public Weight weight() {
        return weight;
    }

    public int other(int x) {
        return (x == v ? w : v);
    }

    @Override
    public int compareTo(Edge<Weight> o) {
        return weight.compareTo(o.weight);
    }

    @Override
    public String toString() {
        return "" + v + "-" + w + ": " + weight;
    }
}
