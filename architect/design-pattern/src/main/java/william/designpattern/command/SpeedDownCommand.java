package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 快退命令
 */
public class SpeedDownCommand extends AbstractCommand {
    public SpeedDownCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.speedDown();
    }
}
