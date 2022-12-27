# DynamicSession_single
动态修改session 单体应用
# 场景介绍
用户充值会员，对应的session信息需要实时变动 否则用户充值成功后需要再次登录才会显示会员信息
针对这个问题可以通过session来实现 因为 session中可以封装当前用户信息

## 实现步骤
1. 用户登录获取session和当前用户ID
2. session和用户ID存放到redis或ServletContext中 格式{userId:sessionId}
3. 用户登录时通过session监听器创建session 信息保存到一个Map中
4. 添加session信息到sessionMap中 格式{userId:session对象}
5. 根据userId到ServletContext查询到sessionId
6. 根据SessionId到SessionMap中查出对应的session对象
7. 从session对象获取当前用户信息并进行修改
8. 修改后重新放回sessionId
9. 前端刷新时获取到新的sessionId就可以获取到当前用户的最新等级信息



