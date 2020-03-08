package william.designpattern.builder;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description Builder模式, 可以指定创建对象的步骤和所使用的各部分组件, 实现对创建过程的精细化控制
 */
public class Client {
    public static void main(String[] args) {
        Course course = Course.newBuilder()
                .name("Java高级架构师培训")
                .video("视频")
                .ppt("讲义")
                .sourceCode("源码")
                .homework("课后作业")
                .build();
        System.err.println(course);
    }
}
