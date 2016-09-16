package ml.stephen.website.root.menu.controller;

import ml.stephen.website.root.menu.service.MMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Stephen on 16/9/10.
 */
@Controller
@RequestMapping(value = "/menu")
public class MMenuController {

    @Autowired
    private MMenuService mMenuService;

    /**
     * 菜单列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() throws Exception {
        return this.mMenuService.getMenus();
    }

}
