package william.designprinciple.simpleresponsibility;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description 接口职责拆分——课程展示接口
 */
public interface CourseInfo {
    //获取课程详情
    String getDescription();

    //获取视频流
    byte[] getVideoStream();
}
