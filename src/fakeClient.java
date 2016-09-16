import java.net.ServerSocket;
import java.lang.Thread;
import java.net.Socket;
import java.io.*;
import java.util.Date; 
import java.util.Calendar; 
import java.text.SimpleDateFormat; 

public class fakeClient{
	public static void main(String[] args){
		try{
			Socket s = new Socket("127.0.0.1",7776);
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("get\n");
			pw.flush();
			BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));  
			String r;
			r = br.readLine();
			System.out.println(r);
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}