package william.designpattern.responsiblechain;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
public class Client {
    public static void main(String[] args) {
        Photo photo = Photo.builder()
                .photoId(1L)
                .title("热门视频")
                .deleted(false)
                .negative(false)
                .legalAuthor(false)
                .build();
        PhotoHandlerChain chain = new PhotoHandlerChain();
        System.err.println(chain.canDisPlay(photo));
    }
}
