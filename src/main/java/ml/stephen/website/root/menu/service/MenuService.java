package ml.stephen.website.root.menu.service;

import ml.stephen.constant.ServiceConstants;
import ml.stephen.core.cache.RedisCache;
import ml.stephen.website.root.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/8.
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    protected RedisCache redisCache;

    /**
     * 菜单列表
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectMenuList() throws Exception {
        return this.menuMapper.selectMenuList();
    }

    /**
     * 从缓存中获取menu
     * @return
     */
    public List<Map<String, Object>> getMenus() throws Exception {
        Object menus = this.redisCache.getValue(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_MENU);

        if (null == menus) {
            menus = this.selectMenuList();
            this.redisCache.updateKey(ServiceConstants.CACHE_TABLE_DBINDEX, ServiceConstants.CACHE_TABLE_MENU, menus, null);
        }

        return (List<Map<String,Object>>) menus;
    }

}
