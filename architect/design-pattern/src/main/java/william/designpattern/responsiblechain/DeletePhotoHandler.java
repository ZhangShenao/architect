package william.designpattern.responsiblechain;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class DeletePhotoHandler implements PhotoHandler {
    @Override
    public boolean canDisplay(Photo photo) {
        if (photo.isDeleted()) {
            System.err.println("已删除的视频不展示! photoId: " + photo.getPhotoId());
            return false;
        }
        return true;
    }
}
