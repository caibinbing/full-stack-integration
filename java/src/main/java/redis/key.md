# Key
键

+ keys pattern

查看所有符合给定模式pattern的key

+ exists key

检查给定key是否存在，存在返回1，不存在返回0。

+ expire key seconds

为给定key设置生存时间，当key过期时，它会被自动删除。

+ rename key newkey

将key改名为newkey。

+ type key

返回key所存储的值的类型。

+ move key db

将当前数据库的key移动到给定数据库db中。

