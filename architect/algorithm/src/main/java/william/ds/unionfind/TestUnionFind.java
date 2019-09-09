package william.ds.unionfind;

import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/6/5 12:58
 * @Description:并查集测试
 */
public class TestUnionFind {
    @Test
    public void test() {
        int n = 20;
        UnionFind uf = new QuickUnion(n);

        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < 10; i++) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            uf.union(p, q);
        }

        for (int i = 0; i < 20; i++) {
            int p = random.nextInt(n);
            int q = random.nextInt(n);
            System.err.println(String.format("%s and %s is connected: %s", p, q, uf.isConnected(p, q)));
        }

        System.err.println("connectedComponentCount: " + uf.connectedComponentCount());
    }
}
