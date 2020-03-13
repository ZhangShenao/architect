package william.designpattern.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-13
 * @Description 组合模式的Leaf
 */
public class SingleFile implements File{
    private String name;
    private long createTime;

    public SingleFile(String name) {
        this.name = name;
        this.createTime = System.currentTimeMillis();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public long createTime() {
        return createTime;
    }

    @Override
    public void open() {
        System.err.println("Open File");
    }

    @Override
    public void delete() {
        System.err.println("Delete File");
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}
