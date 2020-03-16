package william.designpattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 具体的集合
 */
public class CourseAggregate implements Aggregate<Course> {
    private List<Course> courses = new ArrayList<>();


    @Override
    public void add(Course course) {
        courses.add(course);
    }

    @Override
    public void remove(Course course) {
        courses.remove(course);
    }

    @Override
    public Iterator<Course> iterator() {
        return new CourseAggregateIterator();
    }

    //迭代器
    private final class CourseAggregateIterator implements Iterator<Course> {
        //记录当前遍历到的索引
        private int index = 0;

        @Override
        public Course next() {
            return courses.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < courses.size();
        }
    }
}
