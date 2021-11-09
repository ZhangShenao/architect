package william.algo.basic.stack;

import java.util.Stack;

/**
 * @Author zhangshenao
 * @Date 2021-11-09
 * @Description 基于栈实现的简单浏览器, 支持网页的前进和后退功能
 */
public class BrowserBasedOnStack {
    //使用两个栈,分别保存后退网页和前进网页
    private Stack<String> back;
    private Stack<String> forward;

    //记录当前网页
    private String current;

    public BrowserBasedOnStack() {
        this.back = new Stack<>();
        this.forward = new Stack<>();
    }

    //打开一个网页
    public void open(String url) {
        //先将当前浏览的网页保存到后退栈中
        if (current != null) {
            back.push(current);
        }

        //记录新网页
        current = url;

        //打开新网页后,前进栈需要清空
        forward.clear();

        System.err.println("打开新网页: " + url);
    }

    //是否可以后退
    public boolean canBack() {
        return back.size() > 0;
    }

    //后退
    public void back() {
        if (!canBack()) {
            System.err.println("没有更早的浏览记录了!");
            return;
        }

        //将当前页保存到前进栈
        forward.push(current);
        current = back.pop();
        System.err.println("后退至网页: " + current);
    }

    //前进
    public void forward() {
        if (forward.isEmpty()) {
            System.err.println("当前已经是最新的记录了!");
            return;
        }

        //将当前页保存至后退栈
        back.push(current);

        //将前进栈出栈
        current = forward.pop();

        System.err.println("前进至网页: " + current);
    }

    public static void main(String[] args) {
        BrowserBasedOnStack browser = new BrowserBasedOnStack();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.back();
        browser.back();
        browser.forward();
        browser.open("http://www.qq.com");
        browser.forward();
        browser.back();
        browser.forward();
        browser.back();
        browser.back();
        browser.back();
        browser.back();
    }
}
