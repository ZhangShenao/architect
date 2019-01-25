package william.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/18 09:51
 * @Description:The Usage of Fork/Join Framework
 * Use RecursiveTask to do Some Computation with Results and RecursiveActive without Result
 * Use ForkJoinPool to Invoke Tasks
 * Work-Stealing
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int size = 1000000;
        double[] datas = new double[size];
        for (int i = 0;i < size;i++){
            datas[i] = Math.random();
        }
        Counter counter = new Counter(0, size, datas, (d -> d > 0.5));

        //Invoke Sync,Block Until Compute Completely
        forkJoinPool.invoke(counter);
        System.err.println(counter.join());
    }

    public static class Counter extends RecursiveTask<Integer> {
        private static final int THRESHOLD = 1000;

        private int from;
        private int to;
        private double[] datas;
        private DoublePredicate filter;

        public Counter(int from, int to, double[] datas, DoublePredicate filter) {
            this.from = from;
            this.to = to;
            this.datas = datas;
            this.filter = filter;
        }

        @Override
        protected Integer compute() {
            //Within Threshold,Compute Directly
            if ((to - from) < THRESHOLD){
                int count = 0;
                for (int i = from; i < to;i++){
                    if (filter.test(datas[i])){
                        count++;
                    }
                }
                return count;
            }

            //Beyond Threshold,Fork into 2 Sub-Tasks
            int mid = (from + to) / 2;
            Counter first = new Counter(from,mid,datas,filter);
            Counter second = new Counter(mid,to,datas,filter);
            invokeAll(first,second);

            //Join Results,Block Until Complete.
            return (first.join() + second.join());
        }
    }
}
