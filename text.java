import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;

//By Zhuangh7
public class text{
	public static void main(String[] args){
		int port;
		System.out.println("begin~");
		if(args.length!=0){
			port = Integer.parseInt(args[0]);
		}
		else{
			port = 7777;
		}
		//这里连接服务器持续监听一个tag
		while(true){
			try{
				Socket ss = new Socket("123.206.208.46",7778);
				System.out.println("server connected~");
				BufferedReader br=new BufferedReader(new InputStreamReader(ss.getInputStream()));  
				String r;
				System.out.println("begin listen");
				br.readLine();
				System.out.println("get");
				Socket localS = new Socket("127.0.0.1",port);
				System.out.println("local connected~");
				System.out.println("begin fun~");
				new up(ss,localS).start();
				new down(ss,localS).start();
				while(true){

				}
				
			}catch(Exception e){
				System.out.print("connect server failed");
				e.printStackTrace();
			}
		}
	}
	/*public void connect(Socket s){
		try{
			Socket socket  = new Socket("127.0.0.1",7777);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			InputStream iss = s.getInputStream();
			OutputStream oss = s.getOutputStream();
			byte[] data;
			int readlength = 0;
			data = new byte[10];
			while(true){
				readlength = iss.read(data);
				if(readlength!=0){
					os.write(data,0,readlength);
					os.flush();
				}
			}//
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error!");
		}
	}*/
}