package william.concurrent.bits;

/**
 * @Author zhangshenao
 * @Date 2019-09-03
 * @Description 使用位运算进行权限操作
 */
public class Permission {
    //权限定义
    public static final int QUERY = 1 << 0;
    public static final int MODIFY = 1 << 1;
    public static final int DELETE = 1 << 2;

    //当前权限状态
    private int state;

    public static Permission valueOf(int state) {
        Permission p = new Permission();
        p.state = state;
        return p;
    }

    //判断是否有权限,采用位运算
    public boolean isEnableQuery() {
        return (state & QUERY) == QUERY;
    }

    public boolean isEnableModify() {
        return (state & MODIFY) == MODIFY;
    }

    public boolean isEnableDelete() {
        return (state & DELETE) == DELETE;
    }

    //增加权限,使用位运算
    public void enable(int permission) {
        state = state | permission;
    }

    //禁用权限,使用位运算
    public void disable(int permission) {
        state = state & ~permission;
    }

    public static void main(String[] args) {
        Permission permission = valueOf(0);
        permission.enable(QUERY);
        permission.enable(MODIFY);
        System.err.println("isEnableQuery: " + permission.isEnableQuery());
        System.err.println("isEnableModify: " + permission.isEnableModify());
        System.err.println("isEnableDelete: " + permission.isEnableDelete());

    }
}
