package wcc.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author charse
 * @create 2018-10-28
 * @Description:
 */
public class NettyServer {

    public static void main(String[] args){

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workGroup)
                       .channel(NioServerSocketChannel.class)
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {

                           @Override
                           protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                           }
                       });

        //serverBootstrap.bind(8000);
        bind(serverBootstrap, 8000);


    }


    /**
     * 如果绑定端口失败,继续寻找可用的端口
     * @param serverBootstrap
     * @param port
     */
    public static void bind(final  ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()){
                    System.out.println("服务端端口[" + port + "]绑定成功");
                }else {
                    System.out.println("服务端端口[" + port + "]绑定失败");
                    int temp = port +1;
                    bind(serverBootstrap, temp);
                }

            }
        });

    }


}
