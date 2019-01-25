package william.concurrent.framework;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 11:22
 * @Description:
 */
public enum TaskResultType {
    SUCCESS(1),
    FAIL(0),
    ERROR(-1),
    ;

    private int value;

    TaskResultType(int value) {
        this.value = value;
    }
}
