package ml.stephen.website.root.index.service;

import ml.stephen.core.mybatis.page.PageHelper;
import ml.stephen.core.mybatis.page.PageList;
import ml.stephen.website.root.index.mapper.IndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 16/9/6.
 */
@Service
public class IndexService {

    @Autowired
    private IndexMapper indexMapper;

    /**
     * 获取列表 分页
     * @param pageNum
     * @param pageSize
     * @return
     * @throws Exception
     */
    public Map<String, Object> list(Integer pageNum, Integer pageSize) throws Exception {
        Map<String, Object> rtn = new HashMap<String, Object>();

        PageHelper.startPage(pageNum, pageSize);
        PageList<Map<String, Object>> list = this.indexMapper.list();

        rtn.put("pageNum", list.getPageNum());
        rtn.put("pageSize", list.getPageSize());
        rtn.put("totalCount", list.getTotal());
        rtn.put("data", list.getResult());

        return rtn;
    }

}
