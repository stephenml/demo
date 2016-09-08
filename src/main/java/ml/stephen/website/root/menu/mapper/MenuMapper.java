package ml.stephen.website.root.menu.mapper;

import ml.stephen.core.mybatis.annotation.MyBatisMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/8.
 */
@MyBatisMapper
public interface MenuMapper {

    /**
     * 菜单列表
     * @return
     */
    List<Map<String, Object>> selectMenuList();

}
