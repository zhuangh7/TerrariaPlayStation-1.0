import java.net.ServerSocket;
import java.lang.Thread;
import java.net.Socket;
import java.io.*;
import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 
//By Zhuangh7
public class Server {
	public static int ClientNum = 0;
	
	public static String getTime(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = dateFormat.format( now ); 
		return time;
	}
	public static String getRoomId(){
		int id = ++Server.ClientNum;
		return ""+id;
	}
	public static void main(String[] args){

			try{
				ServerSocket server = new ServerSocket(7778);
				//new RoomChecker().start();
				new Server7777().start();
				while(true){
					System.out.println("begin listen 7778");
					try{
						Socket Host = server.accept();
						//judge is the first time coming
						BufferedReader br=new BufferedReader(new InputStreamReader(Host.getInputStream()));
						Host.setSoTimeout(1000);
						String r = br.readLine();
						System.out.println("port 7778 get "+r);
						if(r.equals("0")){
							String roomId = Server.getRoomId();
							Host.setSoTimeout(0);
							Client temp = new Client(Host,Server.getTime(),true);
							Room newRoom = new Room(roomId,temp);
							temp.setRoom(newRoom);
							temp.dis();
							System.out.println("send RoomId "+roomId);
							RoomManager.add(newRoom);
							//create new Room with the specific RoomId;
							OutputStream os = Host.getOutputStream();
							PrintWriter pw = new PrintWriter(os);
							pw.write(roomId+"\n");
							pw.flush();
							System.out.println("new room "+roomId+" created!");
							//if is the first time come, then return a new RoomId to the client(Host)
						}else{
							System.out.println(r+" come to refresh");
							String roomId = r;
							OutputStream os = Host.getOutputStream();
							PrintWriter pw = new PrintWriter(os);
							pw.write("get\n");
							pw.flush();
							Room room = RoomManager.search(roomId);
							Client host = room.getHost();
							Host.setSoTimeout(0);
							host.newSocket(Host);
							System.out.println("room "+roomId+" refresh");

							//****this will come a synchronize problem;
							//if is the second time come, then refresh the Host socket with a now one;
						}//else
					}catch(Exception ee){

					}




					//Server.ClientNum++;
					//Client c = new Client(Host,Server.getTime(),Server.ClientNum);
					//ClientManager.add(c);
					//System.out.println("client(Host) "+ Server.ClientNum +"access!");
					//System.out.println("client(Host) "+ Server.ClientNum +"access!");
				}//while
				
				/*OutputStream os=Host.getOutputStream();  
	            PrintWriter pw=new PrintWriter(os); 
	            pw.write("1\n");
	            pw.flush();
				new up(s,Host).start();
				System.out.println("up fun begin");
				new down(s,Host).start();
				System.out.println("down fun begin");*/
			}catch(Exception e){
				System.out.println("please restart the server");
				e.printStackTrace();
			}
		
	}


	
	
}