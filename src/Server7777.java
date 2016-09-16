import java.net.ServerSocket;
import java.lang.Thread;
import java.net.Socket;
import java.io.*;
import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 
import java.lang.Integer;

public class Server7777 extends Thread{
	public static ServerSocket ss;
	public void run(){
		try{
			Server7777.ss = new ServerSocket(7777);
		}catch(Exception ee){
			ee.printStackTrace();
			System.out.println("the port 7777 for your pc is already used !!!");
		}
			while(true){
				try{
					Socket s = Server7777.ss.accept();
					BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));  
					String r;
					r = br.readLine();
					Room room = RoomManager.search(r);
					if(room == null){
						s.close();
					}else{
						OutputStream os = s.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.write("get\n");
						pw.flush();
						System.out.println("client access to room "+r);
						Client temp = new Client(s,Server.getTime(),false);
						temp.run(room.getHost().socket);
						room.addClient(temp);
						System.out.println("new client crated success ~"); 
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		
	}

}