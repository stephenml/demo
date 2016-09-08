package ml.stephen.website.web.init.service;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.core.cache.RedisCache;
import ml.stephen.website.root.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/8.
 * 初始化redis
 */
@Service
public class InitRedisService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MenuService menuService;

    @Async
    public void asyncInit() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("清空redis缓存");
        }

        this.redisCache.flushAllRetainSession();
        this.initMenu();
    }

    /**
     * 初始化menu
     * @throws Exception
     */
    private void initMenu() throws Exception {
        logger.debug("初始化表[menu], key : " + ServiceConstants.CACHE_TABLE_MENU);

        List<Map<String, Object>> menus = this.menuService.selectMenuList();
        this.redisCache.updateKey(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_MENU, menus, null);
    }

}
