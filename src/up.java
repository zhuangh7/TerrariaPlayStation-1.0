import java.lang.Thread;
import java.net.Socket;
import java.io.*;

//By Zhuangh7
public class up extends Thread{
	Socket s;
	Socket local;
	up(Socket ss,Socket local){
		s = ss;
		this.local = local;
	}
	public void run(){
		try{
			OutputStream os = s.getOutputStream();
			InputStream is = local.getInputStream();
			byte[] data;
			int readlength = 0;
			data = new byte[10];
			while(true){
				//System.out.println("up");
				readlength = is.read(data);
				if(readlength!=0){
					os.write(data,0,readlength);
					os.flush();
				}
			}//to local 7777 port
		}catch(Exception e){
			//System.out.println("Up exec error!");
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