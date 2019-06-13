package william.ds.mst;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/13 20:47
 * @Description:最小生成树ADT
 */
public interface MST {
    /**
     * 获取最小生成树的所有边
     * @return
     */
    Iterable<Edge> edges() ;

    /**
     * 获取最小生成树的总权值
     */
    double weight()  ;
}
