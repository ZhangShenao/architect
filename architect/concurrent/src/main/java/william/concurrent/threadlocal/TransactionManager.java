package william.concurrent.threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author zhangshenao
 * @Date 2019-08-30
 * @Description 使用ThreadLocal实现的事务管理器, 每个线程绑定一个事务
 */
public class TransactionManager {
    //业务线程
    private static final class BizTask implements Runnable{
        private final ThreadLocal<Transaction> transaction;

        public BizTask(){
            //创建事务,并与当前线程绑定
            transaction = ThreadLocal.withInitial(() -> {
                long id = Thread.currentThread().getId();
                return new Transaction(id);
            });
        }

        @Override
        public void run() {
            transaction.get().begin();
            System.err.println("执行业务逻辑");
            try {
                Thread.sleep(1000L);
                transaction.get().commit();
                transaction.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //事务
    private static final class Transaction {
        private long id;
        private TransactionStatus status;

        public Transaction(long id) {
            this.id = id;
        }

        public void begin(){
            status = TransactionStatus.BEGIN;
            System.err.println("开启事务, id: " + id + ", status: " + status);
        }

        public void commit() {
            status = TransactionStatus.COMMIT;
            System.err.println("提交事务, id: " + id + ", status: " + status);
        }

        public void rollback() {
            status = TransactionStatus.ROLLBACK;
            System.err.println("回滚事务, id: " + id + ", status: " + status);
        }
    }

    //事务状态
    private enum TransactionStatus {
        BEGIN,
        COMMIT,
        ROLLBACK,

    }

    private ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(() -> 1);
    private ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(() -> "abc");
    private ThreadLocal<Object> threadLocal3 = ThreadLocal.withInitial(Object::new);

    public static void main(String[] args) throws InterruptedException {
        int threadNum = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadNum);

        //执行业务逻辑,可以看到每个线程的事务相互独立,互不影响
        for (int i = 0; i < threadNum; i++) {
            executor.submit(new BizTask());
        }
        executor.shutdown();

    }

}
