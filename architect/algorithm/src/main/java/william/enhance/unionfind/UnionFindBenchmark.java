package william.enhance.unionfind;

import org.junit.Test;
import java.lang.reflect.Constructor;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: ZhangShenao
 * @Date: 2019/7/21 17:12
 * @Description:并查集性能测试
 */
public class UnionFindBenchmark {
    @Test
    public void benchmark() {
        int n = 1000000;
//        unionFindOptions(QuickFind.class,n);
//        unionFindOptions(QuickUnion.class,n);
        unionFindOptions(SizeOptimizedUnionFind.class,n);
        unionFindOptions(RankOptimizedUnionFind.class,n);
        unionFindOptions(PathCompressedUnionFind.class,n);
    }

    private void unionFindOptions(Class<? extends UnionFind> clazz, int n) {
        try {
            Constructor<? extends UnionFind> constructor = clazz.getConstructor(int.class);
            UnionFind unionFind = constructor.newInstance(n);
            ThreadLocalRandom random = ThreadLocalRandom.current();
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                int p = random.nextInt(0, n);
                int q = random.nextInt(0, n);
                unionFind.union(p, q);
            }
            for (int i = 0; i < n; i++) {
                int p = random.nextInt(0, n);
                int q = random.nextInt(0, n);
                unionFind.isConnected(p, q);
            }
            long endTime = System.currentTimeMillis();
            System.err.println("并查集: " + unionFind.name() + ", 操作次数: " + n + ", 耗时: " + (endTime - startTime) + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
