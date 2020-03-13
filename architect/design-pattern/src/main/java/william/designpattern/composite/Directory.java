package william.designpattern.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2020-03-13
 * @Description 组合模式的Composite, 内部可以组合Leaf和其他Composite
 */
public class Directory implements File {
    private List<File> files;

    private String name;
    private long createTime;

    public Directory(String name) {
        this.name = name;
        this.createTime = System.currentTimeMillis();
        this.files = new ArrayList<>();
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
        System.err.println("Open Directory");
    }

    @Override
    public void delete() {
        System.err.println("Delete Directory");
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void listFiles() {
        files.forEach(f -> {
            if (f.isDirectory()) {
                Directory dir = (Directory) f;
                dir.listFiles();
            } else {
                System.err.println(f.name());
            }
        });
    }
}
