package william.designpattern.command;

/**
 * @Author zhangshenao
 * @Date 2020-03-16
 * @Description 播放命令
 */
public class PlayCommand extends AbstractCommand {
    public PlayCommand(Player player) {
        super(player);
    }

    @Override
    public void execute() {
        player.play();
    }
}
