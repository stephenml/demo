package ml.stephen.dao.table.mapper;

import ml.stephen.core.mybatis.BaseMapper;
import ml.stephen.core.mybatis.annotation.MyBatisMapper;
import ml.stephen.dao.table.entity.Menu;
import ml.stephen.dao.table.entity.MenuExample;

@MyBatisMapper("menuMapper")
public interface MenuMapper extends BaseMapper<Menu, Long, MenuExample> {
}