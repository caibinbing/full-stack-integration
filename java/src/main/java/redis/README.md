# Redis 笔记

+ 如何后台运行Redis？

&emsp;&emsp;修改redis.conf中，deamonize yes。然后运行./bin/redis-server ./redis.conf

+ 如何关闭Redis？

&emsp;&emsp;运行./redis-cli shutdown

+ Redis客户端图形化界面？

&emsp;&emsp;Redis Desktop Manager

+ 如何安装多个Redis？

&emsp;&emsp;正常情况下，不会在同一个服务器上安装多个Redis。学习环境可以安装多个Redis。

&emsp;&emsp;启动时指定端口:./redis-server ../conf/redis.conf --port 6380（默认端口号：6379）

+ 如何切换Redis数据库？

&emsp;&emsp;select 15（在15库。安装完默认16库0-15）

+ 如何清空数据库？

&emsp;&emsp;flush db或者flushall