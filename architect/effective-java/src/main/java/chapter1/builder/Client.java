package chapter1.builder;

import static chapter1.builder.NyPizza.Size.SMALL;
import static chapter1.builder.Pizza.Topping.HAM;
import static chapter1.builder.Pizza.Topping.SAUSAGE;

import org.junit.Assert;
import org.junit.Test;

import chapter1.builder.NyPizza.Builder;

/**
 * @author zhangshenao
 * Created on 2019-08-20
 * 如果类的构造器或者静态工厂中具有多个参数,设计这种类时,Builder模式就是一种不错的选择,特别是当大多数参数都是可选或者类型相同的时候。
 * 与使用重叠构造器模式相比,使用Builder模式的客户端代码将更易于阅读和编写,构建器也比JavaBeans更加安全。此外,如果后期需要扩展类中的属性,Builder模式也十分方便。
 */
public class Client {
    @Test
    public void useSimpleBuilder() {
        //通过Builder模式创建对象,采用流式API,可读性强
        NutritionFacts nutritionFacts = NutritionFacts.newBuilder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27)
                .build();
        Assert.assertNotNull(nutritionFacts);
    }

    @Test
    public void useHierarchicalBuilder() {
        //带有层次关系的Builder
        NyPizza nyPizza = new Builder(SMALL)
                .addTopping(SAUSAGE)
                .addTopping(SAUSAGE)
                .build();

        Calzone calzone = new Calzone.Builder()
                .addTopping(HAM)
                .sauceInside()
                .build();

        Assert.assertNotNull(nyPizza);
        Assert.assertNotNull(calzone);

    }


}
