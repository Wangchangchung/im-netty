package wcc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author charse
 * @create 2018-10-28
 * @Description:
 */
public class NettyClient {

    private static final  int MAX_RETRT = 3;

    public static void main(String[] args){

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

                //1.指定线程模型
        bootstrap.group(workGroup)
                //2.指定IO类型为NIO
                .channel(NioSocketChannel.class)
                //3.IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

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

        connect(bootstrap, "127.0.0.1",1000, MAX_RETRT);


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

           }else if (retry == 0){
               System.out.println("重试次数已经用完,放弃连接");

           }else {
               int index = (MAX_RETRT - retry)  +1;

               int delayTime = 1<<index;

               System.out.println(new Date()+": 连接失败,第:" + index +"次重新连接");
               bootstrap.config().group().schedule(()->connect(bootstrap, host, port, retry -1), delayTime, TimeUnit.SECONDS);

           }


        });
    }


}
