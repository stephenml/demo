package ml.stephen.website.root.index.service;

import ml.stephen.website.root.index.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/6.
 */
@Service
public class IndexService {

    @Autowired
    private IndexMapper indexMapper;

    /**
     * 菜单列表
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> menuList() throws Exception {
        return this.indexMapper.menuList();
    }

}
