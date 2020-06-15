package william.jvm.classloader;

/**
 * @Author zhangshenao
 * @Date 2020-06-14
 * @Description ClassLoader在加载类时, 是lazy模式的按需加载, 只有在真正使用到一个类时才会进行加载, 而不是一次性把所有类全部加载到内存中
 */
public class LazyLoadClass {
    static {
        System.err.println("Load Class TestLazyLoadClass");
    }


    public static void main(String[] args) {
        A a = new A();
        //        B b = null; //这里并没有真正使用到B类,因此不会对B执行类加载

        B b = new B(); //这里真正使用到B类,会对B执行类加载
    }

    private static class A {
        static {
            System.err.println("Load Class A");
        }

        public A() {
            System.err.println("Init A");
        }
    }

    private static class B {
        static {
            System.err.println("Load Class B");
        }

        public B() {
            System.err.println("Init B");
        }
    }
}
