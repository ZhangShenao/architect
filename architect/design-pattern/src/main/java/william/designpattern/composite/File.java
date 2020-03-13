package william.designpattern.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-13
 * @Description Component接口, 定义Composite和Leaf的公共行为
 */
public interface File {
    String name();

    long createTime();

    void open();

    void delete();

    boolean isDirectory();
}
