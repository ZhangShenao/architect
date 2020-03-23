package william.designpattern.observer;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
public interface Fans {
    String name();

    void listenStarNews(StarEvent event);
}
