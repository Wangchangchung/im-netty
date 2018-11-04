package wcc.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import wcc.netty.service.inbound.InBoundHandlerA;
import wcc.netty.service.inbound.InBoundHandlerB;
import wcc.netty.service.inbound.InBoundHandlerC;
import wcc.netty.service.outbund.OutBoundHandlerA;
import wcc.netty.service.outbund.OutBoundHandlerB;
import wcc.netty.service.outbund.OutBoundHandlerC;

/**
 * @author charse
 * @create 2018-10-28
 * @Description:
 */
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args){

        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workGroup)
                       .channel(NioServerSocketChannel.class)
                       .option(ChannelOption.SO_BACKLOG, 1024)
                       .childOption(ChannelOption.SO_KEEPALIVE, true)
                       .childOption(ChannelOption.TCP_NODELAY, true)
                       .childHandler(new ChannelInitializer<NioSocketChannel>() {

                           @Override
                           protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                               //nioSocketChannel.pipeline().addLast(new ServiceHandler());
                               //inBound, 处理读数据的逻辑链
                               nioSocketChannel.pipeline().addLast(new InBoundHandlerA());
                               nioSocketChannel.pipeline().addLast(new InBoundHandlerB());
                               nioSocketChannel.pipeline().addLast(new InBoundHandlerC());

                               //outBound, 处理写数据的逻辑
                               nioSocketChannel.pipeline().addLast(new OutBoundHandlerA());
                               nioSocketChannel.pipeline().addLast(new OutBoundHandlerB());
                               nioSocketChannel.pipeline().addLast(new OutBoundHandlerC());



                           }
                       });

        //serverBootstrap.bind(8000);
        bind(serverBootstrap, PORT);


    }


    /**
     * 如果绑定端口失败,继续寻找可用的端口
     * @param serverBootstrap
     * @param port
     */
    public static void bind(final  ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(future -> {
                if (future.isSuccess()){
                    System.out.println("服务端端口[" + port + "]绑定成功");
                }else {
                    System.out.println("服务端端口[" + port + "]绑定失败");
                    int temp = port +1;
                    bind(serverBootstrap, temp);
                }

        });

    }


}
