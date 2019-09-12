package william.designprinciple.simpleresponsibility;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description 原始设计——一个接口承担多个职责,耦合严重
 */
public interface Course {
    //获取课程详情
    String getDescription();

    //获取视频流
    byte[] getVideoStream();

    //学习课程
    void study();

    //退款
    void refund();
}
