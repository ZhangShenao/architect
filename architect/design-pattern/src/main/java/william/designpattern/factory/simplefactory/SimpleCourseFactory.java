package william.designpattern.factory.simplefactory;

import william.designpattern.factory.Course;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description
 */
public class SimpleCourseFactory {
    public static Course create(Class<? extends Course> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
