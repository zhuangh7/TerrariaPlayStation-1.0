import java.lang.Thread;
import java.net.ServerSocket;
import java.lang.Thread;
import java.net.Socket;
import java.io.*;
import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 

public class waitClient extends Thread{
	String roomId;
	Socket ss;
	public static boolean run = true;
	public waitClient(String roomId,Socket s){
		this.roomId = roomId;
		ss = s;
	}
	public void run(){
		if(waitClient.run){
			try{
				
				BufferedReader br=new BufferedReader(new InputStreamReader(ss.getInputStream()));  
				System.out.println("wait new Client =- =");
				String r = br.readLine();
				System.out.println("new client access your Room "+roomId);
				r = br.readLine();
				if(r==null){
					System.out.println("client leave");
					Socket newSocket = new Socket("123.206.208.46",7778);
					OutputStream os=newSocket.getOutputStream();  
			        PrintWriter pw=new PrintWriter(os); 
			        pw.write(roomId+"\n");
			        pw.flush();
			        BufferedReader brr=new BufferedReader(new InputStreamReader(newSocket.getInputStream())); 
			        if(brr.readLine().equals("get")){
			        	System.out.println("refresh");
			    		new waitClient(roomId,newSocket).start();
			        }else{
			        	System.out.println("refresh error!");
						new waitClient(roomId,newSocket).start();
			        }
				}else{
					System.out.println("some one try to join ");
					Socket localS = new Socket("127.0.0.1",7777);
					//System.out.println("local connected~");
					System.out.println("begin fun~");
					new up(ss,localS).start();
					new down(ss,localS).start();
					//refresh server socket
					System.out.println("wo yao fresh!");
					Socket newSocket = new Socket("123.206.208.46",7778);
					OutputStream os=newSocket.getOutputStream();  
			        PrintWriter pw=new PrintWriter(os); 
			        pw.write(roomId+"\n");
			        pw.flush();
			        BufferedReader brr=new BufferedReader(new InputStreamReader(newSocket.getInputStream())); 
			        if(brr.readLine().equals("get")){
			        	System.out.println("refresh");
			    		new waitClient(roomId,newSocket).start();
			        }else{
			        	System.out.println("refresh error!");
						new waitClient(roomId,newSocket).start();
			        }
			        //new waitClient(roomId,newSocket).start();
				}
		    }catch(Exception e){
		    	try{
		    		System.out.println("catch");
			    	e.printStackTrace();
			    	Socket newSocket = new Socket("123.206.208.46",7778);
					OutputStream os=newSocket.getOutputStream();  
			        PrintWriter pw=new PrintWriter(os); 
			        pw.write(roomId+"\n");
			        pw.flush();
			        BufferedReader brr=new BufferedReader(new InputStreamReader(newSocket.getInputStream())); 
			        if(brr.readLine().equals("get")){
			        	System.out.println("refresh");
			    		new waitClient(roomId,newSocket).start();
			        }else{
			        	System.out.println("refresh error!");
						new waitClient(roomId,newSocket).start();
			        }
			    	//new waitClient(roomId,newSocket).start();
		   	 	}catch(Exception ee){
		   	 		System.out.println("terrible");
		   	 		ee.printStackTrace();
		   	 	}
		    }
		}
	}
}