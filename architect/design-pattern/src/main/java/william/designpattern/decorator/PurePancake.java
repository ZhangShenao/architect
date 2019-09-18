package william.designpattern.decorator;

/**
 * @Author zhangshenao
 * @Date 2019-09-18
 * @Description 普通的煎饼
 */
public class PurePancake implements Pancake {
    @Override
    public String material() {
        return "普通煎饼";
    }

    @Override
    public int price() {
        return 6;
    }
}
