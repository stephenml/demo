package ml.stephen.website.root.index.controller;

import ml.stephen.website.root.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Stephen on 16/9/6.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 菜单列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    @ResponseBody
    public Object list() throws Exception {
        return this.indexService.menuList();
    }

}
