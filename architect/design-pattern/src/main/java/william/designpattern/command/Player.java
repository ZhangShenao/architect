package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 播放器, 接收命令的一方
 */
public class Player {
    public void play() {
        System.err.println("播放");
    }

    public void pause() {
        System.err.println("暂停");
    }

    public void close() {
        System.err.println("关机");
    }

    public void speedUp() {
        System.err.println("快进");
    }

    public void speedDown() {
        System.err.println("快退");
    }
}
