package ml.stephen.website.root.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Stephen on 16/9/6.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
