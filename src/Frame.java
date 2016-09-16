import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Frame {
	public static JLabel roomId = new JLabel("null");
	public static boolean running = false;
	public static JFrame mainFrame = new JFrame("Terraria");
	
	public static void main(String[] args){
		
		//JFrame mainFrame = new JFrame("Terraria");
		mainFrame.setResizable(false);
		mainFrame.setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Frame
		int frameWidth = 400;
		int frameHeight = 170;
		mainFrame.setSize(frameWidth, frameHeight);
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		mainFrame.setLocation((width-frameWidth)/2, (height-frameHeight)/2);
		mainFrame.setVisible(true);
		
		
		JPanel panel = new JPanel();
		panel.setLocation(0,0);
		panel.setSize(400,45);
		
		//panel.setForeground(Color.black);
		//Panel
		mainFrame.add(panel);
		JButton host = new JButton("Start as Host");
		//Button
		panel.add(host);
		JLabel runing = new JLabel("stop",Label.RIGHT);
		//Label
		runing.setFont(new   java.awt.Font("Dialog",   0,   15));
		runing.setForeground(Color.gray);
		panel.add(runing);
		//Client4Terraria mainTerraria = new Client4Terraria();
		host.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e){
	        	if(!Frame.running){	
		            if(JOptionPane.showConfirmDialog(mainFrame, "点击确认开启服务器","确认",JOptionPane.OK_CANCEL_OPTION)==0)
		            {
		               Client4Terraria mainTerraria = new Client4Terraria(new String[0]);
		               waitClient.run = true;
		               mainTerraria.start();
		               runing.setText("running");
		               runing.setForeground(Color.red);
		               host.setText("Stop");
		               Frame.running = true;
		               
		            }
		        }else{
		        	 if(JOptionPane.showConfirmDialog(mainFrame, "真的要关闭服务器吗","确认",JOptionPane.OK_CANCEL_OPTION)==0)
		        	 {
		        		 waitClient.run = false;
			             runing.setText("stop");
			             runing.setForeground(Color.gray);
			             host.setText("Start as Host");
			             Frame.roomId.setText("null");
			             Frame.running = false;
			         }
		        }
	        }
	    });
		panel.add(Frame.roomId);
		
		
		JPanel clientPanel = new JPanel();
		//Panel
		mainFrame.add(clientPanel);
		//Frame add
		clientPanel.setLocation(0,50);
		clientPanel.setSize(400,80);
		clientPanel.setLayout(new FlowLayout());
//		clientPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JButton clinet = new JButton("Start as Client");
		//Button
		clinet.setSize(114,28);
		clinet.setLocation(114, 5);
		clientPanel.add(clinet);
		//clinet.setLocation(0, 0);
		JLabel runn = new JLabel("stop");
		//Label
		runn.setSize(50,30);
		runn.setLocation(232, 5);
		runn.setFont(new java.awt.Font("Dialog",   0,   15));
		runn.setForeground(Color.gray);
		clientPanel.add(runn);
		JLabel Id = new JLabel("RoomID:");
		clientPanel.add(Id);
		JTextField room = new JTextField(10);
		room.setEnabled(true);
		//room.setColumns(10);
		clientPanel.add(room);

		clinet.addActionListener(new ActionListener(){
			@Override
	        public void actionPerformed(ActionEvent e){
	        	if(!Frame.running){	
		            if(JOptionPane.showConfirmDialog(mainFrame, "点击确认加入房间","确认",JOptionPane.OK_CANCEL_OPTION)==0)
		            {
		            	String roomId = room.getText();
		            	String[] room = new String[1];
		            	System.out.println(roomId);
		            	room[0] = roomId;
		            	Client4Terraria mainTerraria = new Client4Terraria(room);
		            	mainTerraria.start();
		            	runn.setText("running");
			            runn.setForeground(Color.red);
			            clinet.setText("Stop");
			            Frame.running = true;
		               
		            }
		        }else{
		        	 if(JOptionPane.showConfirmDialog(mainFrame, "真的要关闭连接吗","确认",JOptionPane.OK_CANCEL_OPTION)==0)
		        	 {
		        		 //waitClient.run = false;
			             runn.setText("stop");
			             runn.setForeground(Color.gray);
			             clinet.setText("Start as Client");
			             Frame.running = false;
			         }
		        }
	        }
		});
	}
}
