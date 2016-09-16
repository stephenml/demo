package ml.stephen.dao.table.service;

import ml.stephen.core.cache.RedisCache;
import ml.stephen.core.mybatis.BaseMapper;
import ml.stephen.core.mybatis.BaseService;
import ml.stephen.dao.table.entity.Framework;
import ml.stephen.dao.table.entity.FrameworkExample;
import ml.stephen.dao.table.mapper.FrameworkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("frameworkService")
public class FrameworkService extends BaseService<Framework, Long, FrameworkExample> {
    @Autowired
    protected FrameworkMapper frameworkMapper;

    @Autowired
    protected RedisCache redisCache;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected BaseMapper<Framework, Long, FrameworkExample> getBaseMapper() {
        if (super.getBaseMapper() == null) {super.setBaseMapper(frameworkMapper);}return super.getBaseMapper();
    }
}