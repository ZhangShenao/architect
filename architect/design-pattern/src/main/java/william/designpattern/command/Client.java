package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 命令模式: 将要执行的操作封装成一个命令对象,实现请求方和接收方的解耦
 */
public class Client {
    public static void main(String[] args) {
        Player player = new Player();

        Command play = new PlayCommand(player);
        play.execute();

        Command speedUp = new SpeedUpCommand(player);
        speedUp.execute();

        Command close = new CloseCommand(player);
        close.execute();
    }
}
