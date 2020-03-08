package william.designpattern.prototype.simple;

/**
 * @Author zhangshenao
 * @Date 2020-03-08
 * @Description 原型接口
 */
public interface Prototype {
    /**
     * 通过属性的复制,创建一个原型对象
     */
    Prototype clone();
}
