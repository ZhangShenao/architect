package william.jvm.allocation;

/**
 * @Author zhangshenao
 * @Date 2019-09-09
 * @Description 空间分配担保:新生代在进行MinorGC之前,JVM会首先检查老年代最大可用的连续空间是否大于新生代所有对象的空间总和。
 * 如果条件成立,那么此次MinorGC就是安全的。
 * 否则,JVM会查看-XX:HandlePromotionFailure设置的值是否允许担保失败。如果允许,JVM会检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小。
 * 如果大于,将尝试进行一次MinorGC,尽管此次MinorGC是有风险的。
 * 如果小于,或者设置为不允许,则要改为进行一次FullGC。
 * 大部分情况下,HandlePromotionFailure可用有效降低FullGC的频率
 * -XX:HandlePromotionFailure
 */
public class HandlePromotionFailure {
}
