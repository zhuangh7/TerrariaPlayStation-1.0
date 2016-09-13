import java.util.Vector;

public class Room{
	public String RoomId;
	private Client Host;
	private Vector<Client> clients;

	public Client getHost(){
		return Host;
	}
	public int getClientsNum(){
		return clients.size();
	}
	public void dis(){
		System.out.println(RoomId +" room have "+(clients.size()+1)+" clients");
	}
	public Room(String Id,Client host){
		RoomId = Id;
		clients = new Vector<Client>();
		Host = host;
	}
	public void addClient(Client c){
		clients.add(c);
	}

	public void removeClient(Client c){
		if(c == Host){
			RoomManager.remove(this);
			System.out.println("remove room "+RoomId);
		}else{
			clients.remove(c);
		}
	}
	public void delete(){
		Host.delete();
		for(Client c:clients){
			c.delete();
		}
	}
}