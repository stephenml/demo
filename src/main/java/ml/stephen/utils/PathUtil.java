package ml.stephen.utils;

import ml.stephen.constant.Constants;
import ml.stephen.core.utils.PropertyUtil;

/**
 * Created by Stephen on 16/9/13.
 */
public class PathUtil {

    /** 资源上传根目录路径 */
    public static String UPLOAD_BASE_PATH = PropertyUtil.getProperty(Constants.PRONAME_UPLOAD_DIR);
    /** 资源上传根目录url */
    public static String UPLOAD_BASE_URL = PropertyUtil.getProperty(Constants.PRONAME_UPLOAD_DOMAIN);

    /** 用户头像目录路径 */
    public static String USER_HADE_IMG_PATH = UPLOAD_BASE_PATH + "/head";

    /** 用户头像目录url */
    public static String USER_HADE_IMG_URL = UPLOAD_BASE_URL + "/head";


    /**
     * 获取用户头像目录路径
     * @param uuid
     * @return
     */
    public static String getUserHadeImgPath(String uuid) {
        StringBuffer path = new StringBuffer();
        return path.append(USER_HADE_IMG_PATH)
                .append("/")
                .append(uuid).append(".png")
                .toString();
    }

    /**
     * 获取用户头像目录url
     * @param uuid
     * @return
     */
    public static String getUserHadeImgUrl(String uuid) {
        StringBuffer path = new StringBuffer();
        return path.append(USER_HADE_IMG_URL)
                .append("/")
                .append(uuid).append(".png")
                .toString();
    }

}
