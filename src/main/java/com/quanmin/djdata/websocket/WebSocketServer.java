package com.quanmin.djdata.websocket;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: ate
 * @Description: websocket
 * @CreateDate: 2019-11-18 10:24
 * @ClassName: com.quanmin.djdata.websocket.WebSocket
 */
@Component
@ServerEndpoint(value = "/ws/{account}")
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /** 获取redission客户端连接 */
	//private RedissonClient redissonClient = SpringContextHolder.getBean("redissonClient");
    /** ConcurrentHashMap是线程安全的，而HashMap是线程不安全的 */
    private static ConcurrentHashMap<String, Session> mapUS = new ConcurrentHashMap<String, Session>();
    /** US(UserSession),SU(SessionUser) */
    private static ConcurrentHashMap<Session, String> mapSU = new ConcurrentHashMap<Session, String>();
    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private String sid="";

    /**
     * @author: ate
     * @description: 建立连接
     * @date: 2019/11/18 10:25
     * @param: [session, account]
     * @return: void
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("account") String account) {
    	// 如果为空表示心跳检查
		if (StringUtils.isEmpty(account)){
			account = "ping";
		}
		this.session = session;
		webSocketSet.add(this);     //加入set中
		addOnlineCount();           //在线数加1
		logger.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
		this.sid=sid;
		try {
			sendMessage("连接成功");
		} catch (IOException e) {
			logger.error("websocket IO异常");
		}
//		String jsonResult = setNoticeMessage(account, "online");
//		// 循环发给所有在线的人，上线通知
//		for(Session s: session.getOpenSessions()){
//			s.getAsyncRemote().sendText(jsonResult);
//		}
//		// 设置在线放到MAP
//		this.setOnlinePutMap(session, account);
	}

    private String setNoticeMessage(String account, String status) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("content", status);
		jsonObject.put("account", account);
		jsonObject.put("type", "onlineStatus");
		return JSONObject.toJSONString(jsonObject);
	}

	private void setOnlinePutMap(Session session, String account) {
		mapUS.put(account, session);
		mapSU.put(session, account);
		logger.info(String.format("%s进入聊天,当前在线人数为%d", account, mapUS.size()));
	}

	/**
	 * @author: ate
	 * @description: 连接关闭
	 * @date: 2019/11/18 10:30
	 * @param: [session]
	 * @return: void
	 */
	@OnClose
	public void onClose(Session session) {
		String account = mapSU.get(session);
		if (!StringUtils.isEmpty(account)) {
			// 设置离线且移除MAP
			this.setOfflineRemoveMap(session, account);
		}
		webSocketSet.remove(this);  //从set中删除
		subOnlineCount();           //在线数减1
		logger.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * @author: ate
	 * @description: 发生错误
	 * @date: 2019/11/18 10:30
	 * @param: [session, error]
	 * @return: void
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		String account = mapSU.get(session);
		if (!StringUtils.isEmpty(account)) {
			// 设置离线且移除MAP
			this.setOfflineRemoveMap(session, account);
		}
		logger.error("WebSocket发生错误! {}", error.getStackTrace());
	}

	/**
	 * @author: ate
	 * @description: 收到客户端消息
	 * @date: 2019/11/18 10:30
	 * @param: [message, session]
	 * @return: void
	 */
	@OnMessage
	public void onMessage(String message, Session session){
		if(message.equals("ping")){
			session.getAsyncRemote().sendText("pong");
		}else{
			JSONObject jsonObject = JSONObject.parseObject(message);
			logger.info("000000000000000000000");
			/*String type = jsonObject.getJSONObject("to").getString("type");
			if(type.equals("onlineStatus")){
				// 循环发给所有在线的人
				this.sendAllOnline(session, jsonObject, type);
			} else if(type.equals("sendMessage")){
				// 发送消息
				this.sendMessage2(session, jsonObject, type);
			}*/
		}
	}

	/**
	 * @author: ate
	 * @description: 群发消息
	 * @date: 2019/11/18 12:15
	 * @param: [message, sid]
	 * @return: void
	 */
	public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
		logger.info("推送消息到窗口"+sid+"，推送内容:"+message);
		for (WebSocketServer item : webSocketSet) {
			try {
				//这里可以设定只推送给这个sid的，为null则全部推送
				if(sid==null) {
					item.sendMessage(message);
				}else if(item.sid.equals(sid)){
					item.sendMessage(message);
				}
			} catch (IOException e) {
				continue;
			}
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

	/**
	 * @author: ate
	 * @description: 设置离线且移除MAP
	 * @date: 2019/11/18 10:30
	 * @param: [session, account]
	 * @return: void
	 */
	private void setOfflineRemoveMap(Session session, String account) {
		mapUS.remove(account);
		mapSU.remove(session);
		logger.info(String.format("%s退出聊天,当前在线人数为%d", account, mapUS.size()));
	}

	/**
	 * @author: ate
	 * @description: 循环发给所有在线的人
	 * @date: 2019/11/18 10:33
	 * @param: [session, jsonObject, type]
	 * @return: void
	 */
	private void sendAllOnline(Session session, JSONObject jsonObject, String type) {
		for(Session s:session.getOpenSessions()){
			JSONObject toMessage = new JSONObject();
			toMessage.put("account", jsonObject.getJSONObject("mine").getString("account"));
			toMessage.put("content", jsonObject.getJSONObject("mine").getString("content"));
			toMessage.put("type", type);
			s.getAsyncRemote().sendText(toMessage.toString());
		}
	}

	/**
	 * @author: ate
	 * @description: 服务器主动推送
	 * @date: 2019/11/18 12:14
	 * @param: [message]
	 * @return: void
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}

	/**
	 * @author: ate
	 * @description: 发送消息
	 * @date: 2019/11/18 12:22
	 * @param: [session, message]
	 * @return: void
	 */
	private void sendMessage2(Session session, JSONObject jsonObject, String type) {

    }

}
