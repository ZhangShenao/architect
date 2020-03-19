package william.designpattern.memento;

import java.util.Stack;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 草稿箱, 对应备忘录管理角色
 */
public class DraftBox {
    //使用一个栈,保存历史记录
    private Stack<Article> history = new Stack<>();

    //保存
    public void save(Article article) {
        history.push(article);
    }

    //回滚
    public Article rollback() {
        if (history.isEmpty()) {
            throw new RuntimeException("草稿箱为空,无法回滚");
        }
        history.pop();
        return history.peek();
    }

}
