import java.lang.Thread;
import java.util.Vector;
import java.net.SocketException;
import java.net.SocketTimeoutException;
public class RoomChecker extends Thread{
	public void run(){
		while(true){
			//System.out.println("RoomChecker is working");
			try{
				sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
			Vector<Room> rooms = RoomManager.getRooms();
			for(Room r:rooms){
				Client host = r.getHost();
				r.dis();
				/*if(host.socket.isInputShutdown()){
					host.room.removeClient(host);
					System.out.println("remove Room");
					break;
				}*/
				if(r.getClientsNum()==0){
					try{
						host.socket.setSoTimeout(1);
						host.socket.getInputStream().read();
						host.socket.setSoTimeout(0);
					}catch(SocketTimeoutException e){  
	                   // e.printStackTrace(); 
	                   //System.out.println("timeout"); 
	                }catch(SocketException e){  
	                   // e.printStackTrace();  
	                    host.room.removeClient(host);
						//System.out.println("remove Room");
						break;
	                }catch(Exception e){
						host.room.removeClient(host);
						//System.out.println("remove Room");
						break;
						//e.printStackTrace();
					}
				}

			}
		}
	}
}