package chapter2;

/**
 * @Author zhangshenao <zhangshenao@kuaishou.com>
 * @Date 2019-08-27
 * @Description
 */
public final class CaseInsensitiveString {
    private final String str;

    public CaseInsensitiveString(String str) {
        this.str = str;
    }


    @Override
    public boolean equals(Object obj) {
        //重写equals方法,满足对称性
        return obj instanceof CaseInsensitiveString && str.equalsIgnoreCase(((CaseInsensitiveString) obj).str);
    }
}
