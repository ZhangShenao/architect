package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 暂停命令
 */
public class PauseCommand extends AbstractCommand {
    public PauseCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.pause();
    }
}
