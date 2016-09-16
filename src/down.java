import java.lang.Thread;
import java.net.Socket;
import java.io.*;

//By Zhuangh7
public class down extends Thread{
	Socket s;
	Socket local;
	down(Socket ss,Socket local){
		s = ss;
		this.local = local;
	}
	public void run(){
		try{
			OutputStream os = local.getOutputStream();
			InputStream is = s.getInputStream();
			byte[] data;
			int readlength = 0;
			data = new byte[10];
			while(true){
				//System.out.println("down");
				readlength = is.read(data);
				if(readlength!=0){
					os.write(data,0,readlength);
					os.flush();
				}
			}//from the port 7777
		}catch(Exception e){
			//System.out.println("Down exec error!");
			try{
				//e.printStackTrace();
				if(s!=null){
					s.close();
				}
				if(local!=null){
					local.close();
				}
			}catch(Exception ee){

			}
		}

	}
}