package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 关机命令
 */
public class CloseCommand extends AbstractCommand {
    public CloseCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.close();
    }
}
