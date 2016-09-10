package ml.stephen.website.root.framework.mapper;

import ml.stephen.core.mybatis.annotation.MyBatisMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/10.
 */
@MyBatisMapper
public interface FrameworkMapper {

    /**
     * 框架列表
     * @return
     */
    List<Map<String, Object>> selectFrameworkList();

}
