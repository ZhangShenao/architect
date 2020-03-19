package william.designpattern.memento;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 文章, 对应备忘录角色
 */
@Data
@AllArgsConstructor
public class Article {
    private String title;

    private String content;

    private long editTime;
}
