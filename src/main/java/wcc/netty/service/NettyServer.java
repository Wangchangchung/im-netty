package wcc.netty.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import wcc.netty.client.handler.CreateGroupResponseHandler;
import wcc.netty.client.handler.LogoutResponseHandler;
import wcc.netty.codec.Spliter;
import wcc.netty.service.handler.AuthHandler;
import wcc.netty.codec.PacketDecoder;
import wcc.netty.codec.PacketEncoder;
import wcc.netty.service.handler.LogInRequestHandler;
import wcc.netty.service.handler.MessageRequestHandler;

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
                                nioSocketChannel.pipeline().addLast(new Spliter());
                                nioSocketChannel.pipeline().addLast(new PacketDecoder());
                                nioSocketChannel.pipeline().addLast(new LogInRequestHandler());
                                nioSocketChannel.pipeline().addLast(new LogoutResponseHandler());
                                nioSocketChannel.pipeline().addLast(new CreateGroupResponseHandler());
                                nioSocketChannel.pipeline().addLast(new AuthHandler());
                                nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                                nioSocketChannel.pipeline().addLast(new PacketEncoder());
                           }
                       });
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
