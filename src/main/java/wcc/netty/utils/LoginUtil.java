package wcc.netty.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import wcc.netty.attribute.Attributes;

/**
 * @author charse
 * @create 2018-11-03
 * @Description:
 */
public class LoginUtil {

    //设置登录的标记
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    //从channel中获取是否已经登录
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;

    }
}
