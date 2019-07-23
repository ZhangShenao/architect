package william.enhance.graph;

import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/23 18:10
 * @Description:图ADT
 */
public interface Graph {
    /**
     * 获取图的顶点数
     */
    int V();

    /**
     * 获取图的边数
     */
    int E();

    /**
     * 增加v->w的一条边
     */
    void addEdge(int v,int w);

    /**
     * 判断是否存在v->w的一条边
     */
    boolean hasEdge(int v,int w);

    /**
     * 打印图
     */
    void print();

    /**
     * 返回与顶点v相连的所有边
     */
    List<Integer> adjs(int v);
}
