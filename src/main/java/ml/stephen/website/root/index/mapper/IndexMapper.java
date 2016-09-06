package ml.stephen.website.root.index.mapper;

import ml.stephen.core.mybatis.annotation.MyBatisMapper;
import ml.stephen.core.mybatis.page.PageList;

import java.util.Map;

/**
 * Created by Stephen on 16/9/6.
 */
@MyBatisMapper
public interface IndexMapper {

    public PageList<Map<String, Object>> list();

}
