package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 抽象命令
 */
public abstract class AbstractCommand implements Command {
    protected Player player;

    public AbstractCommand(Player player) {
        this.player = player;
    }
}
