package ml.stephen.core.identicon;

import com.google.common.base.Preconditions;
import com.google.common.math.DoubleMath;
import ml.stephen.core.identicon.generator.BaseGenartor;
import ml.stephen.core.identicon.generator.DefaultGenerator;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.RoundingMode;

/**
 * 随机生成用户头像工具
 * https://github.com/superhj1987/awesome-identicon
 * Created by Stephen on 16/9/13.
 */
public class Identicon {

    private BaseGenartor genartor;

    public Identicon() {
        this.genartor = new DefaultGenerator();
    }

    public Identicon(BaseGenartor genartor) {
        this.genartor = genartor;
    }

    public BufferedImage create(String hash, int size) {
        Preconditions.checkArgument(size > 0 && StringUtils.isNotBlank(hash));

        boolean[][] array = genartor.getBooleanValueArray(hash);


        int ratio = DoubleMath.roundToInt(size / 5.0, RoundingMode.HALF_UP);

        BufferedImage identicon = new BufferedImage(ratio * 5, ratio * 5, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = identicon.getGraphics();

        graphics.setColor(genartor.getBackgroundColor()); /** 背景色 */
        graphics.fillRect(0, 0, identicon.getWidth(), identicon.getHeight());

        graphics.setColor(genartor.getForegroundColor()); /** 图案前景色 */
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (array[i][j]) {
                    graphics.fillRect(j * ratio, i * ratio, ratio, ratio);
                }
            }
        }

        return identicon;
    }

}
