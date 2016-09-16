package ml.stephen.website.root.messages.controller;

import ml.stephen.website.root.messages.service.MMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Stephen on 16/9/13.
 */
@Controller
@RequestMapping(value = "/messages")
public class MMessagesController {

    @Autowired
    private MMessagesService mMessagesService;

    /**
     * 获取用户头像地址
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/head", method = RequestMethod.GET)
    @ResponseBody
    public Object head(@RequestParam(required = true) String uuid) throws Exception {
        return this.mMessagesService.head(uuid);
    }

    /**
     * 消息列表
     * @param startId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer startId) throws Exception {
        return this.mMessagesService.selectMessagesList(startId);
    }

    /**
     * 发送消息
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object send(@RequestBody Map<String, Object> map) throws Exception {
        return this.mMessagesService.save(map);
    }

}
