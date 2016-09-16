import java.net.Socket;

import javax.swing.JOptionPane;

import java.io.*;
import java.net.ServerSocket;

//By Zhuangh7
public class Client4Terraria extends Thread{
	private String[] args;
	/*public static void main(String[] args){
		//int port;
		String room;
		System.out.println("begin~");
		if(args.length!=0){
			//port = Integer.parseInt(args[0]);
			room = args[0];
			Client4Terraria.connectAsClient(room);
		}else{
			Client4Terraria.connectAsHost();
		}		
	}*/
	
	public Client4Terraria(String[] args){
		this.args = args;
	}
	public void run(){
		String room;
		System.out.println("begin~");
		if(args.length!=0){
			//port = Integer.parseInt(args[0]);
			room = args[0];
			Client4Terraria.connectAsClient(room);
		}else{
			Client4Terraria.connectAsHost();
		}
	}
	public static void connectAsClient(String room){
		try{
			String roomId = room;
			Socket remote = new Socket("123.206.208.46",7777);
			System.out.println("connect Server success");
			OutputStream os = remote.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(roomId+"\n");
			pw.flush();
			System.out.println("send roomId");
			BufferedReader br=new BufferedReader(new InputStreamReader(remote.getInputStream()));  
			String r;
			remote.setSoTimeout(1000);
			r = br.readLine();
			if(r.equals("null")){
				JOptionPane.showMessageDialog(Frame.mainFrame.getContentPane(),"No such RoomId!", "系统信息", JOptionPane.WARNING_MESSAGE);
				System.out.println("null No such RoomId!!!");
				//System.quit(0);
			}else{
				remote.setSoTimeout(0);
				System.out.println("enter the specific room");
				pw.write("1\n");
				pw.flush();
				//now the server will create the connect with local and remote ,what's more is to connect local to this socket named"remote";
				ServerSocket local = new ServerSocket(7777);//*********************************
				Socket l = local.accept();
				System.out.println("game begin!");
				pw.write("1\n");
				pw.flush();

				new up(remote,l).start();
				System.out.println("up fun begin ");
				new down(remote,l).start();
				System.out.println("down fun begin ");
			}
			//connect 127.0.0.1 in the game
		}catch(Exception e){
			JOptionPane.showMessageDialog(Frame.mainFrame.getContentPane(),"No such RoomId!", "系统信息", JOptionPane.WARNING_MESSAGE);
			System.out.println("timeout No such RoomId!");
			e.printStackTrace();
		}
	}
	public static void connectAsHost(){
		String roomId;
		try{
			Socket ss = new Socket("123.206.208.46",7778);
			System.out.println("server connected~");
			OutputStream os=ss.getOutputStream();  
            PrintWriter pw=new PrintWriter(os); 
            pw.write("0\n");
            pw.flush();
			BufferedReader br=new BufferedReader(new InputStreamReader(ss.getInputStream()));  
			String r;
			System.out.println("requesting Room Id");
			r = br.readLine();
			roomId = r;
			System.out.println("your Room Id is :"+r);
			Frame.roomId.setText(roomId);
			new waitClient(roomId,ss).run();	
		}catch(Exception e){
			JOptionPane.showMessageDialog(Frame.mainFrame.getContentPane(),"connect server failed!", "系统信息", JOptionPane.WARNING_MESSAGE);
			System.out.print("connect server failed");
			e.printStackTrace();
		}
		
	}

	/*public void connect(Socket s){
		try{
			Socket socket  = new Socket("127.0.0.1",7777);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			InputStream iss = s.getInputStream();
			OutputStream oss = s.getOutputStream();
			byte[] data;
			int readlength = 0;
			data = new byte[10];
			while(true){
				readlength = iss.read(data);
				if(readlength!=0){
					os.write(data,0,readlength);
					os.flush();
				}
			}//
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error!");
		}
	}*/
}