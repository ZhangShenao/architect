package william.concurrent.async;

import java.util.concurrent.CompletableFuture;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/19 16:04
 * @Description:
 */
public class TestCompletableFuture {
    public static void main(String[] args) throws Exception {
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase)
                .whenComplete((result, throwable) -> {
                    if (throwable == null){
                        System.err.println("Result: " + result);
                    }
                    else {
                        System.err.println(throwable.getMessage());
                    }
                });
    }
}
