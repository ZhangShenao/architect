package chapter1;

import org.springframework.lang.Nullable;

/**
 * @author zhangshenao
 * Created on 2019-08-19
 * 使用工厂方法代替构造器
 * 优势：1.工厂方法可以指定名称,通过名称可以直观地看出创建的是什么对象
 * 2.不必在每次调用的时候都创建一个新的对象,可以实现对象的复用(需要对对象进行不可变处理)
 * 3.可以返回原类型的任意子类
 * 4.可以根据工厂方法的参数值,动态返回不同的对象
 * <p>
 * 缺点：1.如果类中不包含public或protected的构造器,那么它就无法被子类化
 * 2.工厂方法没有构造器那样明显的标识,不容易被客户端察觉
 */
public class CommonResponse<T> {
    private static final String SUCCESS_MESSAGE = "success";

    //可复用的实例,避免每次创建
    private static final CommonResponse EMPTY_SUCCESS_INSTANCE;

    static {
        EMPTY_SUCCESS_INSTANCE = new CommonResponse();
        EMPTY_SUCCESS_INSTANCE.success = true;
        EMPTY_SUCCESS_INSTANCE.message = SUCCESS_MESSAGE;
    }


    private boolean success;
    private T data;

    @Nullable
    private String message;

    //使用静态工厂方法代替构造器,语义更加明确
    public static <T> CommonResponse successOf(T data) {
        CommonResponse response = new CommonResponse();
        response.success = true;
        response.data = data;
        response.message = SUCCESS_MESSAGE;
        return response;
    }


    public static CommonResponse errorOf(String message) {
        CommonResponse response = new CommonResponse();
        response.success = false;
        response.message = message;
        return response;
    }

    public static <T> CommonResponse errorOf(T data,String message) {
        CommonResponse response = errorOf(message);
        response.data = data;
        return response;
    }

    //通过静态工厂方法,可以实现对象的复用
    public static CommonResponse successOfEmpty() {
        return EMPTY_SUCCESS_INSTANCE;
    }


}
