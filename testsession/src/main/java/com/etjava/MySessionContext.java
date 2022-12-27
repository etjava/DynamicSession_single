package com.etjava;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * 自定义session存储
 * @author etjav
 *
 */
public class MySessionContext {

	private static MySessionContext instance;
	
	public static Map<String,HttpSession> sessionMap;
	
	private MySessionContext() {
		sessionMap = new HashMap<>();
	}
	
	public static MySessionContext getInstance() {
		if(instance==null) {
			instance = new MySessionContext();
		}
		return instance;
	}
	
	// 添加session
	public synchronized void addSession(HttpSession session) {
		if(session!=null) {
			System.out.println("添加session = "+session.getId());
			sessionMap.put(session.getId(), session);
		}
	}
	
	// 删除session
	public synchronized void delSession(HttpSession session) {
		if(session!=null) {
			System.out.println("删除session = "+session.getId());
			sessionMap.remove(session.getId());
		}
	}
	
	public synchronized HttpSession getSession(String sessionID) {
		if(sessionID==null) {
			return null;
		}
		return sessionMap.get(sessionID);
	}
	
}
