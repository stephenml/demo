package ml.stephen.core.identicon.generator;

import java.awt.*;

/**
 * Created by Stephen on 16/9/13.
 */
public interface BaseGenartor {

    /**
     * 将hash字符串转换为bool二维6*5数组
     * @param hash
     * @return
     */
    boolean[][] getBooleanValueArray(String hash);

    /**
     * 获取图片背景色
     * @return
     */
    Color getBackgroundColor();

    /**
     * 获取图案前景色
     * @return
     */
    Color getForegroundColor();

}
