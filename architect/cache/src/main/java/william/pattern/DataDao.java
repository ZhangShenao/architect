package william.pattern;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/15 11:07
 * @Description:
 */
public interface DataDao {
    Data loadDataFromDB(String key);

    void updateData(String key, Data data);
}
