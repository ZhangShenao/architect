package william.netty.practise.client;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author zhangshenao
 * @Date 2021-07-26
 * @Description 使用OkHttpClient发起Http请求
 */
public class UseHttpClient {
    public static void main(String[] args) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8004/test";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());
        }
    }
}
