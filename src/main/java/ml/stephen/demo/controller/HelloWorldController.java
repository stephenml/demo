package ml.stephen.demo.controller;

import ml.stephen.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by Stephen on 16/6/27.
 */
@Controller
@RequestMapping(value = "/demo")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping(value = "")
    public String index(Map<String, Object> model) {

        model.put("title", helloWorldService.getTitle());
        model.put("msg", helloWorldService.getMsg());

        return "demo/index";
    }
}
