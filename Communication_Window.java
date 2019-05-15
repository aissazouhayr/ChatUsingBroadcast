import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat.Encoding;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Communication_Window extends JFrame implements ActionListener {
	
	private  JTextField textField;
	
	private JTextArea textarea;
	private  JButton Send;
	private JButton Exit;
	static Border borders;
	private Container content;
	private JScrollPane stackTraceScrollPane;
	private String port = "";
	private String ip = "";
	private InetAddress inetAddress;
	private JFrame frame;
	private  boolean b = false;
    public Socket mySocket ;
    private String Name ="";
	public Communication_Window(Socket S,String portNumber,  InetAddress address, String name) throws UnknownHostException {
		this.mySocket = S;
		this.Name =name;
		this.port = portNumber;
		 inetAddress = address;
		String title = inetAddress+":"+Name;
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(300, 290));

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = frame.getContentPane();
		content.setLayout(new FlowLayout());

		textarea = new JTextArea(20, 30);
		textarea.setEditable(false);
		borders = BorderFactory.createLineBorder(Color.BLUE, 1);
		textarea.setBorder(borders);
		textarea.append("\n");

		stackTraceScrollPane = new JScrollPane(textarea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		content.add(stackTraceScrollPane, BorderLayout.CENTER);

		textField = new JTextField(30);
		textField.setHorizontalAlignment(JTextField.CENTER);
		content.add(textField);

		Send = new JButton("Send");
		Send.setHorizontalAlignment(JTextField.CENTER);
		Send.setSize(new Dimension(200,200));
		content.add(Send);

		Exit = new JButton("Exit");
		Exit.setHorizontalAlignment(JTextField.CENTER);
		content.add(Exit);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setSize(450, (int) frame.getSize().getHeight()+180);
		
		Send.addActionListener(this);
		Exit.addActionListener(this);

	}

	public  void createWindow() {
		frame.setVisible(true);
	}
    // get port as int 
	public  int getPort() {
		return Integer.parseInt(port);
	}
	public  String getStringPort() {
		return port;
	}
	// get ip as string
	public  InetAddress getIp() {

		return inetAddress;
	}
	public  String getStringIP() {
		return ip;
	}
	
	public void setTextArea(String s) {
		textarea.append(s+"\n");
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="Send") {
			String m = textField.getText();
			textarea.append("Aissa: " + m + "\n");	
			mySocket.send(m,inetAddress, getPort());
			
		}
		if(e.getActionCommand()=="Exit") {
			 Set<InetAddress> keys =  TestSocket.wList.keySet();
			 for(InetAddress key :keys) {
				
					
				 if(TestSocket.wList.get(key).frame==frame) {
					 TestSocket.wList.remove(key);	
					 break;
				 }
			 }
            frame.dispose();
			
		}
		
		
		
	}



}
