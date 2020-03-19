package william.designpattern.memento;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 编辑器, 对应发起人角色
 */
public class Editor {
    private DraftBox draftBox = new DraftBox();

    private Article article;

    //保存
    public void save(String title, String content) {
        article = new Article(title, content, System.currentTimeMillis());

        //同步保存草稿箱
        draftBox.save(article);
    }

    //撤销
    public void undo() {
        article = draftBox.rollback();
    }

    public void show() {
        System.err.println("Title: " + article.getTitle() + ", Content: " + article.getContent());
    }
}
