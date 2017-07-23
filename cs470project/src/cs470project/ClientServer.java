package cs470project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


class Pair {

    private final String face;  
    private final String suit;  

    Pair(String suit, String face) {
        this.face = face; 
        this.suit = suit;             
    }
    @Override
    public String toString() {
       return "(" + suit + ", " + face + ")";
    }
}

 class Clientgo extends Thread {
	 
	 public void run(){
		 
		 String hostName = "127.0.0.1";
			int portNumber = 3797;
		
			BufferedReader in = null;
			BufferedReader stdIn = null;
			PrintWriter out = null;
			  InetAddress ip=null;
		        String hostname;
		        String fromServer;
				String fromUser;
				 String xy;
				 String[] str=null;
				 Socket kkSocket=null;
				 List<Pair> deck = new ArrayList<Pair>();
				 List<String> suits = new ArrayList<String>();
				 List<String> faces = new ArrayList<String>();
				try {
					ip = InetAddress.getLocalHost();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				
			try {
			   kkSocket = new Socket(hostName, portNumber);
			     out = new PrintWriter(kkSocket.getOutputStream(), true);
					
				
			    in = new BufferedReader(
			        new InputStreamReader(kkSocket.getInputStream()));
			    stdIn = new BufferedReader(
				        new InputStreamReader(System.in));
			    
			} catch (Exception e) {
				System.out.println("Exception" +e);
			}
			   
			try {
				while((fromUser = stdIn.readLine()) != null) {
				    System.out.println("client: " + fromUser);
				    String connectDetails [] = fromUser.split(" ");
				  
				    if (connectDetails[0].equals("help"))
				    {
				    	
				    	out.println("Display information about the available user interface options or command manual.");
				    }
				    
				    if (connectDetails[0].equals("myip"))
				    {
				    	
				    
				    	System.out.println("Your current IP address : " + ip);
				    }
				    
				    if (connectDetails[0].equals("myport"))
				    {
				    	
				    
				    	 System.out.println("Your current Hostname : " + portNumber);
				    }
				    
				   
					
				    if(connectDetails[0].equals("connect")) {
				    	try {
						    kkSocket = new Socket(connectDetails[1], Integer.parseInt(connectDetails[2]));
						     out = new PrintWriter(kkSocket.getOutputStream(), true);
								
							
						    in = new BufferedReader(
						        new InputStreamReader(kkSocket.getInputStream()));
						    stdIn = new BufferedReader(
							        new InputStreamReader(System.in));
						} catch (Exception e) {
							System.out.println("Exception" +e);
						}
		    		
				    }
				     String s1=kkSocket.getInetAddress().toString();
				    suits.add(s1);
				    String s2 =""+kkSocket.getPort();
                    faces.add(s2);
					 
				    if(fromUser.equals("list"))
                    {
                         System.out.println("id:\tIP address\t\tPort No. ");                                                  
                      
                        
                         for(int suit = 0; suit < suits.size(); suit++){

                        	    for(int face = 0; face < faces.size(); face++){

                        	        deck.add(new Pair(suits.get(suit), faces.get(face)));             

                        	    }

                        	}
                        StringBuilder builder = new StringBuilder();
                         for (Pair value : deck) {
                             builder.append(value);
                         }
                         String text = builder.toString();
                       	  
                       	 
                         String formatedString = text.toString()
                        		    .replace(",", "")  //remove the commas
                        		    .replace("(", "")  //remove the right bracket
                        		    .replace(")", "") //remove the left bracket
                        		    .replace("/", "")
                        		    .trim();  
                     
                         System.out.println(" " + formatedString);
                    }
				    
				 /*   if(fromUser.equals("list"))
                    {
                         System.out.println("id:\tIP address\t\tPort No. ");                                                  
                      
                       	  
                       	 System.out.println(":\t"+kkSocket.getInetAddress()+"\t"+kkSocket.getPort()); 
                       	 
                       	 
                         
                    }
				   */
				    
				    
				        out.println(fromUser);
				    //Thread.sleep(1000);
				    fromServer  = in.readLine();
				    if (fromServer != null) {
				    	{
				    	System.out.println("From Server: " + fromServer);
				    	}
				   }
				    	
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	

	
}
 class Servergo extends Thread {
	 @SuppressWarnings("resource")
	public void run(){
	  ServerSocket serverSocket = null;
		int i =0;
	 Socket so;
		
		int portNumber = 3797;
		
		System.out.println("Creating server socket on port " + portNumber);
		System.setProperty("java.net.useSystemProxies", "true");
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			serverSocket.getReuseAddress();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		while(true){
		
			xyz w = null;
			try {
				w = new xyz(serverSocket.accept());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		w.start();
		}
	 }
	
	 
 }
 
 class xyz extends Thread{
	 
	 Socket so;
	 xyz(Socket so){
		 this.so=so;
	 }
	 public void run() {
			OutputStream os = null ;
			PrintWriter pw;
			BufferedReader br = null;
			try {
				os = so.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw = new PrintWriter(os, true);
			try {
				br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String messageFromClient = null;
			while(true) {
			try {
				messageFromClient = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// check if the string contains "list all the ip", send.
			pw.println("Got the message from zubin!!! " + messageFromClient);
			}
		}
	 
 }

public class ClientServer{
	public static void main(String [] args){
		Clientgo a=new Clientgo();
		a.start();
		
		Servergo b=new Servergo();
		b.start();
	}
}

















