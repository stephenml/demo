package ml.stephen.website.root.menu.service;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.core.cache.RedisCache;
import ml.stephen.dao.table.entity.Menu;
import ml.stephen.dao.table.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Stephen on 16/9/8.
 */
@Service
public class MMenuService {

    @Autowired
    private MenuService menuService;
    @Autowired
    protected RedisCache redisCache;

    /**
     * 从缓存中获取menu
     * @return
     */
    public List<Menu> getMenus() throws Exception {
        Object menus = this.redisCache.getValue(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_MENU);

        if (null == menus) {
            menus = this.menuService.selectByExample(null); /** 获取所有的菜单 */
            this.redisCache.updateKey(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_MENU, menus, null);
        }

        return (List<Menu>) menus;
    }

}
