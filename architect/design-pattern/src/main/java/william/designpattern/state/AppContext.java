package william.designpattern.state;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 操作上下文, 主要用于控制转态的转换
 */
public class AppContext {
    private UserState currentState; //当前状态


    public AppContext() {
        //初始为未登录状态
        currentState = new NotLoginState(this);
    }

    public void login() {
        this.currentState = new LoginState(this);
    }

    public void collect() {
        currentState.collect();
    }

    public void comment(String content) {
        currentState.comment(content);
    }
}
