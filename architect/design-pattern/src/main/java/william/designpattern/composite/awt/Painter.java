package william.designpattern.composite.awt;

/**
 * @Author zhangshenao
 * @Date 2021-01-13
 * @Description
 */
public class Painter {
    public static void main(String[] args) {
        AbstractComponentContainer window = new WindowForm("WINDOW窗口");
        AbstractComponent picture = new Picture("LOGO图片");
        AbstractComponent loginButton = new Button("登录");
        AbstractComponent registerButton = new Button("登录");
        AbstractComponentContainer frame = new Frame("FRAME1");
        window.addComponent(picture);
        window.addComponent(loginButton);
        window.addComponent(registerButton);
        window.addComponent(frame);
        AbstractComponent userNameLabel = new Label("用户名");
        AbstractComponent textBox = new TextBox("文本框");
        AbstractComponent passwordLabel = new Label("密码");
        AbstractComponent passwordBox = new PasswordBox("密码框");
        AbstractComponent checkBox = new CheckBox("复选框");
        AbstractComponent rememberTextBox = new TextBox("记住用户名");
        AbstractComponent linkLabel = new LinkLabel("忘记密码");
        frame.addComponent(userNameLabel);
        frame.addComponent(textBox);
        frame.addComponent(passwordLabel);
        frame.addComponent(passwordBox);
        frame.addComponent(checkBox);
        frame.addComponent(rememberTextBox);
        frame.addComponent(linkLabel);
        window.print();

    }
}
