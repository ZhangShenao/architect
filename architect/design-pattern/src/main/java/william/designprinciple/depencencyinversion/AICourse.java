package william.designprinciple.depencencyinversion;

/**
 * @Author zhangshenao
 * @Date 2019-09-12
 * @Description
 */
public class AICourse implements Course {
    @Override
    public String study() {
        return "学习人工智能课程";
    }
}
