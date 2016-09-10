package ml.stephen.website.root.framework.controller;

import ml.stephen.website.root.framework.service.FrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Stephen on 16/9/10.
 */
@Controller
@RequestMapping(value = "/framework")
public class FrameworkController {

    @Autowired
    private FrameworkService frameworkService;

    /**
     * 框架列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() throws Exception {
        return this.frameworkService.getFrameworks();
    }

}
