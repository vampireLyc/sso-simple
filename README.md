## 项目简介
- 简单的单点登录实现
## 实现目标
- demo1和demo2分别进行登录，如果demo1访问没有登录，会跳转到登录页面，demo1登录成功后，demo2请求页面，则不需要登录，可以直接显示请求页面
## 实现原理浅析
- 使用Redis作为session共享的手段，demo1登录成功后，会把cookie的值保存到redis中，demo2得到请求cookie后，可以直接去redis中查询是否有保存该值