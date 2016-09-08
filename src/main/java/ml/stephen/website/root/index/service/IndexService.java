package ml.stephen.website.root.index.service;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.core.cache.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/6.
 */
@Service
public class IndexService {

    @Autowired
    protected RedisCache redisCache;

    /**
     * 从缓存中获取menu
     * @return
     */
    public List<Map<String, Object>> getMenus() throws Exception {
        Object menus = this.redisCache.getValue(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_MENU);
        return (List<Map<String,Object>>) menus;
    }

}
