package william.jvm.classloader;

import lombok.Data;

/**
 * @Author zhangshenao
 * @Date 2020-01-07
 * @Description
 */
@Data
public class User {
    private String name;

    public void show() {
        System.err.println("Show User!");
    }
}
