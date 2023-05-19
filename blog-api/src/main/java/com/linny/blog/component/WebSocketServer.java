package com.linny.blog.component;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/{sid}")
@Component
public class WebSocketServer {

    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);// 配置日志

    private static int onlineCount = 0;//静态变量，用来记录当前在线连接人数

    private Session session;           //与某个客户端的连接会话，需要通过他来给客户端发送数据
                                       //接收用户的sid，指定需要推送的用户
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap
                                               = new ConcurrentHashMap<>();

    private String sid;// 接收sid,指定需要推送的用户


    /**
     * 连接成功后调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketMap.put(sid,this);// 加入map中
        addOnlineCount(); //人数加+1
        logger.info("有新窗口开始监听"+sid+",当前在线人数为"+getOnlineCount());

    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.get(this.sid)!=null){
            //webSocketSet.remove(this);  //从set中删除
            webSocketMap.remove(this.sid);  //从map中删除
            subOnlineCount();           //在线数减1
            logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
        }
    }



    /**
     *收到客户端消息后调用的方法，根据业务要求进行处理，这里就简单地将收到的消息直接群发推送出去
     * @param message 客户端发送过来的消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message,Session session){
        logger.info("收到来自窗口"+sid + "的信息:"+message);
        if (StringUtils.isNotBlank(message)){
            for (WebSocketServer server: webSocketMap.values()){
                try {
                    server.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    /**
     * 发生错误时的回调函数
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){
        logger.error("发生错误");
        error.printStackTrace();
    }


    /**
     * 实现服务器主动推送消息
     */
    private void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(@PathParam("message") String message,@PathParam("sid") String sid){
        logger.info("推送消息到窗口"+sid+",推送内容:"+message);

        if (!StringUtils.isBlank(message)){
            for (WebSocketServer server: webSocketMap.values()){
                if(sid==null){
                    server.sendMessage(message);
                }else if (server.sid.equals(sid)){
                    server.sendMessage(message);
                }
            }
        }
    }


    synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }

     public synchronized int getOnlineCount(){
        return onlineCount;
    }

    synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
