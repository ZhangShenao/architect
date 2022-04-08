package william.concurrent.visibility;

//并发问题的根源之一: 可见性问题
//保证可见性的方案有两种：1.内存屏障 2.上下文切换
//内存屏障在Java层面又多种实现：volatile、synchronized、Lock、final
public class Visibility {
    private boolean flag = true;

    private void stop(){
        flag = false;
        System.out.println("update flag to false");
    }

    private void incr(){
        int i = 0;
        while (flag){
            i++;

            //保证可见性的方式1:final
            //保证可见性的方式2:volatile
            //保证可见性的方式3:Thread.Sleep()
//        try {
//            Thread.sleep(1L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

            //保证可见性的方式4:synchronized
//            System.out.println(i);

            //保证可见性的方式5:上下文切换——Thread.yield()
            Thread.yield();
        }
        System.out.println("i=" + i);
        System.out.println("incr exit");
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility visibility = new Visibility();
        new Thread(visibility::incr).start();

        Thread.sleep(1000L);

        new Thread(visibility::stop).start();

        Thread.sleep(Long.MAX_VALUE);
    }
}
