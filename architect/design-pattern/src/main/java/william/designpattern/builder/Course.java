package william.designpattern.builder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 创建一个课程类需要指定多个组件, 适合采用Builder模式
 */
@Getter
@Setter
@ToString
public class Course {
    private String name;
    private String video;
    private String ppt;
    private String sourceCode;
    private String homework;

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Course course = new Course();

        public Builder name(String name) {
            course.name = name;
            return this;
        }

        public Builder video(String video) {
            course.video = video;
            return this;
        }

        public Builder ppt(String ppt) {
            course.ppt = ppt;
            return this;
        }

        public Builder sourceCode(String sourceCode) {
            course.sourceCode = sourceCode;
            return this;
        }

        public Builder homework(String homework) {
            course.homework = homework;
            return this;
        }

        //构造对象,改过程可能十分复杂
        public Course build() {
            //此处可以进行一些必要的属性校验等工作
            return course;
        }
    }
}
