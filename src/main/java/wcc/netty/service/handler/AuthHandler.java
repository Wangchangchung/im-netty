package wcc.netty.service.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import wcc.netty.utils.SessionUtil;

/**
 * @author charse
 * @create 2018-11-11
 * @Description:
 */
public class AuthHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!SessionUtil.hasLogin(ctx.channel())){
            ctx.channel().close();

        }else {
            //如果登录成功,动态将Handler进行删除
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);

        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(SessionUtil.hasLogin(ctx.channel())){
            System.out.println("当前连接登录验证完毕,无需再次验证,AuthHandler被移除");
        }else {
            System.out.println("无登录验证,强制关闭连接!");
        }
    }
}
