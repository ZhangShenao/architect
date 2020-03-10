package william.designpattern.facade;

/**
 * @Author zhangshenao
 * @Date 2020-03-10
 * @Description 门面类, 内部会调度多个子系统
 */
public class Facade {
    //门面内部会调度多个子系统
    private SubSystem subSystemA = new SubSystemA();
    private SubSystem subSystemB = new SubSystemB();
    private SubSystem subSystemC = new SubSystemC();


    public void aWork() {
        subSystemA.work();
    }

    public void bWork() {
        subSystemB.work();
    }

    public void cWork() {
        subSystemC.work();
    }
}
