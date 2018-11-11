package wcc.netty.utils;

import io.netty.channel.Channel;
import wcc.netty.attribute.Attributes;
import wcc.netty.session.Session;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author charse
 * @create 2018-11-11
 * @Description:
 */
public class SessionUtil {

    public static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();


    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel){
        if (hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel){
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel){
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId){
        return userIdChannelMap.get(userId);
    }


}
