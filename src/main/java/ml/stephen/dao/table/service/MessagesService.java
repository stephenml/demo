package ml.stephen.dao.table.service;

import ml.stephen.core.cache.RedisCache;
import ml.stephen.core.mybatis.BaseMapper;
import ml.stephen.core.mybatis.BaseService;
import ml.stephen.dao.table.entity.Messages;
import ml.stephen.dao.table.entity.MessagesExample;
import ml.stephen.dao.table.mapper.MessagesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messagesService")
public class MessagesService extends BaseService<Messages, Long, MessagesExample> {
    @Autowired
    protected MessagesMapper messagesMapper;

    @Autowired
    protected RedisCache redisCache;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected BaseMapper<Messages, Long, MessagesExample> getBaseMapper() {
        if (super.getBaseMapper() == null) {super.setBaseMapper(messagesMapper);}return super.getBaseMapper();
    }
}