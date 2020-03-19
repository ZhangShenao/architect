package william.designpattern.memento;

/**
 * @Author zhangshenao
 * @Date 2020-03-19
 * @Description 备忘录模式:在不破坏封装性的前提下,捕获一个对象的内部状态,并在外部保存这个状态,后面可以基于这个状态恢复一个对象
 */
public class Client {
    public static void main(String[] args) {
        Editor editor = new Editor();

        editor.save("title1", "content1");
        editor.save("title2", "content2");
        editor.save("title3", "content3");

        editor.show();

        editor.undo();
        editor.undo();

        editor.show();
    }
}
