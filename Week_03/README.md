
作业说明

1.homework/ProxyBizFilter

Request和Response的通用过滤接口和实现类。

.

2.homeowrk/outbound

1)nettyclient

Netty实现Http Client,请求Http03服务。

存在问题：channelRead中ctx.write(response);后
前端没收到响应。程序也主动跑哪了。

不知道该如何调试？请老师指点一下。

2)okhttpclient

OkHttpClient实现请求Http03服务。

数据可以正常返回给前端。
