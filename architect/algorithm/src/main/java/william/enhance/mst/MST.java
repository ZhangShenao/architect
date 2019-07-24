package william.enhance.mst;

import java.util.List;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 22:49
 * @Description:最小生成树 ADT
 */
public interface MST<Weight extends Number & Comparable<Weight>> {
    /**
     * 获取最小生成树的权值
     */
    Number weight();

    /**
     * 获取最小生成树的所有边
     */
    List<Edge<Weight>> edges();
}
