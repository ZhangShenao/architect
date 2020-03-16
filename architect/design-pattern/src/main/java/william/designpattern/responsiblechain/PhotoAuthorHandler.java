package william.designpattern.responsiblechain;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class PhotoAuthorHandler implements PhotoHandler {
    @Override
    public boolean canDisplay(Photo photo) {
        if (!photo.isLegalAuthor()) {
            System.err.println("作者非法的视频不展示! photoId: " + photo.getPhotoId());
            return false;
        }
        return true;
    }
}
