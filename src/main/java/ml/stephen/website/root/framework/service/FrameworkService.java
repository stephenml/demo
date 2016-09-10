package ml.stephen.website.root.framework.service;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.core.cache.RedisCache;
import ml.stephen.website.root.framework.mapper.FrameworkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/10.
 */
@Service
public class FrameworkService {

    @Autowired
    private FrameworkMapper frameworkMapper;
    @Autowired
    protected RedisCache redisCache;

    /**
     * 框架列表
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectFrameworkList() throws Exception {
        return this.frameworkMapper.selectFrameworkList();
    }

    /**
     * 从缓存中获取framework
     * @return
     */
    public List<Map<String, Object>> getFrameworks() throws Exception {
        Object frameworks = this.redisCache.getValue(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_FRAMEWORK);

        if (null == frameworks) {
            frameworks = this.selectFrameworkList();
            this.redisCache.updateKey(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_FRAMEWORK, frameworks, null);
        }

        return (List<Map<String,Object>>) frameworks;
    }

}
