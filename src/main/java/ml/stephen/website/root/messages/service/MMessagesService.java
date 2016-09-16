package ml.stephen.website.root.messages.service;

import ml.stephen.core.identicon.Identicon;
import ml.stephen.core.utils.BeanMapUtil;
import ml.stephen.dao.table.entity.Messages;
import ml.stephen.dao.table.service.MessagesService;
import ml.stephen.utils.PathUtil;
import ml.stephen.website.root.messages.mapper.MMessagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Stephen on 16/9/13.
 */
@Service
public class MMessagesService {

    @Autowired
    private MMessagesMapper mMessagesMapper;
    @Autowired
    private MessagesService messagesService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取用户头像地址
     * @param uuid
     * @return
     * @throws Exception
     */
    public String head(String uuid) throws Exception {
        String headPath = PathUtil.getUserHadeImgPath(uuid);
        File headFile = new File(headPath);
        if (!headFile.exists()) {
            /** 先创建文件夹 */
            File headDirectory = new File(PathUtil.USER_HADE_IMG_PATH);
            if (!headDirectory.exists()) {
                headDirectory.mkdirs();
            }
            /** 生成头像 */
            Identicon identicon = new Identicon();
            BufferedImage image = identicon.create(uuid, 128);
            ImageIO.write(image, "PNG", headFile);
        }

        return PathUtil.getUserHadeImgUrl(uuid);
    }

    /**
     * 消息列表
     * @param startId
     * @return
     * @throws Exception
     */
    public List<Map<String, Object>> selectMessagesList(Integer startId) throws Exception {
        List<Map<String, Object>> messagesList = this.mMessagesMapper.selectMessagesList(startId);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> map : messagesList) {
            String uuid = map.get("uuid").toString();
            map.put("head", PathUtil.getUserHadeImgUrl(uuid));
            map.put("commit_date", this.simpleDateFormat.format(map.get("commit_date")));
            list.add(map);
        }

        return list;
    }

    /**
     * 发送消息
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> save(Map<String, Object> map) throws Exception {
        Messages messages = BeanMapUtil.toBean(map, Messages.class);
        messages.setCommitDate(new Date());
        this.messagesService.insertSelective(messages);
        return BeanMapUtil.toMap(messages, this.simpleDateFormat);
    }

}
