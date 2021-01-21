package william.oop.idgenerator;

/**
 * @Author zhangshenao
 * @Date 2021-01-21
 * @Description ID 生成器接口
 */
public interface IdGenerator {
    /**
     * 生成id
     */
    String generate() throws IdGenerationFailureException;
}
