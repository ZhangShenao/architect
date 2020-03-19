package william.designpattern.state;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description
 */
public class NotLoginState extends AbstractUserState {
    public NotLoginState(AppContext appContext) {
        super(appContext);
    }

    @Override
    public void collect() {
        System.err.println("未登录状态下无法收藏");
        System.err.println("跳转登录页面");
        appContext.login();
    }

    @Override
    public void comment(String content) {
        System.err.println("未登录状态下无法评论");
        System.err.println("跳转登录页面");
        appContext.login();
    }
}
