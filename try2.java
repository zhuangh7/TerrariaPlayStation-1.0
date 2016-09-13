import java.net.Socket;
import java.io.*;
import java.net.ServerSocket;
import java.lang.Thread;


public class try2 {

	public static void show(Socket client){
		System.out.println("\nshow Information ");
		System.out.println("isConnected " + client.isConnected());
		System.out.println("isBound "+client.isBound());
		System.out.println("isClosed "+client.isClosed());
		System.out.println("isInputShutdown "+client.isInputShutdown());
		System.out.println("isOutputShutdown "+client.isOutputShutdown());
	}
	public static void main(String[] args){

		String tag = args[0];
		switch(tag){
			case "1":
				try{
					System.out.println("1");
					ServerSocket server = new ServerSocket(1233);
					Socket client = server.accept();
					System.out.println("listen");
					try2.show(client);
					Thread.sleep(1000);
					try2.show(client);
					/*OutputStream os = client.getOutputStream();
					PrintWriter pw = new PrintWriter(os);
					pw.write("1"+"\n");
					pw.flush();*/

					/*BufferedReader br =new BufferedReader(new InputStreamReader(client.getInputStream()));  
					String s = br.readLine();*/

					InputStream is = client.getInputStream();
					is.read();
					System.out.println("success");
					break;
				}catch(Exception e){
					e.printStackTrace();
					break;
				}

			case "2":
				try{
					System.out.println("2");
					Socket s = new Socket("127.0.0.1",1233);
					System.out.println("connected");
					Thread.sleep(2000);
					while(true){

					}
					//s.close();
					//System.out.println("close");
					//break;
				}catch(Exception e){
					e.printStackTrace();
				}
		
		}
	}
}