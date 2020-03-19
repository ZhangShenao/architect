package william.designpattern.state;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 登录状态
 */
public class LoginState extends AbstractUserState {
    public LoginState(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void collect() {
        System.err.println("收藏成功");
    }

    @Override
    public void comment(String content) {
        System.err.println("评论成功~ 评论内容: " + content);
    }
}
