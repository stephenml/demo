package ml.stephen.dao.table.mapper;

import ml.stephen.core.mybatis.BaseMapper;
import ml.stephen.core.mybatis.annotation.MyBatisMapper;
import ml.stephen.dao.table.entity.Messages;
import ml.stephen.dao.table.entity.MessagesExample;

@MyBatisMapper("messagesMapper")
public interface MessagesMapper extends BaseMapper<Messages, Long, MessagesExample> {
}