package william.jvm;

/**
 * @Description 虚拟机栈的栈帧,每个方法执行时都会创建一个栈帧
 * @Author ZhangShenao
 * @Date 2019/9/7 上午11:47
 */
public class TestStackFrame {
    private  void earn(int money) {
        money = money + 1000;
    }

    public static void main(String[] args) {
        TestStackFrame stackFrame = new TestStackFrame();
        stackFrame.earn(100);
    }
}
