import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TestSocket  {


	
	static Socket mySocket;  
     static Socket mySocket2;
	static MySocketWIndow  wSocket;
	private InetAddress inetAddress;
	private  String ip="";
	private String port ="";
	static String text ="";
	static  String  newline = "\n";
	static  Communication_Window    w ;
	static Hashtable<InetAddress, Communication_Window> wList = new Hashtable<>();
	public TestSocket() {
		
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		 mySocket = new Socket(64000, Socket.SocketType.Broadcast);
		wSocket = new  MySocketWIndow(mySocket,mySocket.getPortNumber(),mySocket.getAddress());
		wSocket.createWindow();
		 TestSocket  driverSocket = new TestSocket ();
		 driverSocket.Display();
		
       
		
	}
	    // to diplay the socket window
		public void Display() {
	
			
			 
			 do {
			       
		 		 
					
					//	 System.out.println(" Wlist size is= "+TestSocket.wList.size());
							    DatagramPacket inPacket = mySocket.receive();
							   
							    
							    byte[] inBuffer ;
								String  inMessage ;
								InetAddress  senderAddress;
								int senderPort ;
								String Name ="";
								String IPS ="";
							System.out.println("received inpacket "+ (inPacket==null));
							if (inPacket != null) 
							{
								
							//	System.out.println("received packet "+wList.size());
								inBuffer = inPacket.getData();
								inMessage = new String(inBuffer);
								senderAddress = inPacket.getAddress();
								senderPort = inPacket.getPort();
								System.out.println("received mesaage "+ inMessage);
								System.out.println("senderAddress"+ senderAddress);
								if(inMessage.startsWith("?????"))
								{
									
						
									//check if some one looked for me by name
									if(inMessage.substring(5, inMessage.lastIndexOf("#")-4).contains("aissa")) {
									   
										// set the message containing my name and send it 
										String m = "##### "+ "aissa" + " ##### " +mySocket.getAddress();
														
										InetAddress broadcast;
										try {
											broadcast = InetAddress.getByName("255.255.255.255");
											mySocket.send(m, broadcast, mySocket.getPortNumber());
											
											
											
										    //	String name = inMessage.substring(5, inMessage.lastIndexOf("#")-4);
											//String N = name.substring(1, name.length()-1);
											
											String N = inMessage.substring(inMessage.lastIndexOf("#")+1,inMessage.length());
											String Port = senderPort +"";
											System.out.println("senderAddress"+senderAddress);
											Communication_Window w = new Communication_Window(mySocket,Port,senderAddress,N ); 
											wList.put(senderAddress,w);
											wSocket.Names.put(N,N);
										} 
										catch (UnknownHostException e) 
										{
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										//inPacket = null;
									}
								}
								else if(inMessage.startsWith("#####")) 
								{ 
									System.out.println("inside #");
									String name = inMessage.substring(5, inMessage.lastIndexOf("#")-4);
									String N = name.substring(1, name.length()-1);  
									
									String ip = inMessage.substring(inMessage.lastIndexOf("#")+1,inMessage.length());
									if(wSocket.Names.containsKey(N))
									{
									
									
									
									String Port = senderPort +"";
									try {
									
									//	Communication_Window w = new Communication_Window(mySocket,Port,IPS,N );
										System.out.println("senderAddress"+senderAddress);
										Communication_Window w = new Communication_Window(mySocket,Port,senderAddress,N ); 
										wList.put(senderAddress,w);
										wList.get(senderAddress).createWindow();
										System.out.println(wList.size());
										//wSocket.Names.remove(N);
										
										
									} catch (UnknownHostException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									
									
									}
									
								}
								
	                          else {
							    	
							    	//if (winTable.containsKey(key)) 
									//chat = wList.get(key);
	                  //      	  if (!inMessage.startsWith("?") && (!inMessage.startsWith("#"))) {
	                        		  /*	if(wList.containsKey(senderAddress)) {
											wList.get(senderAddress).setTextArea( inMessage + "\n");
											//chat.win();
										}
										else {
											String Port = senderPort +"";
											Communication_Window w = new Communication_Window(mySocket,Port,senderAddress,"" ); 
											wList.put(senderAddress,w);
											wList.get(senderAddress).createWindow();
											wList.get(senderAddress).setTextArea(inMessage);
											
										}*/
	                        	        if(!wList.get(senderAddress).isVisible()) {
	                        	        	wList.get(senderAddress).createWindow();
	                        	        }
										
										wList.get(senderAddress).setTextArea( inMessage);
										//chat.win();
					//				}
							    }

							    
								
								
								
					
								} 
			    			
			                    
			                    
			                    
			                    
			                    
							
							else {  
							//	System.out.println("nothing " +wList.size());
								try {
									
									TimeUnit.SECONDS.sleep(2);
								} catch (InterruptedException ie) {
									ie.printStackTrace();
									System.exit(-1);
								}
							}
						
							
					 }while(true);
			
			 
		
		
		}
	
      
}


