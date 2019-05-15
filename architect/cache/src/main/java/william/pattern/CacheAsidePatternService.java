package william.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ZhangShenao
 * @Date: 2019/5/15 11:07
 * @Description:演示Cache-Aside Pattern
 */
@Service
public class CacheAsidePatternService {
    @Autowired
    private CacheService cacheService;

    @Autowired
    private DataDao dataDao;

    //读操作
    public Data getData(String key) {
        //1. 读缓存,如果命中则直接返回
        Data data = cacheService.loadDataFromCache(key);
        if (data != null) {
            return data;
        }

        //2. 缓存未命中,读数据库
        data = dataDao.loadDataFromDB(key);

        //3. 将读取到的数据放入缓存
        cacheService.putData(key,data);

        return data;
    }

    //写操作
    public void updateData(String key,Data data){
        //1. 更新数据库
        dataDao.updateData(key, data);

        //2. 删除缓存
        cacheService.evictData(key);
    }
}
