import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.CharBuffer;
import java.util.Hashtable;
import java.util.Set;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
// Create a simple GUI window
public class MySocketWIndow  extends JFrame implements ActionListener{

	 private String IP="";
	 private int portNumber; 
	 private InetAddress address;
	 private JTextField  IP_textField;
	 private JLabel   IP_Label;
	 private JTextField  Port_textField;
	 private JTextField broadcast_textField;
	 private JLabel   Port_Label;
	 private JLabel   Label;
	 private JButton Start;
	 private  JButton Exit;
	 private Border borders ;
	 private JFrame frame;
	 public Socket mySocket ;
	 private JButton Broadcast;
	 private String name ="";
	 static Hashtable<String, String> Names = new Hashtable<>();

	 public  MySocketWIndow( Socket S ,int portNumber, InetAddress address) {
		 this.mySocket = S;
		 this.portNumber = portNumber;
		  
		  this.address = address;
		  String title = address +":"+ ( portNumber+"");
	      frame = new JFrame(title);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Border border = BorderFactory.createEmptyBorder(10,10,10,10);
	      Container content = frame.getContentPane();
	      ((JComponent) content).setBorder(border);
	      content.setLayout(new GridLayout(0, 2));
	      
          
	      Label = new JLabel("   Enter Name:     ");
		    
	      Label.setHorizontalAlignment(JTextField.CENTER);
	      content.add(Label);
	      
	    
	      broadcast_textField= new JTextField("");
	      broadcast_textField.setHorizontalAlignment(JTextField.CENTER);
	      content.add(broadcast_textField);
	      
	      Exit = new JButton("Exit");     
	      Exit.setHorizontalAlignment(JTextField.CENTER);
	     
	      content.add(Exit);
	      
	      Broadcast =new JButton("Send Broadcast");
	      Broadcast.setHorizontalAlignment(JTextField.CENTER);
	      content.add(Broadcast);
	      
	      frame.pack();
	      frame.setSize(550, (int) frame.getSize().getHeight()+20);

	      Exit.addActionListener(this);
	      Broadcast.addActionListener(this);
	 }
	 
	 public void createWindow() {
	      frame.setVisible(true);
	     
	   }
		
	
		public  boolean checkWindowExistence(String address, String port) {
			boolean b =false;
			Set<InetAddress> keys = TestSocket.wList.keySet();
            for(InetAddress key: keys) {
            	if(TestSocket.wList.get(key).getStringIP()==address&&
            			TestSocket.wList.get(key).getStringPort()==port) 
            	{
            		b =  true;
            		break;
                }
            }
			  return b;
		}
	
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			 
			 if(e.getActionCommand()=="Send Broadcast") {
				 String ip  = "255.255.255.255";
						
				 String port = "64000";
						
				 String message = broadcast_textField.getText();
				  name = message ;
				 
				  Names.put(name,name);
				 message = "?????"+" "+ name+" " +"#####"+" "+ "aissa";
				 
				 InetAddress inetAddress;
				 try {
					 
					inetAddress = InetAddress.getByName(ip);
					mySocket.send(message,inetAddress,Integer.parseInt(port));
					
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				 
			 }
			 if(e.getActionCommand()=="Exit") {
				 System.out.println("Exit button clicked");				 
				 frame.dispose();
				 mySocket.close();
				 
			 }
			
		}
}
