package finalcodecs470;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




 class Clientgo extends Thread {
	 String id;
	 
	 public Clientgo(String id) {
		this.id=id;
	}
 RSA r= new RSA(1024);
	public void run(){
		 
		 String hostName = "192.168.43.74";
		int portNumber = 3797;
		
			BufferedReader in = null;
			BufferedReader stdIn = null;
			PrintWriter out = null;
			  InetAddress ip=null;
		        String fromServer;
				String fromUser;
				byte[] User;
				 Socket socket=null;
				 OutputStreamWriter osw;
				
			
				
			try {
			   socket = new Socket(hostName, portNumber);
			   out = new PrintWriter(socket.getOutputStream(), true);
			   
			   stdIn = new BufferedReader(
				       new InputStreamReader(new ByteArrayInputStream(id.getBytes())));
			  // osw =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			 //  osw.write(Credentials[0][2]);	
			   
			} catch (Exception e) {
				System.out.println("Exception" +e);
			}
			   try {
				while ((fromUser = stdIn.readLine()) != null) {
					    System.out.println("client: validation id sent");
					    User= r.encryptFile(fromUser, r.N, r.d);
					    if (fromUser.equals("Bye."))
					        break;
					    out.println(fromUser+" "+null+" "+User+" "+r.public_key[0]+" "+ r.public_key[1]);
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

		
		int portNumber = 3798;
		
		System.out.println("Creating server socket on port " + portNumber);
		System.setProperty("java.net.useSystemProxies", "true");
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			serverSocket.getReuseAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		
		while(true){
		
			xyz w = null;
			try {
				w = new xyz(serverSocket.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		w.start();
		}
	 }
	
	 
 }
 
 class xyz extends Thread {
	 RSA r =new RSA(1024);
	 Socket so;
	 xyz(Socket so){
		 this.so=so;
	 }
	 
	 public void run() {
			OutputStream os = null ;
			PrintWriter pw;
			String message = " ";
			BufferedReader br = null;
			String Credentials[][];
			Credentials = new String[3][3];
			Credentials[0][0] ="pavani";
			Credentials[0][1] = "pavani123";
			Credentials[0][2] = "pavani456";
			Credentials[1][0] ="lohi";
			Credentials[1][1] = "lohi123";
			Credentials[1][2] = "lohi456";
			Credentials[2][0] ="zubin";
			Credentials[2][1] = "zubin123";
			Credentials[2][2] = "zubin456";
			
			try {
				os = so.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw = new PrintWriter(os, true);
			try {
				br = new BufferedReader(new InputStreamReader(so.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			String str[]=new String[15];
			String messageFromClient = null;
			String Username=null;
			String pass = null;
			String encrypted=null;
			String de=null;
			BigInteger N=null,e = null;
			String s[] = null;
			byte[] User = null;
			while(true) {
			try {
				messageFromClient = br.readLine();
				
					str=messageFromClient.split(" ");
					
					Username=str[0];
					pass = str[1];
		         	encrypted=str[2];
		    		N=new BigInteger(str[3]);
		    		e=new BigInteger(str[4]);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			// check if the string contains "list all the ip", send.
			

			try {
				de = r.decryptFile(encrypted.getBytes(),N,e);
				s = de.split(" ");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i=0; i<3 ; i++){
			
				
						
			if(str[0].equals(Credentials[i][0]) && str[1].equals(Credentials[i][1])){
				try {
					User= r.encryptFile(Credentials[i][2],r.N, r.d);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				pw.println(Credentials[i][2]+" "+User+" "+ r.public_key[0]+" "+ r.public_key[1]);
				Clientgo a=new Clientgo(Credentials[i][2]);
				a.start();
				break;
			}
		/*	else{
			pw.println("Got the message from zubin!!! " + "ERROR");
			}*/
			}
			
			}
		}
			
	 
 }

public class CLA{
	public static void main(String [] args){
		
				
		Servergo b=new Servergo();
		b.start();
		
		
	}
}






















