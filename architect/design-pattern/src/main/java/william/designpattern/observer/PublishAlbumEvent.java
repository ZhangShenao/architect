package william.designpattern.observer;

/**
 * @Author zhangshenao
 * @Date 2020-03-23
 * @Description
 */
public class PublishAlbumEvent implements StarEvent {

    @Override
    public String desc() {
        return "发布新专辑";
    }
}
