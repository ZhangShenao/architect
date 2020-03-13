package william.designpattern.composite;

/**
 * @Author zhangshenao
 * @Date 2020-03-13
 * @Description 组合模式:将对象组合成树形结构,以表示"整体-部分"的层次结构,使得客户端可以通过一致的方式使用单一对象和组合对象
 */
public class Client {
    public static void main(String[] args) {
        File file1 = new SingleFile("a.txt");
        File file2 = new SingleFile("b.txt");
        Directory dir1 = new Directory("dir1");
        dir1.addFile(file1);
        dir1.addFile(file2);
        File file3 = new SingleFile("c.txt");
        Directory dir2 = new Directory("dir2");
        dir2.addFile(file3);
        dir2.addFile(dir1);
        dir2.listFiles();
    }
}
