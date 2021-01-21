package william.oop.idgenerator;

/**
 * @Author zhangshenao
 * @Date 2021-01-21
 * @Description ID 生成异常
 */
public class IdGenerationFailureException extends Exception {
    public IdGenerationFailureException(String message) {
        super(message);
    }
}
