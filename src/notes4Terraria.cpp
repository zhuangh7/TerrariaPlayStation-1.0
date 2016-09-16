***forNotes

Client Host to Server:
	1.connect ServerSocket(7778) for first time with a String "0" for creating a new Room and return RoomID;
	2.connect ServerSocket(7778) for second or more time with a String not "0" for refresh a existed room's Host Socket;
Client to Server:
	1.connect ServerSocket(7777) with a String means RoomId
		a.if the RoomId is existed, then begin the fun();
		b.else if the RoomId is not existed, just close the Socket;

7.生成可执行的jar包。
在I:\myApp路径下输入如下命令：
jar -cef test.CardLayoutDemo CardLayoutDemo.jar test
以上命令及参数的含义如下：
jar命令为java自带的专用打包工具；
c代表生成新的jar包；
e代表可执行的类，亦即main方法所在的类。书写时要加上包名，在本例中是后面的test.CardLayoutDemo；
f代表生成的jar包的名称，在本例中是CardLayoutDemo.jar。此包名可以随意命名，没有规定；
test最后面的这个参数表示将test目录下的所有文件都打包放到新的jar包中。

jar -cef Client4Terraria TerrariaPS-1.0.jar *.*

***************************************************************
'阶段性成果 第一次可以，第二次若不重启一下客户端重新加入房间就GG，啊,没有测试是否需要重启服务器。这是明天的任务