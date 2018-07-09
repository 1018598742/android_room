这是一个 android 提供的 sql 库的练习。

```aidl

debugImplementation 'com.amitshekhar.android:debug-db:1.0.3'

这是一个数据库测试库，使用方法，依赖-》在控制台后打出类似下面信息来查看

D/DebugDB: Open http://192.168.88.182:8080 in your browser


可以在 buildTypes 下修改访问的端口号，添加如下代码将端口号修改为 8081

debug {
            resValue("string", "PORT_NUMBER", "8081")
        }

```
