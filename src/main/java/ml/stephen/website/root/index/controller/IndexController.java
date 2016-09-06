package ml.stephen.website.root.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Stephen on 16/9/6.
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {

    @RequestMapping(value = "")
    public String index(Map<String, Object> model) {
        return "index";
    }

}
