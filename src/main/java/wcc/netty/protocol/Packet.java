package wcc.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author charse
 * @create 2018-10-29
 * @Description: 基础发送包
 */
public abstract class Packet {

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte vesion = 1;


    @JSONField(serialize = false)
    public abstract  Byte getCommand();

    public Byte getVesion() {
        return vesion;
    }

    public void setVesion(Byte vesion) {
        this.vesion = vesion;
    }
}
