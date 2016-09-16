package ml.stephen.website.root.framework.service;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.core.cache.RedisCache;
import ml.stephen.dao.table.entity.Framework;
import ml.stephen.dao.table.service.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stephen on 16/9/10.
 */
@Service
public class MFrameworkService {

    @Autowired
    private FrameworkService frameworkService;
    @Autowired
    protected RedisCache redisCache;

    /**
     * 从缓存中获取framework
     * @return
     */
    public List<Framework> getFrameworks() throws Exception {
        Object frameworks = this.redisCache.getValue(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_FRAMEWORK);

        if (null == frameworks) {
            frameworks = this.frameworkService.selectByExample(null); /** 获取所有框架列表 */
            this.redisCache.updateKey(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_FRAMEWORK, frameworks, null);
        }

        return (List<Framework>) frameworks;
    }

}
