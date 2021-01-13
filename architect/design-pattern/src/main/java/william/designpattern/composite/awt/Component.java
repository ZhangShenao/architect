package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description 组件接口
 */
public interface Component {
    /**
     * 打印
     */
    void print();

    boolean isContainer();
}
