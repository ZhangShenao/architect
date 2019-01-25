package william.concurrent.framework;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/30 11:24
 * @Description:
 */
public class TaskResultInfo<R> {
    private static final String SUCCESS_MESSAGE = "success";

    private TaskResultType resultType;
    private R resultData;
    private String message;

    public static <R> TaskResultInfo<R> success(R resultData){
        TaskResultInfo<R> resultInfo = new TaskResultInfo<>();
        resultInfo.resultType = TaskResultType.SUCCESS;
        resultInfo.resultData = resultData;
        resultInfo.message = SUCCESS_MESSAGE;
        return resultInfo;
    }

    public static <R> TaskResultInfo<R> fail(String message){
        TaskResultInfo<R> resultInfo = new TaskResultInfo<>();
        resultInfo.resultType = TaskResultType.FAIL;
        resultInfo.message = message;
        return resultInfo;
    }

    public static <R> TaskResultInfo<R> fail(Throwable throwable){
        TaskResultInfo<R> resultInfo = new TaskResultInfo<>();
        resultInfo.resultType = TaskResultType.FAIL;
        resultInfo.message = throwable.getMessage();
        return resultInfo;
    }

    public boolean isSuccess(){
        return (TaskResultType.SUCCESS == resultType);
    }
}
