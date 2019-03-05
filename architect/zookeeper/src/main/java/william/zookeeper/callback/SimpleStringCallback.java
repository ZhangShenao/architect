package william.zookeeper.callback;

import org.apache.zookeeper.AsyncCallback;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/3/5 15:10
 * @Description:
 */
public class SimpleStringCallback implements AsyncCallback.StringCallback{
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.err.println("Process Result: [rc : " + rc + ", path: " + path + ", name: " + name);
    }
}
