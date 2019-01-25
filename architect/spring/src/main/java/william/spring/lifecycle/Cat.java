package william.spring.lifecycle;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/4 12:21
 * @Description:
 */
public class Cat {
    public void init(){
        System.err.println("Cat init...");
    }

    public void destroy(){
        System.err.println("Cat destroy...");
    }
}
