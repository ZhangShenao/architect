package william.designpattern.state;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description
 */
public abstract class AbstractUserState implements UserState{
    protected AppContext appContext;

    public AbstractUserState(AppContext appContext) {
        this.appContext = appContext;
    }
}
