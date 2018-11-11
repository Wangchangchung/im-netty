package wcc.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import wcc.netty.client.handler.LoginResponseHandler;
import wcc.netty.codec.PacketDecoder;
import wcc.netty.codec.PacketEncoder;
import wcc.netty.protocol.request.LoginRequestPacket;
import wcc.netty.protocol.request.MessageRequestPacket;
import wcc.netty.service.handler.MessageRequestHandler;
import wcc.netty.utils.SessionUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author charse
 * @create 2018-10-28
 * @Description:
 */
public class NettyClient {

    private static final  int MAX_RETRT = 3;

    private static final String HOST = "127.0.0.1";

    private static final int PORT = 8000;

    public static void main(String[] args){

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

                //1.指定线程模型
        bootstrap.group(workGroup)
                //2.指定IO类型为NIO
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                //3.IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //socketChannel.pipeline().addLast(new ClientHandler());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new MessageRequestHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());

                    }
                });
                //4.建立连接
       /* bootstrap.connect("127.0.0.1",80).addListener(future ->{
            if (future.isSuccess()){
                System.out.println("连接成功");

            }else {
                System.out.println("连接失败");

            }
        });*/
        //bootstrap.attr()
        connect(bootstrap, HOST,PORT, MAX_RETRT);


    }


    /**
     * 失败重试, 重试时间执行间隔幂指数增长
     * @param bootstrap
     * @param host
     * @param port
     * @param retry
     */
    public static void connect(final Bootstrap bootstrap, String host, int port, int retry){
        bootstrap.connect(host, port).addListener(future -> {
           if (future.isSuccess()){
               System.out.println("连接成功");
               Channel channel = ((ChannelFuture)future).channel();
               //开启一个线程,从控制台发送数据
               startConsoleThread(channel);


           }else if (retry == 0){
               System.out.println("重试次数已经用完,放弃连接");

           }else {
               int index = (MAX_RETRT - retry)  +1;

               int delayTime = 1<<index;

               System.out.println(new Date()+": 连接失败,第:" + index +"次重新连接");
               //bootstrap.config 返回 BootstrapConfig: 他是对bootstrap配置参数的抽象
               //.group 返回的是我们一开始设置的线程模型对象, workGroup的.schedule可以实现定时任务的逻辑
               bootstrap.config().group().schedule(()->connect(bootstrap, host, port, retry -1), delayTime, TimeUnit.SECONDS);

           }


        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        new Thread(()->{
           while (!Thread.interrupted()){

               if (!SessionUtil.hasLogin(channel)) {
                   System.out.print("输入用户名登录: ");
                   String username = sc.nextLine();
                   loginRequestPacket.setUserName(username);

                   // 密码使用默认的
                   loginRequestPacket.setPassword("pwd");

                   // 发送登录数据包
                   channel.writeAndFlush(loginRequestPacket);
                   waitForLoginResponse();
               } else {
                   String toUserId = sc.next();
                   String message = sc.next();
                   channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
               }
           }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }


}
