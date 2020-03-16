package william.designpattern.responsiblechain;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class NegativePhotoHandler implements PhotoHandler {

    @Override
    public boolean canDisplay(Photo photo) {
        if (photo.isNegative()) {
            System.err.println("负向视频不展示! photoId: " + photo.getPhotoId());
            return false;
        }
        return true;
    }
}
