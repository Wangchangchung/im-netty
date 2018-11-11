package wcc.netty.attribute;

import io.netty.util.AttributeKey;
import wcc.netty.session.Session;

/**
 * @author charse
 * @create 2018-11-03
 * @Description:
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
