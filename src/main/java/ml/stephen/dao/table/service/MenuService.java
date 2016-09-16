package ml.stephen.dao.table.service;

import ml.stephen.core.cache.RedisCache;
import ml.stephen.core.mybatis.BaseMapper;
import ml.stephen.core.mybatis.BaseService;
import ml.stephen.dao.table.entity.Menu;
import ml.stephen.dao.table.entity.MenuExample;
import ml.stephen.dao.table.mapper.MenuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("menuService")
public class MenuService extends BaseService<Menu, Long, MenuExample> {
    @Autowired
    protected MenuMapper menuMapper;

    @Autowired
    protected RedisCache redisCache;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected BaseMapper<Menu, Long, MenuExample> getBaseMapper() {
        if (super.getBaseMapper() == null) {super.setBaseMapper(menuMapper);}return super.getBaseMapper();
    }
}