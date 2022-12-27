package com.etjava.controller;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etjava.MySessionContext;
import com.etjava.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/login")
	public String login(HttpSession session) {
		User u = new User(1,"etjava","123456","common");
		session.setAttribute("currentUser",u);
		System.out.println("session = "+session.getId());
		// 模拟存储用户session 这里使用application上下文  正常应该是redis 
		ServletContext context = session.getServletContext();
		context.setAttribute(u.getId()+"", session.getId());
		
		return "LOGIN SUCCESS";
	}
	
	@RequestMapping("/getUserInfo")
	public User getUserInfo(HttpSession session) {
		
		return (User)session.getAttribute("currentUser");
	}
	
	@RequestMapping("/modifySession")
	public String modifySession(HttpSession session) {
		ServletContext servletContext = session.getServletContext();
		String userId = "1";
		// 添加到上下文时 key是userID作为sessionID
		String sessionId = (String)servletContext.getAttribute(userId);
		System.out.println("sessionID="+sessionId);
		// 获取sessionMap
		Map<String, HttpSession> sessionMap = MySessionContext.sessionMap;
		// 获取session
		HttpSession httpSession = sessionMap.get(sessionId);
		// 获取用户信息
		User user = (User)httpSession.getAttribute("currentUser");
		// 修改用户信息
		user.setLevel("vip");
		return "修改成功";
	}
}
