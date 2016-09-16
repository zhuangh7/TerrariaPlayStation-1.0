import java.net.ServerSocket;
import java.lang.Thread;
import java.net.Socket;
import java.io.*;
import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 

public class fakeServer{
	public static void main(String[] args){
		try{
			ServerSocket ss = new ServerSocket(7777);
			Socket s = ss.accept();
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));  
			String r;
			r = br.readLine();
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("get\n");
			pw.flush();
			System.out.println(r);
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}