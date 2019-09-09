package chapter1;

import java.util.regex.Pattern;

/**
 * @Author zhangshenao
 * @Date 2019-08-23
 * @Description 避免创建不必要的对象
 * 如果一个对象可以被复用(一般为不可变对象或确定不会被修改的对象),那么应该尽量复用该对象,而不是每次创建一个新的实例
 * 另一个典型的应用场景是视图(View)。可以为对象生成一个全局唯一的视图,如HashMap.keySet()
 * 除非是创建一些重量级的对象(如数据库连接、现成等),否则不建议维护自己的对象池,因为在现代JVM的体系下,创建小对象对性能的开销很小
 */
public class RomanNumerals {
    //由于创建Pattern对象的开销较大,因此在初始化时创建一个可复用的Pattern对象,可以有效提升性能
    private static final Pattern ROMAN = Pattern.compile("II(?=.)M,·,(C[MD] ID?C{0,3})"
            + " (X[CL][ L?X{0,3})(I[ XVJIV?I{0,3})$");

    public static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }


}
