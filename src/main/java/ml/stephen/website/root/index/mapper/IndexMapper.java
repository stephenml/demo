package ml.stephen.website.root.index.mapper;

import ml.stephen.core.mybatis.annotation.MyBatisMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/6.
 */
@MyBatisMapper
public interface IndexMapper {

    /**
     * 菜单列表
     * @return
     */
    public List<Map<String, Object>> menuList();

}
