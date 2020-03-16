package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 快进命令
 */
public class SpeedUpCommand extends AbstractCommand {
    public SpeedUpCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.speedUp();
    }
}
