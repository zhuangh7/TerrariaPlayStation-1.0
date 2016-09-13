import java.net.ServerSocket;
import java.lang.Thread;
import java.net.Socket;
import java.io.*;
import java.util.Vector;

public class Client{
		public Socket socket;
		public String comeDate;
		public boolean isHost;
		public Room room;
		public void setRoom(Room r){
			room = r;
		}
		public void delete(){
			try{
				if (socket!=null){
					socket.close();
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		public void newSocket(Socket ss){
			socket = ss;
		}
		public Client(Socket s,String d,boolean i){
			socket = s;
			comeDate = d;
			isHost = i;
		}

		public void dis(){
			System.out.println("Client: "+socket.toString()+" "+comeDate+" ");
		}

		public void run(Socket host){
			if(!isHost){
				try{
					//OutputStream os=host.getOutputStream();  
		            //PrintWriter pw=new PrintWriter(os); 
		            //.write("1\n");
		           //w.flush();
					new up(socket,host).start();
					System.out.println("up fun begin ");
					new down(socket,host).start();
					System.out.println("down fun begin ");
				}catch(Exception e){
					e.printStackTrace();
					room.removeClient(this);
				}
			}
		}
	}