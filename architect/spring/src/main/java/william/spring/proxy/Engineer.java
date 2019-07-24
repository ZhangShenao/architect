package william.spring.proxy;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/24 15:50
 * @Description:
 */
public class Engineer implements Worker{
    @Override
    public void goToWork() {
        System.err.println("工程师去上班啦");
    }

    @Override
    public void goOffWork() {
        System.err.println("工程师下班啦");
    }
}
