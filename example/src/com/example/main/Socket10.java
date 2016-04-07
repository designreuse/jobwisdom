package com.example.main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class Socket10 extends JFrame implements Runnable, ActionListener{
	public Panel leftPanel;
    public Panel rightPanel;
    public Panel lastPanel;
    public Button button1=new Button("´´½¨·þÎñÆ÷"),button2,button3,button4;
    public TextArea text1,text2,text3,text4;
    public Label label1,label2,label3,label4; 
    private TextField textfield1,textfield2,textfield3,textfield4;
    
    Thread inithread;
    String IPserver=null;
	 String nameserver=null;
	 String strin=null;
	 String Address = "44444";
	 String name = "44444";
	 String s=null;
	 String Stringss=null;
		String Strname=null;
	 int scan=2;
	 int lock=0;
	 //Stringarray soctr;
	 //Stringarray soctr1;
	 ServerSocket server=null;
	 Socket you=null;
	 DataInputStream in=null,in1=null;
	 DataOutputStream out=null,out1=null;
	private Socket mysocket;
    
    ////////////////////////////////////µÃµ½Ö÷»úÃû×Ö//////////////////////////////////////////////////////
    public static String getname(String s)
    {
    	try
        {
        InetAddress myip=InetAddress.getLocalHost();
        s=myip.getHostName();
        }
        catch(IOException e2)
    	{
    	}	
    	return s;
    }
    
    ////////////////////////////////////µÃµ½Ö÷»úIP//////////////////////////////////////////////////////////
    public static String getip(String s)

    {
    	try
        {
        InetAddress myip=InetAddress.getLocalHost();
        s=myip.getHostAddress();
        }
        catch(IOException e2)
    	{
    	}	
    	return s;
    }
    

    ///////////////////////////////////////´´½¨Ö÷½çÃæ/////////////////////////////////////////
    
    private void launchFrame()
	{
    	label1=new Label("IPµØÖ·");//"IPµØÖ·"
        label2=new Label("±¾»úÃû³Æ");
        textfield1=new TextField(getip(Address),19);
        textfield2=new TextField(getname(name),19);
        textfield3=new TextField(14);
        textfield4=new TextField(14);
        text1=new TextArea(20,68);
        text2=new TextArea(3,68);
        text3=new TextArea(14,25);
        text4=new TextArea(9,25);
        
        
        button1=new Button("´´½¨·þÎñÆ÷");
        button2=new Button("ËÑË÷·þÎñÆ÷");
        button3=new Button("·¢ËÍ");
    	Container c=getContentPane();
        c.setLayout(new BorderLayout());
        Panel firstPanel=new Panel();
        Panel leftPanel=new Panel();
        Panel rightPanel=new Panel();
        Panel subleftPanel1=new Panel();
        Panel subleftPanel2=new Panel();
        Panel lastPanel=new Panel();
        Panel subrightPanel1=new Panel();
        Panel subrightPanel2=new Panel();
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        firstPanel.add(label1);
        firstPanel.add(textfield1);
        firstPanel.add(label2);
        firstPanel.add(textfield2);
        subleftPanel1.add(text1);
        subleftPanel2.add(text2);
        subrightPanel1.add(text3);
        subrightPanel2.add(text4);
        leftPanel.add(subleftPanel1,BorderLayout.NORTH);
        leftPanel.add(subleftPanel2,BorderLayout.SOUTH);
        rightPanel.add(subrightPanel1,BorderLayout.NORTH);
        rightPanel.add(subrightPanel2,BorderLayout.SOUTH);
        firstPanel.add(button1);
        firstPanel.add(button2);
        lastPanel.add(button3);
        c.add(firstPanel,BorderLayout.NORTH);
        c.add(leftPanel,BorderLayout.CENTER);
        c.add(rightPanel,BorderLayout.EAST);
        c.add(lastPanel,BorderLayout.SOUTH);
        this.pack();
        setSize(700,500);
        setTitle("ÁÄÌìHD");
        setVisible(true);
        Window fream;
        setResizable(false);
        
	}
    
     
	//////////////////////////////////////////////////////////////////////////////////////////////////////
    
	 private void addWindowListener(WindowAdapter windowAdapter) throws IOException {
		// TODO Auto-generated method stub
		 if(scan==0)
		 {
			 out.writeUTF("endover");
			 you.close();
			 System.exit(0);
		 }
		 else if(scan==1)
		 {
			 out1.writeUTF("endover");
			 mysocket.close();
			 System.exit(0);
		 }
	}

	/////////////////////////////////////////////¿ªÆô·þÎñ/////////////////////////////////
	 private void startServer()
		{
			try 
			{
				button1.setLabel("Í£Ö¹·þÎñÆ÷");
				ServerSocket server=new ServerSocket(7028);
				you=server.accept();
				
				
			} 
			catch (Exception e) 
			{
				text3.setText("");
				text3.append("·þÎñÆ÷Æô¶¯´íÎó£¬ÇëÖØÐÂÉèÖÃºóÆô¶¯£¡\n¿ÉÄÜÊÇÓÉÓÚ£º\n");
				text3.append("1.¶Ë¿Ú±»Õ¼ÓÃ¡£\n");
				text3.append("2.·þÎñÆ÷ÒÑ¾­Æô¶¯¡£\n");
				e.printStackTrace();
			}
		}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 private void StartClient() 
	 {
		 try
		 {
			 String IP=textfield1.getText();
		    Socket mysocket;
			
			mysocket=new Socket(IP,7028);
			in1=new DataInputStream(mysocket.getInputStream());
			out1=new DataOutputStream(mysocket.getOutputStream());
			text4.append("Á¬½Óµ½·þÎñÆ÷£¡\n");
			nameserver=textfield2.getText();
			out1.writeUTF("begin"+nameserver);
			button2.setLabel("ÍË³ö·þÎñÆ÷");
			//mysocket.close();
		 }
		 catch (Exception e) 
		 {
			 text4.append("ÎÞ·¨Á¬½ÓÍøÂç£¡\n");
		 }
		 
			
	 }
	
	 /////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 public void Message()
		{
			button1.addActionListener(this);
			button2.addActionListener(this);
			button3.addActionListener(this);
		}
	
	 ///////////////////////////////////////////////////////////////////////////////////////////////////
	 public void actionPerformed(ActionEvent e) 
		{
		 //////////////////////////////////////////////////////////////////////////////////////////////
			if(e.getSource()==button1)
			{
				if(lock==0)
				{
				 IPserver=getip(IPserver);
				 text3.setText("");
				 text3.append("·þÎñÆ÷IPµØÖ·£º"+IPserver+"\n");
				 text3.append("·þÎñÆ÷Ãû³Æ£º"+textfield2.getText()+"\n");
				 textfield1.setText(getip(nameserver));
				 text3.append("¶Ë¿ÚºÅ£º7028"+"\n");
				 text3.append("·þÎñÆ÷ÒÑÆô¶¯£¬ÕýÔÚ¼àÌý......"+"\n");
				// button1.setName("Í£Ö¹·þÎñÆ÷");
				 
				 this.startServer();
				 scan=0;
				 lock=1;
				}
				else  
					{
					try {
						out=new DataOutputStream(you.getOutputStream());
						out.writeUTF("endover");
						text3.append("·þÎñÆ÷ÒÑ¹Ø±Õ£¡"+"\n");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						text3.append("·þÎñÆ÷¹Ø±Õ³öÏÖÎÊÌâ£¬ÇëÉÔºóÖØÊÔ£¡"+"\n");
					}
					}
			}
			
			////////////////////////////////////////////////////////////////////////////////////////////
			else if(e.getSource()==button3)
			{ 
				
				if(scan==0)
				{
				 //String IPserver=null;
				 String nameserver=null;
				 try {
					//in=new DataInputStream(you.getInputStream());
					out=new DataOutputStream(you.getOutputStream());
					//IPserver=getip(IPserver);
					nameserver=textfield2.getText();
					if(text2.getText().length()>0)
					{
					text1.append("---"+nameserver+"---Ëµ£º  \n"+text2.getText()+"\n"); 
					out.writeUTF(text2.getText());
					text2.setText("");
					}
					else 
					{
						text2.setText("\n\n                              ÎÞÊäÈëÄÚÈÝ£¡");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						text2.setText("");
						
						}
					//text1.append("---"+nameserver+"---Ëµ£º11  \n"+text2.getText()+"\n"); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//text1.append("---"+nameserver+"---Ëµ£º  \n"+text2.getText()+"\n"); 
					e1.printStackTrace();
				}
				
				}
				else if(scan==1)
				{
					try {
						//in1=new DataInputStream(mysocket.getInputStream());
						//out1=new DataOutputStream(mysocket.getOutputStream());
						nameserver=textfield2.getText();
						if(text2.getText().length()>0)
						{
						text1.append("---"+nameserver+"---Ëµ£º  \n"+text2.getText()+"\n"); 
						out1.writeUTF(text2.getText());
						text2.setText("");
						}
						else 
							{
							text2.setText("\n\n                              ÎÞÊäÈëÄÚÈÝ£¡");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							text2.setText("");
							
							}
						//text1.append("---"+nameserver+"---Ëµ£º 22 \n"+text2.getText()+"\n"); 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else 
				{
					text2.setText("");
					text2.append("\n\n                              ÍøÂçÎ´Á¬½Ó£¡£¡£¡");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					text2.setText("");
				}
				
				
			}
			
			//////////////////////////////////////////////////////////////////////////////////////////////////////
			else if(e.getSource()==button2)
			{if(lock==0)
			{
				 text4.setText("");
				 text4.append("¿ªÊ¼ËÑË÷·þÎñÆ÷£¬ÇëÉÔºó......\n");	
				 scan=1;
				// button2.setName("ÍË³ö·þÎñÆ÷");
				 this.StartClient();
				 lock=1;
			}
			else  
				try {
					out=new DataOutputStream(mysocket.getOutputStream());
					out.writeUTF("endover");
					text4.append("ÒÑÍË³öµÇÂ¼£¡\n");	
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					text4.append("ÍË³öÁ¬½ÓÊ§°Ü£¬ÇëÉÔºóÖØÊÔ£¡\n");	
				}
				//text4.append("ÒÑÁ¬½Ó·þÎñÆ÷£¬ÇëÎðÖØ¸´Á¬½Ó£¡\n");	
			
			}
		}
	
	 ///////////////////////////////////////////////////////////////////////////////////////////////
	 
	 public void MessageSaR() throws InterruptedException, IOException
		{
			if(scan==0)
			{
				try
				{
					//½ÓÊÕÏûÏ¢
				
					//InputStreamReader input=new InputStreamReader(you.getInputStream());
					//BufferedReader read=new BufferedReader(input);
					in=new DataInputStream(you.getInputStream());
					out=new DataOutputStream(you.getOutputStream());
					String recvier=in.readUTF();
					//String Stringss=null;
					//String Strname=null;
					//Stringss=recvier.substring(0,5);
					if(recvier.length()>4)
					{
						Stringss=recvier.substring(0,5);
					if(Stringss.equals("begin")&&recvier.length()>4)
					{
						Strname=recvier.substring(5);
						text3.append("¿Í»§»ú-"+Strname+"-ÒÑµÇÂ¼\n");
						out.writeUTF("conna"+textfield2.getText());
						//Thread.sleep(200);
						//out.writeUTF("conip"+textfield1.getText());
						//text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
						//text1.append("conip"+textfield1.getText()+"\n");
					}
					if(Stringss.equals("endov")&&recvier.length()>4)
					{
						text3.append("¿Í»§»ú-"+Strname+"-ÒÑÍË³ö\n");
						you.close();
					}
					}
					if(recvier.length()<5)
						text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
					if(!Stringss.equals("begin")){
						text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
					}
					
					
					//else text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				//Thread.sleep(100);
			}
			else if(scan==1)
			{
				try
				{
					//½ÓÊÕÏûÏ¢
					//text1.append("¿Í»§¶Ë£º\n");
					
					//DataInputStream in=null;
					//DataOutputStream out=null;
					//mysocket=new Socket("localhost",7028);
					//in1=new DataInputStream(mysocket.getInputStream());
					//out=new DataOutputStream(mysocket.getOutputStream());
					//text1.append("¿Í»§¶Ë2£º\n");
					String recvier=in1.readUTF();
					//text1.append("¿Í»§¶Ë3£º\n");
					
					//if(Stringss.equals("conna"))
					//{
						
					//	text4.append("·þÎñÆ÷Ãû³Æ£º"+Strname+"\n");
						//text1.append("¿Í»§¶Ë4£º"+recvier+"\n");
					//}
					if(recvier.length()>4)
					{
						Stringss=recvier.substring(0,5);
					if(Stringss.equals("conna")&&recvier.length()>4)
					{
						Strname=recvier.substring(5);
						text4.append("·þÎñÆ÷Ãû³Æ£º"+Strname+"\n");
						
						//text1.append("¿Í»§¶Ë4£º"+recvier+"\n");
					}
					if(Stringss.equals("endov")&&recvier.length()>4)
					{
						text4.append("·þÎñÆ÷-"+Strname+"-ÒÑ¹Ø±Õ\n");
						//out1.writeUTF("endover");
						mysocket.close();
					}
					}
					
					//Stringss=recvier.substring(0,5);
					//text1.append("¿Í»§¶Ë1£º"+recvier+"\n");u
					if(recvier.length()<5)
						text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
					if(!Stringss.equals("conna")){
						text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
					}
					//else
					
					//else if(Stringss.equals("conip"))
					//{
					//	Stringss=recvier.substring(5);
					//	text4.append("·þÎñÆ÷IP£º"+Stringss);
					//	text1.append("¿Í»§¶Ë5£º"+recvier+"\n");
					//}
					
						//text1.append("---"+Strname+"---Ëµ£º  \n"+recvier+"\n");
					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				//Thread.sleep(100);
				//mysocket.close();
			}
		}
	 ///////////////////////////////////////////////////////////////////////////////////////
	 public static void main(String args[]) throws InterruptedException, IOException
	    {
		 Socket10 aa=new Socket10();
		 aa.launchFrame();
		 aa.Message();
		 while(true)
		 {
		 aa.MessageSaR();
		
		 }
	    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


}
