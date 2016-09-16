import java.util.Vector;

public class RoomManager{
		private static Vector<Room> rooms = new Vector<Room>();

		public static boolean add(Room c){
			rooms.add(c);
			return true;
		}

		public static boolean dis(){
			for(Room c:rooms){
				c.dis();
			}
			return true;
		}
		public static void remove(Room c){
			c.delete();
			rooms.remove(c);
			//sSystem.out.println("reduce room");
		}
		public static Room search(String id){
			
			for(Room c:rooms){
				if(c.RoomId.equals(id)){
					return c;
				}
			}
			
			return null;
		}
		public static Vector<Room> getRooms(){
			return rooms;
		}
	}