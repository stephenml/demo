package ml.stephen.website.root.menu.service;

import ml.stephen.website.root.menu.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Stephen on 16/9/8.
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 菜单列表
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectMenuList() throws Exception {
        return this.menuMapper.selectMenuList();
    }

}
