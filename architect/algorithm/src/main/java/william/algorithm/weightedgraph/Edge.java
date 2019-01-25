package william.algorithm.weightedgraph;

import org.springframework.util.Assert;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/26 10:12
 * @Description:有权图的边
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge>{
    private int origin;             //起始点
    private int destination;        //终点
    private Weight weight;          //权值

    public Edge(int origin, int destination, Weight weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Edge<Weight> edge) {
        this.origin = edge.origin;
        this.destination = edge.destination;
        this.weight = edge.weight;
    }

    public Edge(){}

    //返回边上一个顶点的另外一个顶点
    public int other(int v){
        Assert.isTrue((v == origin || v == destination),"Illegal Vertex!!");
        return (v == origin ? destination : origin);
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight.compareTo(o.getWeight());
    }

    @Override
    public String toString() {
        return "" + origin + "-" + destination + ": " + weight;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}
