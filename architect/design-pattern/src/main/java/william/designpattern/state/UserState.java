package william.designpattern.state;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 用户状态
 */
public interface UserState {
    void collect();

    void comment(String content);
}
