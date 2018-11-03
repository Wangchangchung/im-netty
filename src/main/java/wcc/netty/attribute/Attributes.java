package wcc.netty.attribute;

import io.netty.util.AttributeKey;

/**
 * @author charse
 * @create 2018-11-03
 * @Description:
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
