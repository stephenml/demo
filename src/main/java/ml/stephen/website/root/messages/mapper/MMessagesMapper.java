package ml.stephen.website.root.messages.mapper;

import ml.stephen.core.mybatis.annotation.MyBatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/13.
 */
@MyBatisMapper
public interface MMessagesMapper {

    /**
     * 消息列表
     * 未指定id 取最后十条 指定id 取这个id后的所有
     * @param startId
     * @return
     */
    List<Map<String, Object>> selectMessagesList(@Param("startId") Integer startId);

}
