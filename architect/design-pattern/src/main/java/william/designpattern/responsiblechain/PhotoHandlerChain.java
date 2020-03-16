package william.designpattern.responsiblechain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 责任链
 */
public class PhotoHandlerChain {
    private static final List<PhotoHandler> handlers;

    //初始化处理器
    static {
        handlers = new ArrayList<>();
        handlers.add(new DeletePhotoHandler());
        handlers.add(new NegativePhotoHandler());
        handlers.add(new PhotoAuthorHandler());
    }

    public boolean canDisPlay(Photo photo) {
        for (PhotoHandler h : handlers) {
            if (!h.canDisplay(photo)) {
                return false;
            }
        }

        return true;
    }
}
