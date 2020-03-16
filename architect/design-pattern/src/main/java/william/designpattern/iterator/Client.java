package william.designpattern.iterator;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 迭代器模式: 为不同的集合提供一致的遍历行为,而无需关心集合内部的数据结构
 */
public class Client {
    public static void main(String[] args) {
        Aggregate<Course> aggregate = new CourseAggregate();

        aggregate.add(new Course(1, "Java高级架构师课程"));
        aggregate.add(new Course(2, "大数据课程"));
        aggregate.add(new Course(3, "人工智能课程"));

        Iterator<Course> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            System.err.println(iterator.next());
        }
    }
}
