package ml.stephen.dao.table.mapper;

import ml.stephen.core.mybatis.BaseMapper;
import ml.stephen.core.mybatis.annotation.MyBatisMapper;
import ml.stephen.dao.table.entity.Framework;
import ml.stephen.dao.table.entity.FrameworkExample;

@MyBatisMapper("frameworkMapper")
public interface FrameworkMapper extends BaseMapper<Framework, Long, FrameworkExample> {
}