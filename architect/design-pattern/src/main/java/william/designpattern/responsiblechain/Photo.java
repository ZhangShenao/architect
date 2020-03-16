package william.designpattern.responsiblechain;

import lombok.Builder;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description
 */
@Data
@Builder
public class Photo {
    private long photoId;

    private String title;

    private boolean deleted;

    private boolean negative;

    private boolean legalAuthor;
}
