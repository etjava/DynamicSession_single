package com.etjava.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.etjava.MySessionContext;

@WebListener
public class SessionListener implements HttpSessionListener {

	// session contenxt
	private MySessionContext context = MySessionContext.getInstance();
	
	
	// create session 
	@Override
	public void sessionCreated(HttpSessionEvent as) {
		System.out.println("创建session");
		HttpSession session = as.getSession();
		context.addSession(session);
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent ae) {
		System.out.println("销毁session");
		HttpSession session = ae.getSession();
		// 如果使用了缓存 则需要在缓存中同步删除 指定用户的session
		context.delSession(session);
	}
	
}
