
作业说明

#1.homework/ProxyBizFilter

Request和Response的通用过滤接口和实现类。

  .

#2.homeowrk/outbound

  ##1. nettyclient

  Netty实现Http Client,请求Http03服务。

  存在问题：

    1) Request的过滤不知该加到那？

    netty client直接通过f.channel().closeFuture().sync();连接Http服务器。

    不知道从哪里获取原始的Request。


    2) channelRead中ctx.write(response);后，
    前端没收到响应。程序也不知跑哪了。

    不知道该如何调试？


  ##2. okhttpclient

  OkHttpClient实现请求Http03服务。

  数据可以正常返回给前端。

  实现了Request和Response的过滤，已经路由。


#2.homeowrk/router

路由接口和随机路由实现。




